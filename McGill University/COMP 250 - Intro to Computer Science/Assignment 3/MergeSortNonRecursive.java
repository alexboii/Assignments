// ALEXANDER BRATYSHKIN
// 260684228

import java.util.*;
import java.io.*;

class ProgramFrame {

	int A[];
	int start;
	int stop;
	int mid;
	int PC;

	public ProgramFrame(int myA[], int myStart, int myStop) {
		A = myA;
		start = myStart;
		stop = myStop;
		mid = 0;
		PC = 1;
	}

	// returns a String describing the content of the object \
	public String toString() {
		return "ProgramFrame: A = " + Arrays.toString(A) + ", start = " + start + ", stop = " + stop + ", mid = " + mid
				+ ", PC = " + PC;
	}

}

class MergeSortNonRecursive {

	static Stack<ProgramFrame> tempStack;

	// this implement the merge algorithm seen in class. Feel free to call it.
	public static void merge(int A[], int start, int mid, int stop) {
		int index1 = start;
		int index2 = mid + 1;
		int tmp[] = new int[A.length];
		int indexTmp = start;

		while (indexTmp <= stop) {
			if (index1 <= mid && (index2 > stop || A[index1] <= A[index2])) {
				tmp[indexTmp] = A[index1];
				index1++;
			} else {
				if (index2 <= stop && (index1 > mid || A[index2] <= A[index1])) {
					tmp[indexTmp] = A[index2];
					index2++;
				}
			}
			indexTmp++;
		}
		for (int i = start; i <= stop; i++)
			A[i] = tmp[i];
	}

	/**
	 * Non recursively implements the algorithm of MergeSort seen in class
	 * with the use of stacks in order to imitate the mechanism behind the JVM
	 * 
	 * @param A array that you wish to sort 
	 */
	public static void mergeSort(int A[]) {

		tempStack = new Stack<ProgramFrame>();

		ProgramFrame tempFrame = new ProgramFrame(A, 0, A.length - 1);

		tempStack.push(tempFrame);

		while (!tempStack.empty()) {

			System.out.println(tempStack); // debug statement

			if (tempStack.peek().PC == 1) { // if ( start < stop ) then

				tempStack.peek().PC++;

				if (tempStack.peek().start >= tempStack.peek().stop) {
					tempStack.pop();
					if (tempStack.empty()) break;
				}
			}

			if (tempStack.peek().PC == 2) { // mid=(stop+start)/2
				tempStack.peek().mid = ((tempStack.peek().stop + tempStack.peek().start) / 2);
				++tempStack.peek().PC;
			}

			if (tempStack.peek().PC == 3) { // mergeSort(A, start, mid)
				++tempStack.peek().PC;
				tempFrame = new ProgramFrame(A, tempStack.peek().start, tempStack.peek().mid);
				tempStack.push(tempFrame);
				continue;
			}

			if (tempStack.peek().PC == 4) { // mergeSort(A, mid+1, stop)
				++tempStack.peek().PC;
				tempFrame = new ProgramFrame(A, tempStack.peek().mid + 1, tempStack.peek().stop);
				tempStack.push(tempFrame);
				continue;
			}

			if (tempStack.peek().PC == 5) {	// merge(A, start, mid, stop)
				MergeSortNonRecursive.merge(A, tempStack.peek().start, tempStack.peek().mid, tempStack.peek().stop);
				tempStack.pop();
				if (tempStack.empty())
					break;
			}
		}
	}

	public static void main(String args[]) throws Exception {

		// just for testing purposes
		int myArray[] = { 4, 6, 8, 1, 3, 9, 5, 7, 6, 4, 1, 8, 7, 5 };

		mergeSort(myArray);

		System.out.println("Sorted array is: " + Arrays.toString(myArray));
	}
}
