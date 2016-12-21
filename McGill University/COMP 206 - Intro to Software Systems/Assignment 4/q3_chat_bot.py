# -*- coding: utf-8 -*-
# Python 2.7
# Alexander Bratyshkin
# 260684228

import sys, os
import random

def print_usage():
    print("Usage: ./" + os.path.basename(__file__) + " <input file1> <input file2> ... <input fileN>")

# Return true if word ends with stop symbol
def isLastWord(word):
    stopchars= ".!?"
    i, result = 0, False	
    while i < len(stopchars) and result == False:
        if word.endswith(stopchars[i]): result = True
    return result


# Split user string strip each item and return list of words
def splitForWords(line):
    # Characters that we want to remove from words
    badchars = "`~@#$%^&*()_+{}:\"|<>\\/,;'[]-=" # Note that we must save stop symbols like: ".!?"
    stopchars= ".!?"
    # Replace all '-' symbols with space symbol
    line = line.replace('-', ' ')
    # Get list of words
    words = line.split()
    # Map strip function for each word in words list to get list of 'clear' words
    words = list(map(lambda x : str(x).lower().strip(badchars), words))
    # Save words as pairs: string, boolean
    words = [(w, False) for w in words]
    # where string is 'word' and boolean is stop marker 'is word last word in sentence'
    for i in range(len(words)):
        # Get next pair word:stop_marker
        wrd, stop = words[i]
        # Determine if this is last word
        isLast = False
        for s in stopchars: 
            if wrd.endswith(s): isLast = True
        # Upade list with new word without stop symbols and with new stop_marker
        words[i] = (wrd.strip(stopchars), isLast)
    words = [w for w in words if w[0].isalpha()]
    # Return result list of words
    return words
    

# Read words from file. Return list of words (file order words and list order will be same)
def readWords(filename, allWords = []): # Append new words to allWords list if second argument passed
    # Open text file
    with open(filename) as file:
        # Read line by line
        for line in file:
            words = splitForWords(line)
            # Append new chunk of words ot list
            for w in words: allWords.append(w)
    # Return result list
    return allWords

def getResponse(allWords, pairs, query):
    
    # Split user query
    words = splitForWords(query)
    resp = []
    # Get last word from user query
    QN = words[-1][0]
    index = -1

    if (QN, True) in allWords and QN != allWords[-1][0]: 
        index = allWords.index((QN, True)) + 1
    elif (QN, False) in allWords and QN != allWords[-1][0]:
        index = allWords.index((QN, False)) + 1
    else:
        index = random.randint(0, len(allWords) - 1)
    resp.append(allWords[index][0])
    words_count = 1

    while words_count < 20:
        words_count += 1
        index += 1
        resp.append(allWords[index][0])
        # If we found stop pair
        if (resp[-2], resp[-1], True) in pairs: break
        # Stop if we reach end of the text
        if index + 1 >= len(allWords): break
    # Capitalize first word
    # Last word should ends with period
    # Return response as single string
    return " ".join(resp).capitalize() + "."


def main():
    # Check user command line arguments
    if len(sys.argv) < 2: # At least 1 filename should be passed as command line argument 
        print_usage()
        return
    # 
    try:
        # List of words and word pairs
        allWords, pairs = [], []
        for i in range(1, len(sys.argv)):
            allWords = readWords(sys.argv[i], allWords)
        # Generate pairs
        for i in range(len(allWords) - 1):
            # Get pairs for current and next word
            word1, word2 = allWords[i], allWords[i + 1]
            # Get word and stop_marker for both words
            wrd1, isstop1 = word1
            wrd2, isstop2 = word2
            # If second word is last word then isstop2 must be true and this is mean that result pair will be stop pair
            pairs.append((wrd1, wrd2, isstop2))
        #print("\n".join(list(map(str, allWords))))
        #print('--' * 40)
        #print("\n".join(list(map(str, pairs))))
        # User query
        query = ' '
        while len(query) != 0: #
            query = raw_input("Send: ")
            if len(query) == 0: continue
            response = getResponse(allWords, pairs, query)
            print("Received: %s" % response)
        # Caught exceptions
    except Exception as e:
        print(e)

if __name__ == "__main__":
    main()
