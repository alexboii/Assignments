#Assignments 

In this repository, you can find a compilation of most of the programming assignments that I have completed over the span of my (not-yet-completed) academic career. Please use this README file to browse through the repository, as I have tried my best to make the list as comprehensive as possible. I have hyperlinked the assignments to their respective location in this folder, and I have also included brief descriptions for each assignment and course. I hope that this reposity will br able to give you an idea of my progression as a developer. 

___

##<div align="center"><img src="http://royalvictoria.mcgill.ca/wp-content/uploads/2014/02/logo_video.png"  width="50%" height="50%" alt="Computer Hope"></div>

<div align="center"><b>Bachelor of Software Engineering at McGill University. To view the curriculum, please <a href="http://www.mcgill.ca/ece/programs/undergrad/information/se/2015-2016-software-engineering-7-semester-curriculum">click here.</a></b>
</div>

##[COMP 206 - Software Systems](http://www.cs.mcgill.ca/~cs206/)

<i>"Comprehensive overview of programming in C, use of system calls and libraries, debugging and testing of code; use of developmental tools like make, version control systems."</i> 

###[Assignment 1](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%201) [BASH] 

The purpose of this assignment was to get us accustomed to writing BASH scripts. The [second part of the assignment](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%201/q2.bash) consisted of writing a script that would endlessly open a given list of images in a certain order until manually interrupted. If an image is closed, the script has to reopen it. The [third part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%201/q3.bash) was about sorting animals according to the attributes specified by the user (weight, length, alphabetical). Two outputs had to be produced: one with a sorted list of animal names and another one with a sorted list of images. 

###[Assignment 2](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%202) [C]

This assignment was an introduction to C and it allowed us to explore the way the language interacts with the Linux terminal. The [first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%202/q1.c) translates a date piped as an argument from the shell to a given input language. The [second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%202/q2.c) was an exercise in ASCII art. It prints a the calendar for a year in different languages given a starting day offset and a maximum number of characters to print out. 

###[Assignment 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203) [C]

In this assignment, we explored C's dynamic memory allocation in depth. [Part A](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q1a_decrypt.c) of the first question was about decrypting files encoded with the [Caesarean cipher algorithm](https://en.wikipedia.org/wiki/Caesar_cipher), provided the correct key. [Part B](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q1b_crack.c) of the first question cracked down the key of an encrypted text. [The second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q2_text_chat.c) is an implementation of a simple chat, where two users interact through two different terminal windows. [The third question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q3_encrypted_chat.c) combines the concepts from the two questions above to create an encrypted chat client. 

###[Assignment 4](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%204) [PYTHON]

The pedagogical objective of this assignment was to "[learn]  basic  Python  programming  skills  in  domains  where  Python  is  effec-tive, and especially as applied to problems that would be difficult in C." [The first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%204/q1_word_count.py) counts each occurrence of the words from a given input file. [The second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%204/q2_pair_count.py) counts each word pair in a given text. [The third question] implements a very primitive chatbot which constructs its answer based on word associations from one or several input files.   

###[Assignment 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205) [PYTHON / CYTHON]

The pedagogical objective of this assignment was to "[implement] a larger software system that includes components from the web, C libraries written by others, as well as [my] own code written in Python in order to produce a functional integrated system." In the [first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205/q1_image_filter.py), we had to write a Python program with the use of ctypes which would apply filters to an input image. Then, [the second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205/q2_filter_with_history.py) extended the functionality of the first question by implementing an operation history with the help of Python's pickle module, which allowed the user to load, undo and redo filter applications for a given image. [The third question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205/q3_cgi_filter.py) was simply an implementation of the same program, but adapted to Python CGI. A running web version can be found [here](http://www.cs.mcgill.ca/~abraty/A5Q3_template.html).
##
##[COMP 250 - Introduction to Computer Science](http://www.cs.mcgill.ca/~jeromew/comp250.html)
<i>"An introduction to the design of computer algorithms, including basic data structures, analysis of algorithms, and establishing correctness of programs. Overview of topics in computer science."</i> 

<i>For the most part, the assignments for this class were in written form, although the ones below included parts in which we had to do some coding.</i>

###[Assignment 1](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%201/StudentList.java) [JAVA]

