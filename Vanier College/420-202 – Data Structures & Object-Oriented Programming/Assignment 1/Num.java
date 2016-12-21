/** Alexander Bratyshkin - Assignment 1 - Part 2 */

public class Num
{

	private int value; // Wrapped variable

	public Num() // Default constructor
	{
		this.value = 0;
	}

	public Num(int value) // Normal constructor
	{
		this.value = value;
	}

	public Num(Num other) // Copy constructor
	{
		this.value = other.value;

	}

	public int get() // Getter
	{
		return this.value;
	}

	public void set(int value) // Setter
	{
		this.value = value;
	}

	public String toString() // Utility, return this object in string form
	{
		return String.valueOf(value);
	}

}


