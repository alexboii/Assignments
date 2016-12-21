# -*- coding: utf-8 -*-
# Python 2.7
# Alexander Bratyshkin
# 260684228

import sys, os

def print_usage():
    print("Usage: ./" + os.path.basename(__file__) + " <input file>")

def main():
    # Check user command line arguments
    if len(sys.argv) != 2:
        print_usage()
        return
    # 
    try:
        # Characters that we want to remove from words
        badchars = "`~!@#$%^&*()_+{}:\"|<>?\\/,.;'[]-="
        # Dictionary of pairs: (word pair)->frequency
        freq = {}
        # List of words
        allWords = []
        # Open text file
        with open(sys.argv[1]) as file:
            # Read line by line
            for line in file:
                # Replace all '-' symbols with space symbol
                line = line.replace('-', ' ')
                # Get list of words
                words = line.split()
                # Map strip function for each word in words list to get list of 'clear' words
                words = list(map(lambda x : str(x).lower().strip(badchars), words))
                # Save only alphabetical
                words = [w for w in words if w.isalpha()]
                # Append new chunk of words ot list
                for w in words: allWords.append(w)
        # Words list complete
        # Generate pairs
        pairs = [(allWords[i], allWords[i + 1]) for i in range(len(allWords) - 1)]
        # And calculate frequencies
        for p in pairs:
            if p not in freq: freq[p] = 1
            else: freq[p] = freq[p] + 1
        # Time to print word pairs and their frequencies
        # First create list of tuples 
        result = [(v, k) for (k, v) in freq.iteritems()]
        # Sort tuples by first element (frequency value)
        result.sort(reverse=True)
        # Print result
        for v, k in result:
            print("%s-%s:%d" % (k[0], k[1], v))
        # Caught exceptions
    except Exception as e:
        print(e)

if __name__ == "__main__":
    main()
