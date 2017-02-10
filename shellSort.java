import java.util.Arrays;

public class shellSort {

	/**
	 * Iterative ShellSort
	 * @param Entry
	 */
	public static void shellSortIte(int [] Entry){
		
		int numOfLength = Entry.length;   //Array Length
		int numOfSequence = hibbardSequence(numOfLength); //Get the first HibbardSequence (see method explanation)
		int runTime = runTimeSequence(numOfLength); //Run time for first loop (see method explanation)
		
		for(int i=0;i<runTime;i++){

			for(int j=0; j+numOfSequence<numOfLength;j++){
				if(compareIndex(Entry,j,j+numOfSequence)>0){ // base on hibardSequence to check ( less than length)
					swap(Entry,j,j+numOfSequence);        //Found the small number do swap
					compareSwap(Entry,j,numOfSequence);   //check behind position, if found smaller, do swap
				}
			}
			numOfSequence = (numOfSequence-1) / 2;  // change to lower HibbardSequence when it finished
		}
		
	}
	
	/**
	 * Recursion ShellSort (one argument)
	 * @param Entry
	 */
	public static void shellSortRec(int [] Entry){
		shellSortRecursion(Entry,hibbardSequence(Entry.length));
	}
	
	/**
	 * Recursion ShellSort (two argument)
	 * @param Entry
	 * @param numOfSequence
	 */
	public static void shellSortRecursion(int [] Entry,int numOfSequence){
		if(numOfSequence < 1){
			return;
		}
		for(int i=0; i+numOfSequence<Entry.length;i++){
			if(compareIndex(Entry,i,i+numOfSequence)>0){
				swap(Entry,i,i+numOfSequence);       
				compareSwap(Entry,i,numOfSequence); // check before j
			}
		}
		shellSortRecursion(Entry,(numOfSequence-1)/2);
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
	 * pass in the array'length, then do calculate to find the correct Hibbard Sequence
	 * First Hibbard Sequence should less than half length of the array
	 * Sequence = (previous * 2) +1 
	 * 
	 * Example:            
	 *                ArraySize = 75;
	 *                half size = 32 (Integer)
	 *                then complier pick Hibbard Sequence = 31
	 * @param arrayLen
	 * @return
	 */
	public static int hibbardSequence(int arrayLen){
		int result=1;
		while(result < arrayLen/2 && result!=1){
			result = result*2 + 1 ;
		}
		return result;
	}
	
	/**
	 * Pass in array'length, then return the round number for loop
	 * Example:
	 *          Array size = 75;
	 *          
	 *          first hibbardSequence = 31
	 *          second hibbardSequence = 15
	 *          third hibbardSequence = 7
	 *          fourth hibbardSequence = 3
	 *          fifth hibbardSequence = 1
	 *          
	 *          Run time = 5;
	 * @param len
	 * @return
	 */
	public static int runTimeSequence(int len){
		int result = 0;
		int tempLen = len;
		
		while(tempLen>1){
			tempLen = tempLen/2;
			result++;
		}
		return result;
	}
	
	/**
	 * Pass in the original array and two Index.
	 * 
	 *      1st > 2nd    return 1;
	 *      1st < 2nd    return -1;
	 *      1st = 2nd    return 0;
	 * 
	 * @param Entry
	 * @param firstIndex
	 * @param secondIndex
	 * @return
	 */
	public static int compareIndex(int [] Entry,int firstIndex,int secondIndex){
		int result = 0;
		if(Entry[firstIndex] < Entry[secondIndex]){
			result = -1;
		}
		if(Entry[firstIndex] > Entry[secondIndex]){
			result = 1;
		}
		if(Entry[firstIndex] == Entry[secondIndex]){
			result = 0;
		}
		return result;
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
		int [] test = initializeArray(50,300);
		System.out.println(Arrays.toString(test));
		
//		shellSortIte(test);
		
		shellSortRec(test);
		
		System.out.println(Arrays.toString(test));
	}

}
