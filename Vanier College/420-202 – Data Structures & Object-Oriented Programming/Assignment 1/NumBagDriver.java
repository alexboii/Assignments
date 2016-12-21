public class NumBagDriver
{
   public static void main(String[] args)
   {
      System.out.println("Tesing the default constructor NumBag()");
      NumBag bag1 = new NumBag();
      System.out.println("bag1 after construction: " + bag1);

      System.out.println("\nTesing the normal constructor NumBag(int capacity)");
      NumBag bag2 = new NumBag(8);
      System.out.println("bag2 after construction: " + bag2 + "");

      System.out.println("\nReversing bag2");
      bag2.revers();
      System.out.println("bag2 reversed: " + bag2);

      System.out.println("\nbag2's max value: " + bag2.findMaxValue());
      System.out.println("bag2's max index: " + bag2.findMaxIndex());

      System.out.println("bag2's min value: " + bag2.findMinValue());
      System.out.println("bag2's min index: " + bag2.findMinIndex());

      System.out.println("\nCloning bag2 into a new bag3");
      NumBag bag3 = new NumBag(bag2);  // Testing the copy constructor
      System.out.println("bag3 cloned from bag2: " + bag3);

      System.out.println("\nCreating a new bag4 from a given array");
      System.out.println("Tesing the normal constructor NumBag(Num[] array)");
      Num[] array = {new Num(11), new Num(22), new Num(33), new Num(99), new Num(88), new Num(77), new Num(44), new Num(66), new Num(55)};
      NumBag bag4 = new NumBag(array);
      System.out.println("bag4 created from given array: " + bag4);

      System.out.println("\nCreating a new bag5 filled with all elements of bag3 and bag4");
      NumBag bag5 = bag3.combine(bag4);
      System.out.println("bag3 unchanged: " + bag3);
      System.out.println("bag4 unchanged: " + bag4);
      System.out.println("bag5: " + bag5);

      System.out.println("\nTesting set and get");
      System.out.println("\nResetting first element of bag5 with 111");
      bag5.set(0, new Num(111));             // Reset first element of bag5 to 111
      System.out.println("bag3 unchanged: " + bag3);
      System.out.println("bag4 unchanged: " + bag4);
      System.out.println("bag5: " + bag5);

      System.out.println("\nResetting last element of bag5 with 999");
      int lastIndex = bag5.capacity()-1;  // Also testing the capacity() method
      bag5.set(lastIndex, new Num(999)); // Reset last element of bag5 to 999
      System.out.println("bag3 unchanged: " + bag3);
      System.out.println("bag4 unchanged: " + bag4);
      System.out.println("bag5: " + bag5);

      System.out.println("\nTest the get method");
      System.out.println("Fifth element of bag5: " + bag5.get(4));

      System.out.println("\nLet's get out of bounds! this should kill this program");
      int killerIndex = bag5.capacity(); // any index < 0 or index >= capacity of bag5 is a killer index
      System.out.println("This message must not be displayed!" + bag5.get(killerIndex));
   }
}
