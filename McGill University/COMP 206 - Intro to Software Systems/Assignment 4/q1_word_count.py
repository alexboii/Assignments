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
        # Dictionary of pairs: word->frequency
        freq = {}
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
                # Update frequency values for each word
                for word in words:
                    if word not in freq: freq[word] = 1
                    else: freq[word] = freq[word] + 1
        # Reading done
        # Time to print words and their frequencies
        # First create list of tuples 
        result = [(v, k) for (k, v) in freq.iteritems()]
        # Sort tuples by first element (frequency value)
        result.sort(reverse=True)
        # Print result

        print("-" * 40)
        print("{:>20}  {:<10}".format("Word", "Frequency"))
        for v, k in result:
            print("{:>20}: {:<}".format(k,v) )
        print("-" * 40)

       	# for v, k in result:
           # print("%s:%d" % (k,v))
        # Caught exceptions
    except Exception as e:
        print(e)

if __name__ == "__main__":
    main()
