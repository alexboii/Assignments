<header>
<link rel="stylesheet" href="https://cdn.rawgit.com/konpa/devicon/master/devicon.min.css">
</header> 

#Assignments 

In this repository, you can find a compilation of most of the programming assignments that I have completed over the span of my (not-yet-completed) academic career. Please use this README file to browse through the repository, as I have tried my best to make the list as comprehensive as possible. I have hyperlinked the assignments to their respective location in this folder, and I have also included brief descriptions for each assignment and course. I hope that this reposity will br able to give you an idea of my progression as a developer. 
___

##<div align="center"><img src="http://royalvictoria.mcgill.ca/wp-content/uploads/2014/02/logo_video.png"  width="50%" height="50%" alt="McGill University"></div>

<div align="center"><b>Bachelor of Software Engineering at McGill University. To view the curriculum, please <a href="http://www.mcgill.ca/ece/programs/undergrad/information/se/2015-2016-software-engineering-7-semester-curriculum">click here.</a></b>
</div>

##[COMP 206 - Software Systems](http://www.cs.mcgill.ca/~cs206/)

<i>"Comprehensive overview of programming in C, use of system calls and libraries, debugging and testing of code; use of developmental tools like make, version control systems."</i> 

###[Assignment 1](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%201)&nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/linux-plain.png" width="3%" height="3%">

The purpose of this assignment was to get us accustomed to writing BASH scripts. The [second part of the assignment](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%201/q2.bash) consisted of writing a script that would endlessly open a given list of images in a certain order until manually interrupted. If an image is closed, the script has to reopen it. The [third part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%201/q3.bash) was about sorting animals according to the attributes specified by the user (weight, length, alphabetical). Two outputs had to be produced: one with a sorted list of animal names and another one with a sorted list of images. 

###[Assignment 2](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%202) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/c-plain.png" width="3%" height="3%">

This assignment was an introduction to C and it allowed us to explore the way the language interacts with the Linux terminal. The [first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%202/q1.c) translates a date piped as an argument from the shell to a given input language. The [second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%202/q2.c) was an exercise in ASCII art. It prints a the calendar for a year in different languages given a starting day offset and a maximum number of characters to print out. 

###[Assignment 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/c-plain.png" width="3%" height="3%">

In this assignment, we explored C's dynamic memory allocation in depth. [Part A](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q1a_decrypt.c) of the first question was about decrypting files encoded with the [Caesarean cipher algorithm](https://en.wikipedia.org/wiki/Caesar_cipher), provided the correct key. [Part B](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q1b_crack.c) of the first question cracked down the key of an encrypted text. [The second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q2_text_chat.c) is an implementation of a simple chat, where two users interact through two different terminal windows. [The third question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%203/q3_encrypted_chat.c) combines the concepts from the two questions above to create an encrypted chat client. 

###[Assignment 4](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%204) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/python-plain.png" width="3%" height="3%">

The pedagogical objective of this assignment was to "[learn]  basic  Python  programming  skills  in  domains  where  Python  is  effec-tive, and especially as applied to problems that would be difficult in C." [The first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%204/q1_word_count.py) counts each occurrence of the words from a given input file. [The second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%204/q2_pair_count.py) counts each word pair in a given text. [The third question] implements a very primitive chatbot which constructs its answer based on word associations from one or several input files.   

###[Assignment 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/python-plain.png" width="3%" height="3%">

The pedagogical objective of this assignment was to "[implement] a larger software system that includes components from the web, C libraries written by others, as well as [my] own code written in Python in order to produce a functional integrated system." In the [first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205/q1_image_filter.py), we had to write a Python program with the use of ctypes which would apply filters to an input image. Then, [the second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205/q2_filter_with_history.py) extended the functionality of the first question by implementing an operation history with the help of Python's pickle module, which allowed the user to load, undo and redo filter applications for a given image. [The third question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20206%20-%20Intro%20to%20Software%20Systems/Assignment%205/q3_cgi_filter.py) was simply an implementation of the same program, but adapted to Python CGI. A running web version can be found [here](http://www.cs.mcgill.ca/~abraty/A5Q3_template.html).
##
##[COMP 250 - Introduction to Computer Science](http://www.cs.mcgill.ca/~jeromew/comp250.html)
<i>"An introduction to the design of computer algorithms, including basic data structures, analysis of algorithms, and establishing correctness of programs. Overview of topics in computer science."</i> 

