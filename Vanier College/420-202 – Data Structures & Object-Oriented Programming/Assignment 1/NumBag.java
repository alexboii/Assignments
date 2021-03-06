/**
  * This class helps us model a bag that contains an assigned set of numbers and length.
  * The bags, of course, will be set in forms of arrays, to help us familiarize with the concept.
  * The purpose of this program, contrary to "IntBag.java," is to make us understand the functioning
  * of different data types, and how memory is stored.
  * This class requires the summoning of class "Num," which is provided separately.
  * All of the elements of the array will be of the same data type "Num."
  * Also, the class can perform tasks such as get, set, reverse, capacity finding the max or min values,
  * finding the index which holds the max and min values in the array, and displaying the elements
  * of the array.
  *
  * @author Alexander Bratyshkin
  */

public class NumBag
{
	 /**
	   * This instance field holds a reference to an array of type Num.
	   * There is no need to store the capacity,
	   * because it will be done through the object.
	   */

	private Num[] data;

	 /**
	   * Summons the scanner class.
	   */

	java.util.Scanner input = new java.util.Scanner(System.in);

	 /**
	   * Summons the constructor NumBag with
	   * parameter equal to 5.
	   */

	public NumBag()
	{
		this(5);

	}

	 /**
	   * Sets the length of the array.
	   * Assigns values to the indexes of the array.
	   * @param n The number of indexes the user enters.
	   */

	public NumBag(int n)
	{

		data = new Num[n];
		System.out.println("Enter " + n + " integers");
		if (n<=0)
		{
			System.out.println("Warning: your supplied value is negative or equal to zero!");
			System.out.println("The value is 5 now.");
			n = 5;
		}

		else
		{
			for(int i =0; i <n; i++)
			{
				System.out.print("Value " + (i+1) + "? ");
				data[i] = new Num(input.nextInt());
			}
		}

	 /**
	   * Creates a NumBag from an array.
	   * @param arr The definition of the array.
	   */

	}

	public NumBag(Num[] arr)
	{
		data = new Num[arr.length];
	 	this.data = arr;

	}

	 /**
	   * Creates another bag (otherBag) from the NumBag.
	   * @param otherBag Creates otherBag from method NumBag.
	   */

	public NumBag(NumBag otherBag)
	{
		this.data = new Num[otherBag.data.length];
		this.data = otherBag.data;
	}

	 /**
	   * Returns the length of the array
	   * @return the array's length.
	   */

	public int capacity()
	{
		return this.data.length;
	}

	 /**
	   * Reverses the position of the indexes
	   * of the array with its respective values.
	   */

	public void revers()
	{

		int l=0;
		int r = this.data.length-1;
		while(l<r)
		{
			Num temp = data[l];
			data[l] = data[r];
			data[r] = temp;
			++l;
			--r;

		}

	}

	 /**
	   * Searches and returns the highest value
	   * of the array.
	   * @return The highest value in the array.
	   */

	public Num findMaxValue()
	{
		Num max = this.data[0];
		for(int i = 1; i < data.length; i++)
		{
			if (data[i].get()>max.get())
			{
				max = data[i];

			}

		}

		return max;

	}

	 /**
	   * Searches and returns the lowest value
	   * of the array.
	   * @return The lowest value in the array.
	   */

	public Num findMinValue()
	{
		Num min = this.data[0];
		for(int i = 1; i < data.length; i++)
		{
			if (data[i].get()<min.get())
			{
				min = data[i];

			}

		}

		return min;
	}

	 /**
	   * Searches and returns the index that holds
	   * the highest value of the array.
	   * @return The index of the highest value.
	   */

	public int findMaxIndex()
	{
		int max = this.data[0].get();
		int maxIndex = 0;
		for (int i = 1; i > data.length; i++)
		{
			if(data[i].get()>max)
			{
				max = data[i].get();
				maxIndex = i;

			}
		}

		return maxIndex;
	}

	 /**
	   * Searches and returns the index that holds
	   * the lowest value of the array.
	   * @return The index of the lowest value.
	   */

	public int findMinIndex()
	{
		int min = this.data[0].get();
		int minIndex = 0;
		for (int i = 1; i < data.length; i++)
		{
			if(data[i].get()<min)
			{
				min = data[i].get();
				minIndex = i;

			}
		}

		return minIndex;
	}

	 /**
	   * Gets the value correspoding to a
	   * given index of the array.
	   * @param index The index which the user enters.
	   * @return 	  The value of given index.
	   */

	public Num get(int index)
	{
		if (index >= this.data.length || index < 0)
		{
			System.out.println("Index is out of bounds! Program terminating");
			System.exit(0);
		}
			return this.data[index];

	}

	 /**
	   * Sets the value correspoding to a
	   * given index of the array.
	   * @param index The index which the user enters.
	   * @param value The value which the user assigns.
	   */

	public void set(int index, Num value)
	{
		if (index > this.data.length || index < 0)
		{
			System.out.println("Index is out of bounds! Program terminating");
			System.exit(0);
		}
		else
			this.data[index] = value;

	}

	 /**
	   * Allocates a new String that displays
	   * the values of the array and returns it.
	   * @return The String containing the values
	   			 of the array.
	   */

	public String toString()
	{
		String arrDisplay = new String();

		for (int i=0; i<this.data.length; i++)
		{
			arrDisplay = arrDisplay + data[i] + " ";
		}

		return arrDisplay;

	}

	 /**
	   * Creates a new array which has the same length
	   * as two other desired arrays. Also, assigns the
	   * new array the values from the two chosen arrays.
	   * @param otherBag Summons the second array.
	   * @return 		 The newly created array.
	   */

	public NumBag combine(NumBag otherBag)
	{

		Num bag[] = new Num[this.capacity() + otherBag.capacity()];

		for(int i = 0; i < this.capacity(); i++)
		{
			bag[i]=data[i];
		}

		for(int i = this.capacity(); i < bag.length; i++)
		{
			bag[i]= otherBag.data[i-this.capacity()];

		}

		return new NumBag(bag);

	}



}







