#!/bin/bash

#ALEX BRATYSHKIN - 260684228

filename="result_text.txt"
imagename="results_image.jpg"
tempfile="temp.txt"

function sort_function(){
	echo "Sort by $1"
	ls -f | grep .dat > $filename 
	while read -r line
	do	
		name2=`cut -d' ' -f $2 < $line`" ""$line"
		echo $name2 >> $tempfile
	done < "$filename"	
	cat $tempfile| sort -k1 -n | cut -d " " -f 2 | cut -f 1 -d '.'  > $filename
	rm $tempfile
	while read -r line
	do
		name="$name""$line".jpg" " 
	done < "$filename"
	convert $name -append $imagename
	cat $filename
	rm $filename
}

if [ "$1" = "alpha" ] 
then
	echo "Sort by alphabetical order"
	ls -f | grep .dat | sort | cut -f 1 -d '.' > $filename
	while read -r line
	do
		name="$name""$line".jpg" " 
	done < "$filename"
	convert $name -append $imagename
	cat $filename
	rm $filename
elif [ "$1" = "weight" ] 
then
	column="1"
	sort_function $1, $column
	
elif [ "$1" = "length" ] 
then
	column="2"
	sort_function $1 $column
else
	echo "Positional parameter is wrong! Enter either alpha, weight or length"
fi



