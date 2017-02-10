import java.util.Arrays;

public class insertionSort {
	
	/**
	 * Iterative InsertionSort
	 * @param Entry
	 */
	public static void insertionSortIte(int [] Entry){
		
		int numOfLength = Entry.length;
		
		for(int i=1; i<numOfLength ;i++){  // start at Index 1 because Index 0 is same as a sorted array(size =1)
 			compareSwap(Entry,i,1);
		}
	}
	
	/**
	 * Recursion InsertionSort (one argument)
	 * @param Entry
	 */
	public static void insertionSortRec(int [] Entry){
		insertionSortRecursion(Entry,1);
	}
	
	/**
	 *  Recursion InsertionSort (two argument)
	 * @param Entry
	 * @param position
	 */
	public static void insertionSortRecursion(int [] Entry,int position){
		if(position == Entry.length){ // when the walker go to last position, the loop is end
			return;
		}
		compareSwap(Entry,position,1);  //find the proper position, then insert.
		insertionSortRecursion(Entry,position+1);
	}
	
	
	/**
	 * Pass in the Entry array, and the current element' position which one we want to insert
	 * and also the shiftunit is number to checking the position behind insertion Index.
	 * example: Entry[] = 1,2,3,4,5,6
	 *          compareSwap(Entry,6,1)
	 *          then complier will check 5 -> 4 -> 3,so on 
	 *          compareSwap(Entry,6,2)
	 *          then complier will check 4 -> 2  
	 * Find the small number, then put this element to the right
	 * @param Entry
	 * @param Index
	 * @param shiftUnit
	 */
	public static void compareSwap(int[] Entry,int Index,int shiftUnit){
		for(int i=Index; i>0 && i-shiftUnit>=0;i = i-shiftUnit){ 
			if(Entry[i-shiftUnit] > Entry[i]){    //find small element
				swap(Entry,i-shiftUnit,i);        //then swap
			}
			else{                                //if next element is bigger than input
				break;                           //stop the loop
			}
		}
	}
	
	
	/**
	 * Get two Index and original Entry
	 * Do swap!!!!
	 * @param Entry
	 * @param firstIndex
	 * @param secondIndex
	 */
	public static void swap(int [] Entry, int firstIndex, int secondIndex){
		int temp = Entry[firstIndex];
		Entry[firstIndex] = Entry[secondIndex];
		Entry[secondIndex] = temp;
	}
	
	/**
	 * Pass in the size and random number range to create a Integer Array
	 * @param len
	 * @param range
	 * @return
	 */
	public static int [] initializeArray(int len,int range){
		int [] temp = new int[len];
		for(int i=0;i<len;i++){
			temp[i] =  (int)(Math.random()*range+1);
		}
		return temp;
	}
	
	public static void main(String[] args) {

		int [] test = initializeArray(100,999);
		System.out.println(Arrays.toString(test));
		
//		insertionSortIte(test);
		
		insertionSortRec(test);
		
		System.out.println(Arrays.toString(test));
		
	}
}
