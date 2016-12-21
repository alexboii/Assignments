
public class maxElement {
	
	public static void main(String... args)
	{
	int[] arr = {5, 65, 34, 87, 45, 25};
	int max = findMax (arr, arr[arr.length - 1], arr.length - 1 );
	System.out.println(max);
	}
	
	public static int findMax(int[] arr, int max, int index){
		
	    if ( index < 0 )
	        return max;
	    if ( max < arr[index] )
	        max = arr[index];
	    
	    return findMax(arr, max, index - 1); 
	    
	}
	
}
