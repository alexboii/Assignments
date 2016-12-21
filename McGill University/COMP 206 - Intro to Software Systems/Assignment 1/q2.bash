#!/bin/bash

#ALEX BRATYSHKIN - 260684228

re='^[0-9]+([.][0-9]+)?$' #REGEX

file_name=`ls -f | grep .jpg`
words=`ls -1 | grep .jpg | wc -l`

if ! [[ "$1"  =~ $re ]] ; then
	echo You must enter a number as your argument

else
	while [ 1 ]
	do
	
	file_count=`ps | grep eog | wc -l`

		    while [   "$file_count" -lt "$1" ]; do

			this_file_name=`echo $file_name | cut -d' ' -f1`
			file_name="`echo $file_name | cut -d' ' -f2-` ${this_file_name}"

			echo Opening viewer "for" $this_file_name
			eog -n $this_file_name &

			file_count=`ps | grep eog | wc -l`
			echo There are currently "$file_count" processes open
			
		done
	
	done
fi


