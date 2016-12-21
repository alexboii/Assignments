# -*- coding: utf-8 -*-
# Python 2.7
# Alexander Bratyshkin
# 260684228 

import os
import sys
import struct
import copy
from ctypes import *
# Libname
LibName = "libfast_filter.so"
os.environ['LD_LIBRARY_PATH'] = os.getcwd()


def print_usage():
    print("Usage: ./" + os.path.basename(__file__) + " <input image> <output image> <filter size> <filter weights>")


# Read the filter information from command line and 
# set it up to be used on the image  
def parseFilterCmdArgs( cmd_args ):

    filter_width = int( cmd_args[3] )
    filter_weights = []
    filter_offsets = []

    for i in range(0, filter_width * filter_width):
        filter_weights.append( float(cmd_args[4+i] ))

    return ( filter_width, filter_weights )

# Reads a BMP image from disk into a convenient array format
def loadBMPImage( img_file_name ):
    data = []
    with open( img_file_name, 'rb' ) as bmp_file:
        #data = bmp_file.read()
        byte = ' '
        while byte != "":
            # Do stuff with byte.
            byte = bmp_file.read(1)
            if byte != '': data.append(ord(byte))
    return data


def main():
    # Check user command line arguments
    if len(sys.argv) < 5:
        print_usage()
        return
    # Parse arguments
    (filter_width, filter_weights) = parseFilterCmdArgs( sys.argv )
    # Load input file (function return list of bytes)
    bmp_bytes = loadBMPImage(sys.argv[1])
    # Create empty list for result image
    result_bytes = [0]* len(bmp_bytes)#copy.deepcopy(bmp_bytes)
    # Calculate path to library
    path_to_lib = os.path.join(os.environ['LD_LIBRARY_PATH'], LibName)
    # Load library
    filter_lib = cdll.LoadLibrary(path_to_lib)
    # Convert data to c types
    c_bmp_bytes = (c_ubyte * len(bmp_bytes))(*bmp_bytes)
    c_filter_weights = (c_float * len(filter_weights))(*filter_weights)    
    c_result_bytes = (c_ubyte * len(result_bytes))(*result_bytes)
    # Call external routine
    filter_lib.doFiltering(c_bmp_bytes, c_filter_weights, filter_width, c_result_bytes)
    # Save result image
    with open(sys.argv[2], "wb") as result_file:
        result_file.write(c_result_bytes)


if __name__ == "__main__":
    main()