In this assignment, [the programming part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%201/StudentList.java) consisted of solving list-intersection problems by implementing four different algorithms, one which relied on nested for-loops, another one that used binary search, another one which was based on a sort-first approach with parallel pointers, and finally another one that used the merge-and-sort technique. Then, we had to compare the running times of each algorithm in order to familiarize ourselves with the [big O notation](https://en.wikipedia.org/wiki/Big_O_notation).   

###[Assignment 2](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%202) [JAVA]

The point of this assignment's [programming part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%202/HW2.java) was to empirically demonstrate that iterative functions are much more efficient than recursive ones. We were asked to write an iterative method which computes the factorial of a number, and then two methods, one recurisve and one iterative, which respectively allow for the computation of the input's binomial coefficient. Of course, all of these methods were benchmarked afterwards. 

###[Assignment 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%203) [JAVA]

The [programming question of this assignment](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%203/MergeSortNonRecursive.java) presents a version of the [Merge sort algorithm] (https://en.wikipedia.org/wiki/Merge_sort) which mimics recursion by implementing a stack to keep track of the value of variables during the execution of recursive methods, in the same way that the [Java Virtual Machine does it](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html). 

###[Assignment 4](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%204) [JAVA]

For the [first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%204/expressionTreeNode.java) of this assignment, we were asked to complete two methods, one which evaluated a symbolic expressions, and another one which returned the derivative of a given symbolic expressions. The purpose of this assignment was to familiarize us with [trees](https://en.wikipedia.org/wiki/Tree_(data_structure)) as an abstract data type.   

###[Assignment 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%205) [JAVA]

I found the [programming part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%205/searchEngine.java) of this assignment to be the most challenging of the course, but at the same time the most interesting; this same sentiment was shared by several of my classmates. In short, this  program is a web search engine which was designed to imitate [Google's PageRank algorithm](https://en.wikipedia.org/wiki/PageRank). It performs graph traversals on [McGill School of Computer Science's website](https://www.cs.mcgill.ca/) with the use of the [depth-first search algorithm](https://en.wikipedia.org/wiki/Depth-first_search), and also provides a method which computes the page rank of each vertex in the aforementioned grap, among many other things.  

##
##[ECSE 211 - Design Principles & Methods](http://www.mcgill.ca/study/2016-2017/courses/ECSE-211)

<i>A course on -- as its name aptly betrays -- design and documentation in which we had to write an exhaustive amount of paperwork, but we also got to build robots using Lego Mindstorm kits and the <a href="http://www.lejos.org" />Lejos API</a>. This is the most dreadful course McGill's ECSE department has to offer. Below you can find the code for each different robot we had to build. The greater chunk of the course was allocated to building (but mostly documenting) a robot that would peform in a final competition. The source code for the final robot can be found <a href="https://github.com/alexboii/DPM-Final-Project"> here</a>.</i> 

###[Lab 1](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%201) [JAVA]

In this lab, we were required to build a robot which would follow walls through means of two different techniques: [Bang-bang control](https://en.wikipedia.org/wiki/Bang%E2%80%93bang_control) and [Proportional control](https://en.wikipedia.org/wiki/Proportional_control). 

###[Lab 2](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%202) [JAVA]

Here, we had to implement an odometer for the robot, in addition to an algorithm which would correct the readings on the odometer (and, consequently, the heading of the robot) with the help of a light sensor and black gridlines that were drawn on the floor.   

###[Lab 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%203) [JAVA]

In this lab, we had to implement a navigation functionality for the robot, i.e. a way of making it travel to specified coordinates. Furthermore, the robot had to avoid obstacles in its path while still keeping the correct heading. 

###[Lab 4](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%204) [JAVA]

The code in this lab accomplishes the task localization so that the robot can know its exact positioning relative to the competition floor. First, it performs an ultrasonic localization by retrieving the readings of the distances between itself and its adjacent walls in order to calibrate the odometer's angle relative to the competition floor. The robot then advances to the first intersection of two grid lines, reads four consecutive black line values and sets its new precise position relative to the board, i.e. the (0, 0) coordinate. 

###[Lab 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%205) [JAVA]

This lab could be considered a synthesis of the functionalities implemented in the previous four labs. We had to build a robot which would be capable of localizing, finding a blue Styrofoam block, picking it up and bringing it to a designated drop-off zone, all while ensuring that it avoids all obstacles in its way. 

##
###[ECSE 221 - Intro to Computer Engineering](https://www.mcgill.ca/study/2015-2016/courses/ecse-221) 

<i>This course introduces the student to the low-level facet of computers in general. Most of the assignments in this class were either written or concerned the design of combinational and sequential circuits with the very outdated <a href="http://designworkssolutions.com/logicworks-5-windows/">Logicworks</a> design tool.</i>

###[Assignment 1](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%201) [C]

This [program](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%201/num_2_bin.c) was my first exposure to the C programming language. Its purpose is very simple: it converts a given decimal number to its binary and hexadecimal equivalent. 

###[Assignment 2](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%204) [ASSEMBLY]

The [second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%204/BRATYSHKIN_ALEXANDER_Q2) in this assignment introduced us to the concept of function calls in the MIPS assembly language by writing code that, when executed, would return the factorial of a given input. The [third question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%204/BRATYSHKIN_ALEXANDER_Q3) was slightly trickier, since we had to create a program which would evaluate expressions in the right order of operations. 
