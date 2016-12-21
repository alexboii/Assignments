// COMP 250 - ASSIGNMENT #1

// NAME: Alexander Bratyshkin
// STUDENT ID: 260684228

import java.io.*;
import java.util.*;

class StudentList {
	int studentID[];
	int numberOfStudents;
	String courseName;

	// A constructor that reads a StudentList from the given fileName and
	// assigns it the given courseName
	public StudentList(String fileName, String course) {
		String line;
		int tempID[] = new int[4000000]; // this will only work if the number of
											// students is less than 4000000.
		numberOfStudents = 0;
		courseName = course;
		BufferedReader myFile;
		try {
			myFile = new BufferedReader(new FileReader(fileName));

			while ((line = myFile.readLine()) != null) {
				tempID[numberOfStudents] = Integer.parseInt(line);
				numberOfStudents++;
			}
			studentID = new int[numberOfStudents];
			for (int i = 0; i < numberOfStudents; i++) {
				studentID[i] = tempID[i];
			}
		} catch (Exception e) {
			System.out.println("Can't find file " + fileName);
		}

	} // __2015__

	// This method produces a String containing the information about a
	// StudentList.
	public String toString() {
		return ("Course name: " + courseName + "\n" + "Number of students: "
				+ numberOfStudents + "Student IDs:" + Arrays
					.toString(studentID));
	}

	// A copy constructor that copies the content original StudentList
	// ranging from minStudentIndex to maxStudentIndex inclusively.
	// Used for sort();
	public StudentList(StudentList original, int minStudentIndex,
			int maxStudentIndex) {
		numberOfStudents = maxStudentIndex - minStudentIndex + 1;
		courseName = original.courseName;
		studentID = new int[original.numberOfStudents];
		for (int i = minStudentIndex; i <= maxStudentIndex; i++) {
			studentID[i - minStudentIndex] = original.studentID[i];
		}
	}

	// A constructor that generates a random student list of the given size and
	// assigns it the given courseName
	public StudentList(int size, String course) {
		int IDrange = 2 * size;
		studentID = new int[size];
		boolean[] usedID = new boolean[IDrange];
		for (int i = 0; i < IDrange; i++)
			usedID[i] = false;
		for (int i = 0; i < size; i++) {
			int t;
			do {
				t = (int) (Math.random() * IDrange);
			} while (usedID[t]);
			usedID[t] = true;
			studentID[i] = t;
		}
		courseName = course;
		numberOfStudents = size;
	}

	// Sorts a student list using the MergeSort algorithm
	public void sort() {
		if (numberOfStudents <= 1)
			return;
		StudentList left = new StudentList(this, 0, numberOfStudents / 2 - 1); // the
																				// left
																				// half
																				// of
																				// the
																				// list
		StudentList right = new StudentList(this, numberOfStudents / 2,
				numberOfStudents - 1); // the right half of the list
		left.sort(); // recursively sort the left and right halves
		right.sort();

		// now merge the two sorted halves
		int tmpIndex = 0;
		int indexLeft = 0;
		int indexRight = 0;
		while (tmpIndex < numberOfStudents) {
			if (indexRight >= right.numberOfStudents
					|| (indexLeft < left.numberOfStudents && left.studentID[indexLeft] <= right.studentID[indexRight])) {
				studentID[tmpIndex] = left.studentID[indexLeft];
				indexLeft++;
			} else {
				studentID[tmpIndex] = right.studentID[indexRight];
				indexRight++;
			}
			tmpIndex++;
		}
	}

	/**
	 * Algorithm comparing each element of one StudentList to each elemnt of
	 * another StudentList
	 * 
	 * @param L1 first StudentList
	 * @param L2 second StudentList
	 * @return Number of intersections
	 */
	public static int intersectionSizeNestedLoops(StudentList L1, StudentList L2) {
		int inter = 0; // initialize counter

		for (int i = 0; i < L1.numberOfStudents; i++) {

			for (int j = 0; j < L2.numberOfStudents; j++) {

				if (L1.studentID[i] == L2.studentID[j]) {
					inter++;
				}
			}

		}

		return inter;
	}