<i>For the most part, the assignments for this class were in written form, although the ones below included parts in which we had to do some coding.</i>

###[Assignment 1](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%201) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

In this assignment, [the programming part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%201/StudentList.java) consisted of solving list-intersection problems by implementing four different algorithms, one which relied on nested for-loops, another one that used binary search, another one which was based on a sort-first approach with parallel pointers, and finally another one that used the merge-and-sort technique. Then, we had to compare the running times of each algorithm in order to familiarize ourselves with the [big O notation](https://en.wikipedia.org/wiki/Big_O_notation).   

###[Assignment 2](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%202) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

The point of this assignment's [programming part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%202/HW2.java) was to empirically demonstrate that iterative functions are much more efficient than recursive ones. We were asked to write an iterative method which computes the factorial of a number, and then two methods, one recurisve and one iterative, which respectively allow for the computation of the input's binomial coefficient. Of course, all of these methods were benchmarked afterwards. 

###[Assignment 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%203) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

The [programming question of this assignment](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%203/MergeSortNonRecursive.java) presents a version of the [Merge sort algorithm] (https://en.wikipedia.org/wiki/Merge_sort) which mimics recursion by implementing a stack to keep track of the value of variables during the execution of recursive methods, in the same way that the [Java Virtual Machine does it](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html). 

###[Assignment 4](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%204) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

For the [first question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%204/expressionTreeNode.java) of this assignment, we were asked to complete two methods, one which evaluated a symbolic expressions, and another one which returned the derivative of a given symbolic expressions. The purpose of this assignment was to familiarize us with [trees](https://en.wikipedia.org/wiki/Tree_(data_structure)) as an abstract data type.   

###[Assignment 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%205) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">


I found the [programming part](https://github.com/alexboii/Assignments/blob/master/McGill%20University/COMP%20250%20-%20Intro%20to%20Computer%20Science/Assignment%205/searchEngine.java) of this assignment to be the most challenging of the course, but at the same time the most interesting; this same sentiment was shared by several of my classmates. In short, this  program is a web search engine which was designed to imitate [Google's PageRank algorithm](https://en.wikipedia.org/wiki/PageRank). It performs graph traversals on [McGill School of Computer Science's website](https://www.cs.mcgill.ca/) with the use of the [depth-first search algorithm](https://en.wikipedia.org/wiki/Depth-first_search), and also provides a method which computes the page rank of each vertex in the aforementioned grap, among many other things.  

##
##[ECSE 211 - Design Principles & Methods](http://www.mcgill.ca/study/2016-2017/courses/ECSE-211)

<i>A course on -- as its name aptly betrays -- design and documentation in which we had to write an exhaustive amount of paperwork, but we also got to build robots using Lego Mindstorm kits and the <a href="http://www.lejos.org" />Lejos API</a>. This is the most dreadful course McGill's ECSE department has to offer. Below you can find the code for each different robot we had to build. The greater chunk of the course was allocated to building (but mostly documenting) a robot that would peform in a final competition. The source code for the final robot can be found <a href="https://github.com/alexboii/DPM-Final-Project"> here</a>.</i> 

###[Lab 1](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%201) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

In this lab, we were required to build a robot which would follow walls through means of two different techniques: [Bang-bang control](https://en.wikipedia.org/wiki/Bang%E2%80%93bang_control) and [Proportional control](https://en.wikipedia.org/wiki/Proportional_control). 

###[Lab 2](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%202) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

Here, we had to implement an odometer for the robot, in addition to an algorithm which would correct the readings on the odometer (and, consequently, the heading of the robot) with the help of a light sensor and black gridlines that were drawn on the floor.   

###[Lab 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%203) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

In this lab, we had to implement a navigation functionality for the robot, i.e. a way of making it travel to specified coordinates. Furthermore, the robot had to avoid obstacles in its path while still keeping the correct heading. 

###[Lab 4](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%204)  &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

The code in this lab accomplishes the task localization so that the robot can know its exact positioning relative to the competition floor. First, it performs an ultrasonic localization by retrieving the readings of the distances between itself and its adjacent walls in order to calibrate the odometer's angle relative to the competition floor. The robot then advances to the first intersection of two grid lines, reads four consecutive black line values and sets its new precise position relative to the board, i.e. the (0, 0) coordinate. 

###[Lab 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20211%20-%20Design%20Principles%20and%20Methods/Lab%205)  &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

This lab could be considered a synthesis of the functionalities implemented in the previous four labs. We had to build a robot which would be capable of localizing, finding a blue Styrofoam block, picking it up and bringing it to a designated drop-off zone, all while ensuring that it avoids all obstacles in its way. 

##
##[ECSE 221 - Intro to Computer Engineering](https://www.mcgill.ca/study/2015-2016/courses/ecse-221) 

<i>This course introduces the student to the low-level facet of computers in general. Most of the assignments in this class were either written or concerned the design of combinational and sequential circuits with the very outdated <a href="http://designworkssolutions.com/logicworks-5-windows/">Logicworks</a> design tool.</i>

###[Assignment 1](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%201)  &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/c-plain.png" width="3%" height="3%">

This [program](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%201/num_2_bin.c) was my first exposure to the C programming language. Its purpose is very simple: it converts a given decimal number to its binary and hexadecimal equivalent. 

###[Assignment 4](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%204)  &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/tools-plain.png" width="3%" height="3%">

The [second question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%204/BRATYSHKIN_ALEXANDER_Q2) in this assignment introduced us to the concept of function calls in the MIPS assembly language by writing code that, when executed, would return the factorial of a given input. The [third question](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20221%20-%20Intro%20to%20Computer%20Engineering/Assignment%204/BRATYSHKIN_ALEXANDER_Q3) was slightly trickier, since we had to create a program which would evaluate expressions in the right order of operations. 

##
##[ECSE 321 - Intro to Software Engineering](http://www.mcgill.ca/study/2016-2017/courses/ECSE-321) 

<i>The main objective of this course was "to introduce students to the process of software engineering". In this course, we also had a semester-long project in which we had to design a management system for food trucks. The source code to its Android implementation can be found here, and the source code to its Swing implementation can be found here. A lot of the assignments on this course focused on diagrams and written responses, though there was also a decent portion of programming involved.</i>

###[Assignment 1](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%201)  &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%"> <img src="https://github.com/alexboii/Assignments/blob/master/Icons/android-plain.png" width="3%" height="3%"> <img src="https://github.com/alexboii/Assignments/blob/master/Icons/php-plain.png" width="3%" height="3%"> 


Here, we had to build an event registration application which allows the user to create participants and events, as well registering the created participants to the created events. The assignment was an introductory exercise to concepts such as unit testing, persistence and domain modeling, as well as the [MVC architectural style](https://www.tutorialspoint.com/struts_2/basic_mvc_architecture.htm). The program was built on multiple platforms, including [Java Swing](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%201/Swing%20Application/EventRegistration), [Android]( https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%201/Android%20Application) and [PHP](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%201/Web%20Application).     


###[Assignment 3](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%203)  &nbsp;  <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

In this assignment, we had to apply the [Strategy design](https://sourcemaking.com/design_patterns/strategy) pattern to a transaction program in which customers had three different ways of earning loyalty points.

###[Assignment 5](https://github.com/alexboii/Assignments/tree/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%205) &nbsp;                  <img src="https://github.com/alexboii/Assignments/blob/master/Icons/apache-plain.png" width="3%" height="3%">

The purpose of this assignment was to make us understand how build tools work. Here, we were required to create a [build.xml file](https://github.com/alexboii/Assignments/blob/master/McGill%20University/ECSE%20321%20-%20Intro%20to%20Software%20Engineering/Assignment%205/build.xml) using Apache Ant "to automate the software build process" for a source code that was provided to us. The build.xml had to create a build directory, compile the sources files, produce a .jar file, run all tests for the code, clean up and then execute the program. 

___

##<div align="center"><img src="https://www.collegesinstitutes.ca/wp-content/uploads/2015/11/VanierWordmarkRGB.jpg"  width="40%" height="40%" alt="Vanier College"></div>

<div align="center"><b>DCS (DEC) in Computer Science & Mathematics from Vanier College. To view the curriculum, please <a href="http://www.vaniercollege.qc.ca/advising/files/200C0-grid-Fall-2016.pdf">click here.</a></b>
</div>

##[420-202 - Data Structures & Object-Oriented Programming](http://www.vaniercollege.qc.ca/csm/courses/420-202-data-structures-object-oriented-programming/) 

<i>The main objective of this course was to cover topics in programming such as arrays, writing classes, inheritance, more advanced input/output, exceptions, recursion. It introduced the students to data structures emphasizing pointers and linked lists. A lot of content from this course overlapped with the COMP 250 class I took at McGill.</i>

###[Assignment 1](https://github.com/alexboii/Assignments/tree/master/Vanier%20College/420-202%20%E2%80%93%20Data%20Structures%20%26%20Object-Oriented%20Programming/Assignment%201) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

In this assignment, we were asked to model an imaginary bag of a fixed number of things. This bag could contain anywhere from book titles to simple integers. Several manipulations could be casted on the contents of this bag: a bag can get, set, reverse, and print the items in the bag. I believe this assignment was intended to better make us understand arrays and objects. 

###[Assignment 2](https://github.com/alexboii/Assignments/tree/master/Vanier%20College/420-202%20%E2%80%93%20Data%20Structures%20%26%20Object-Oriented%20Programming/Assignment%202) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

This assignment introduced us to key OOP concepts such as encapsulation, inheritance and polymorphism. We were asked to  implement a simple banking system that consisted of two types of bank accounts: checking and savings. Both types differed in certian criteria but their overall behaviour was similar. 

###[Assignment 3](https://github.com/alexboii/Assignments/tree/master/Vanier%20College/420-202%20%E2%80%93%20Data%20Structures%20%26%20Object-Oriented%20Programming/Assignment%203) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

The main purpose of this assignment was to let us gain experience working with Java's [ArrayList](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html) and [LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html) classes. We were required to write an interactive program that allowed the user to create and manage a contacts list.

###[Assignment 4](https://github.com/alexboii/Assignments/tree/master/Vanier%20College/420-202%20%E2%80%93%20Data%20Structures%20%26%20Object-Oriented%20Programming/Assignment%204) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

This assignment tested us on our understanding of stacks and queues. We had to write a program that determined whether the brackets in a given expression were well-balanced, i.e. they are properly matched and nested

###[Assignment 5](https://github.com/alexboii/Assignments/tree/master/Vanier%20College/420-202%20%E2%80%93%20Data%20Structures%20%26%20Object-Oriented%20Programming/Assignment%204) &nbsp; <img src="https://github.com/alexboii/Assignments/blob/master/Icons/java-plain.png" width="3%" height="3%">

In this assignment, we were able to further explore the concept of recursion. We were asked to implement recursive solutions to a given set of problems which included exercises such as writing a method that computed multiplication recursively, writing a recursive boolean method which searched an array for a specified value, coming up with a recursive way of reversing a string, writing a recursive method that returned the biggest element of an array, coding a recursive palindrome detector and, finally, writing a method that recursively counted the frequency of each character in a character array. 

##
##[420-203 - Program Development for a Graphical Environment](http://www.vaniercollege.qc.ca/csm/courses/420-203-program-development-for-a-graphical-environment/) 

<i>The main objective of this course was to introduce its students to the fundamentals for designing and developing graphical user interface (GUI) applications with the help of the Java Swing widget toolkit and the JavaFX software platform. </i>



