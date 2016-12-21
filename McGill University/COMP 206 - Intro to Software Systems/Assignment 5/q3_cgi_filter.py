#!/usr/bin/python

import os
import sys
import struct
import copy
from ctypes import *
#import pickle
import cPickle as pickle
import cgi
import cgitb; cgitb.enable()

history_file_name = "history.pickle"
result_file_name = "result.bmp"
# Libname
LibName = "libfast_filter.so"

# Header 
print ("Content-type: text/html; charset=utf-8\n\n")
print ("<html><head><title>Filter web interface</title></head>")
print ("<body>")


class History():
    def __init__(self):
        # Previous image from current (Undo), next image from current (Redo) 
        self.undo = []
        self.redo = []
        # Current active image
        self.active = None

    def getActiveBMP(self):
        return self.active

    def reset(self, bmp_bytes): # Load option
        self.undo = []
        self.redo = []
        self.active = copy.deepcopy(bmp_bytes)

    def setNewPoint(self, bmp_bytes): # Filter option
        self.redo = [] # Discar forwad history
        self.undo.append(self.active)
        self.active = copy.deepcopy(bmp_bytes)

    def backward(self): # Undo option
        if len(self.undo) == 0: return False
        self.redo.append(self.active)
        self.active = self.undo.pop()
        return True

    def forward(self): # Reado option
        if len(self.redo) == 0: return False
        self.undo.append(self.active)
        self.active = self.redo.pop()
        return True




# Reads a BMP image from disk into a convenient array format
def loadBMPImage( img_file_name ):
    data = []
    with open( img_file_name, 'rb' ) as bmp_file:
        byte = ' '
        while byte != "":
            # Do stuff with byte.
            byte = bmp_file.read(1)
            if byte != '': data.append(ord(byte))
    return data

def saveBMPImage( img_file_name, bmp_bytes ):
    with open( img_file_name, 'wb' ) as bmp_file:
        bmp_file.write( "".join(map(chr, bmp_bytes)) )


args = cgi.FieldStorage()



# Create initialy empty history
history = History()
# Load history file or create empty
try:
    with open(history_file_name, "rb") as history_file:            
        history = pickle.load(history_file)
except:
    with open(history_file_name, "wb") as history_file:
        pickle.dump(history, history_file)

print("Before<br>")
print("Undo: %d<br>" % len(history.undo))
print("Redo: %d<br>" % len(history.redo))
print("<hr>")

print ("Status of previous command: ")

if "load" in args:
    if args["photo"].value == '':
        print("No new file data provided<br>")
    else:
        bmp_bytes = str(args["photo"].value)
        bmp_bytes = list(map(ord, bmp_bytes))
        history.reset(bmp_bytes)
        saveBMPImage(result_file_name, bmp_bytes)
        print(" Success.<br>")
elif "filter" in args:
    
    filter_weights = []
    filter_width = 3
    for i in range(3):
        for j in range(3):
            filter_weights.append(float(args[str(i) + str(j)].value))
    # Load input file (function return list of bytes)
    bmp_bytes = loadBMPImage(result_file_name) # Load previous image
    # Create empty list for result image
    result_bytes = [0]* len(bmp_bytes)
    # Calculate path to library
    path_to_lib = os.path.join(os.getcwd(), LibName)
    # Load library
    filter_lib = cdll.LoadLibrary(path_to_lib)
    # Convert data to c types
    c_bmp_bytes = (c_ubyte * len(bmp_bytes))(*bmp_bytes)
    c_filter_weights = (c_float * len(filter_weights))(*filter_weights)    
    c_result_bytes = (c_ubyte * len(result_bytes))(*result_bytes)
    # Call external routine
    filter_lib.doFiltering(c_bmp_bytes, c_filter_weights, filter_width, c_result_bytes)
    # Save result image
    with open(result_file_name, "wb") as result_file:
        result_file.write(c_result_bytes)
    # Set new history point
    history.setNewPoint([c_result_bytes[i] for i in range(len(c_result_bytes))])
    print(" Success.<br>")

elif "undo" in args:
    if history.backward() == False:
        print("Already at beginning of history.")
    else:
        saveBMPImage(result_file_name, history.getActiveBMP())
        print(" Success.<br>")

elif "redo" in args:
    if history.forward() == False:
        print("Already at end of history.")
    else:
        saveBMPImage(result_file_name, history.getActiveBMP())
        print(" Success.<br>")


with open(history_file_name, "wb") as history_file:
    pickle.dump(history, history_file)


print('<form name="input" action="./q3_cgi_filter.py" method="post" enctype="multipart/form-data">')
print('<p>Photo to Upload: <input type="file" name="photo" /></p>')
print('<p>First Filter to Apply:</p>')
print('<p><input type="text" name="00" value="1"> <input type="text" name="01" value="1"> <input type="text" name="02"  value="1"> </p>')
print('<p><input type="text" name="10" value="1"> <input type="text" name="11" value="-7"> <input type="text" name="12" value="1"> </p>')
print('<p><input type="text" name="20" value="1"> <input type="text" name="21"  value="1"> <input type="text" name="22" value="1"> </p>')
print('<input type="submit" value="Load" name="load">')
print('<input type="submit" value="Filter" name="filter">')
print('<input type="submit" value="Undo" name="undo">')
print('<input type="submit" value="Redo" name="redo">')
print('</form>')
print("<br>")
print("After<br>")
print("Undo: %d<br>" % len(history.undo))
print("Redo: %d<br>" % len(history.redo))
print("<hr>")

print('<img src="result.bmp"/>')

print "</body>"
print "</html>"