	// This algorithm takes as input a sorted array of integers called
	// mySortedArray, the number of elements it contains, and the student ID
	// number to look for
	// It returns true if the array contains an element equal to ID, and false
	// otherwise.
	public static boolean myBinarySearch(int mySortedArray[],
			int numberOfStudents, int ID) {

		int left = 0;
		int right = numberOfStudents;
		int mid;

		while (right > left + 1) {
			mid = (left + right) / 2;
			if (mySortedArray[mid] > ID) {
				right = mid;
			} else {
				left = mid;
			}
		}

		if (mySortedArray[left] == ID) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Algorithm which first sorts a StudentList, and then uses myBinarySearch()
	 * method to compare target values of first StudentList with elements from
	 * sorted second studentList
	 * 
	 * @param L1 first StudentList
	 * @param L2 second StudentList
	 * @return Number of intersections
	 */
	public static int intersectionSizeBinarySearch(StudentList L1,
			StudentList L2) {
		int inter = 0;

		L2.sort();

		for (int i = 0; i < L1.numberOfStudents; i++) {
			if (myBinarySearch(L2.studentID, L2.numberOfStudents,
					L1.studentID[i])) {

				inter++;
			}

		}
		return inter;
	}

	/**
	 * Algorithm which uses pointers to compare the number of intersections
	 * between two sorted StudentLists
	 * 
	 * @param L1 first StudentList
	 * @param L2 second StudentList
	 * @return Number of intersections
	 */
	public static int intersectionSizeSortAndParallelPointers(StudentList L1,
			StudentList L2) {
		int inter = 0;
		L1.sort();
		L2.sort();
		int pointerA = 0, pointerB = 0;

		while (pointerA < L1.numberOfStudents && pointerB < L2.numberOfStudents) {
			if (L1.studentID[pointerA] == L2.studentID[pointerB]) {
				inter++;
				pointerA++;
				pointerB++;

			} else if (L1.studentID[pointerA] < L2.studentID[pointerB]) {
				pointerA++;

			} else {
				pointerB++;
			}

		}

		return inter;
	}

	/**
	 * Algorithm which merges two StudentLIsts into one, sorts the new merged
	 * StudentList and compares adjacent indexes with the help of a pointer
	 * 
	 * @param L1 first StudentList
	 * @param L2 second StudentList
	 * @return Number of intersections
	 */
	public static int intersectionSizeMergeAndSort(StudentList L1,
			StudentList L2) {

		int pointer = 0, inter = 0;
		int totalStudents = L1.numberOfStudents + L2.numberOfStudents;

		StudentList mergedList = new StudentList(totalStudents, "Merged List");

		for (int i = 0; i <= L1.numberOfStudents - 1; i++) {
			mergedList.studentID[i] = L1.studentID[i];

		}

		for (int j = 0; j <= L2.numberOfStudents - 1; j++) {
			mergedList.studentID[j + L1.numberOfStudents] = L2.studentID[j];
		}

		mergedList.sort();

		while (pointer < (totalStudents - 1)) {
			if (mergedList.studentID[pointer] == mergedList.studentID[pointer + 1]) {
				inter++;
				pointer = pointer + 2;
			} else {
				pointer++;
			}

		}

		return inter;
	}

	/* The main method */
	/*
	 * Write code here to test your methods, and evaluate the running time of
	 * each.
	 */
	/* This method will not be marked */
	public static void main(String args[]) throws Exception {

		StudentList firstList;
		StudentList secondList;

		// This is how to read lists from files. Useful for debugging.

		// firstList=new StudentList("COMP250.txt",
		// "COMP250 - Introduction to Computer Science");
		// secondList=new StudentList("MATH240.txt",
		// "MATH240 - Discrete Mathematics");

		// get the time before starting the intersections
		long startTime = System.nanoTime();

		// repeat the process a certain number of times, to make more accurate
		// average measurements.
		int numberRepetitions = 5;
		for (int rep = 0; rep < numberRepetitions; rep++) {

			firstList = new StudentList(32000,
					"COMP250 - Introduction to Computer Science");
			secondList = new StudentList(1024000,
					"MATH240 - Discrete Mathematics");

			// print the two lists, for future debugging purposes
			// System.out.println(firstList);
			// System.out.println(secondList);

			// run the intersection method
			 int intersection = StudentList.intersectionSizeNestedLoops(
			 firstList, secondList);
			 System.out.println("The intersection size is: (1) " +
			 intersection);

			// int intersection2 = StudentList.intersectionSizeBinarySearch(
			// firstList, secondList);
			// System.out.println("The intersection size is: (2) " +
			// intersection2);

			// int intersection3 =
			// StudentList.intersectionSizeSortAndParallelPointers(
			// firstList, secondList);
			// System.out
			// .println("The intersection size is: (3) " + intersection3);

			// int intersection4 = StudentList.intersectionSizeMergeAndSort(
			// firstList, secondList);
			// System.out
			// .println("The intersection size is: (4) " + intersection4);
		}

		// get the time after the intersection
		long endTime = System.nanoTime();

		System.out.println("/nRunning time: " + (float) (endTime - startTime)
				/ numberRepetitions + " nanoseconds");
	}
}
