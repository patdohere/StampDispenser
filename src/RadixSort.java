/**
 * Class contains a the code for RADIX SORT
 * O(n) sorting algorithm for integers
 * @author dop
 *
 * Modified Implementation
 * https://gist.github.com/yeison/5606963
 */
public class RadixSort { 
	
	/**
	 * 
	 * @param unsorted unsorted list of integers
	 * @return sorted list of integers
	 */
	public int[] sort(int[] unsorted)
	{
		int max = findMax(unsorted);
		int digits = Integer.toString(max).length();
		int place = 1;
		
		// change the place we are sorting each iteration from
		// least to greatest significant digit
		for (int i = 1; i <= digits; i *= 10)
		{
			place *= 10;
			unsorted = countingSort(unsorted, place);
		}
		
		return unsorted;
	}
	
	/**
	 * 
	 * @param unsorted list of unsorted/partially sorted elements
	 * @param place digit we are sorting
	 * @return sorted list in respect to digit at place
	 */
	private int[] countingSort(int[] unsorted, int place)
	{
		// output array
		int[] out = new int[unsorted.length];
		 
		// number of elements per digit
        int[] count = new int[10];
 
        // calculate number of elements per digit
        for(int i=0; i < unsorted.length; i++){
            int digit = getDigit(unsorted[i], place);
            count[digit] += 1;
        }
 
        // calculate offsets for output array
        for(int i=1; i < count.length; i++){
            count[i] += count[i-1];
        }
 
        // move elements into output array sorted using offsets
        for(int i = unsorted.length-1; i >= 0; i--){
            int digit = getDigit(unsorted[i], place);
 
            out[count[digit]-1] = unsorted[i];
            count[digit]--;
        }
 
        return out;
	}
	
	/**
	 * 
	 * @param value the value we are trying to extract the digit
	 * @param place the digit we are trying to get like 1, 10, 100, 1000
	 * @return the digit in the 1, 10, 100, 1000 place
	 */
	private int getDigit(int value, int place)
	{
		return ((value/place) % 10);
	}
	
	/**
	 * 
	 * @param list of integer elements
	 * @return the largest element in list
	 */
	private int findMax(int[] list)
	{
		int max = Integer.MIN_VALUE;
		
		for (int el : list)
		{
			if (el > max)
				max = el;
		}
		
		return max;
	}
}
