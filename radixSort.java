import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class radixSort {

	/**
	 * Iterative radixSort
	 * Idea : 
	 *             Array[] ={3,50,350};     3 = 003   50 = 050     350 = 350 ( base on largest digits)
	 * 
	 * first round:  (container is queue)
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |           |
	 *             |    350    |          |           |            |           |
	 *             |    050    |          |   003     |            |           |
	 *             |           |          |           |            |           |
	 *                 (0)                     (3)                     (5)
	 *                 
	 * second round:
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |    350    |
	 *             |   003     |          |           |            |    050    |
	 *             |           |          |           |            |           |
	 *                 (0)                     (3)                     (5)
	 *                 
	 * Third round
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |           |
	 *             |           |          |           |            |           |
	 *             |    050    |          |           |            |           |
	 *             |    003    |          |           |            |    350    |
	 *             |           |          |           |            |           |
	 *                 (0)                     (3)                     (5)
	 *                 
	 *                 result => 003  050 350
	 * @param Entry
	 */
	public static void radixSortIte(int [] Entry){
		@SuppressWarnings("unchecked")
		Queue<Integer> t[] = new Queue[10];       // create the queue container 0~9
		int numRunDigits = numOfLargeDigit(Entry);  //get largest Digit from entry(see method explanation)
		
		for(int i=0;i<numRunDigits;i++){     
			for(int j =0;j<Entry.length;j++){ 
				int indexQueue = getDigit(Entry[j],i+1);    //get the digit' number (see method explanation)
				if(t[indexQueue] == null){                  //if that index queue is empty
				   t[indexQueue] = new LinkedList<>();      // create a container
				}
				t[indexQueue].add(Entry[j]);                //push this number to container  
			}
			for(int j=0,k=0; j<10;j++){                    //pop everything out from the queue container (order changed)
				if(t[j] != null){            
					while(!t[j].isEmpty()){
						Entry[k] = t[j].poll();
						k++;
					}
				}
			}
		}
	}
	
	/**
	 * Recursion radixSort (one argument)
	 * and also create the queue container 0~9 for sorting
	 * @param Entry
	 */
	public static void radixSortRec(int [] Entry){
		@SuppressWarnings("unchecked")
		Queue<Integer> t[] = new Queue[10];
		int numRunDigits = numOfLargeDigit(Entry);
		radixSortRecursion(Entry,numRunDigits,t);
	}
	
	/**
	 * Recursion radixSort (three argument)
	 * pass in queue container ,entry, and largestDigit
	 * @param Entry
	 * @param largestDigit
	 * @param t
	 */
	public static void radixSortRecursion(int[] Entry,int largestDigit,Queue<Integer>[] t){
		
		if(largestDigit<0){   //if largestDigit come to unit digit, then return
			return;
		}
		
		radixSortRecursion(Entry,largestDigit-1,t);
		
		for(int j =0;j<Entry.length;j++){ 
			int indexQueue = getDigit(Entry[j],largestDigit+1);   //get the digit' number (see method explanation)
			if(t[indexQueue] == null){                            //if that index queue is empty
			   t[indexQueue] = new LinkedList<>();                // create a container
			}
			t[indexQueue].add(Entry[j]);                         //push this number to container  
		}
		for(int j=0,k=0; j<10;j++){                              //pop everything out from the queue container (order changed)
			if(t[j] != null){
				while(!t[j].isEmpty()){
					Entry[k] = t[j].poll();
					k++;
				}
			}
		}
	}
	
	/**
	 * Pass in Entry, return the digits of the largest number
	 * Example:   Entry[] = {12,1,2,3,67,897,1234}
	 *            largest number = 1234;
	 *            LargeDigit = 4;
	 *            then return 4;
	 * @param Entry
	 * @return
	 */
	public static int numOfLargeDigit(int [] Entry){
		int result = 1;
		int largeNum = 0;
		for(int i=0;i<Entry.length;i++){
			if(Entry[i] >largeNum){
				largeNum = Entry[i];
			}
		}
		while((int)Math.pow(10, result) <= largeNum ){
			result++;
		}
		return result;
	}
	
	/**
	 * pass in number, and which digit we looking at.
	 * return the number from that digit
	 * 
	 * Example:
	 *          getDigit(725,3);
	 *          10^3 = 1000;
	 *          725%1000 = 725;
	 *          725/10^2 = 7 (Integer)
	 *          then return 7;
	 *          
	 * @param num
	 * @param digit
	 * @return
	 */
	public static int getDigit(int num,int digit){
		int result =  0;
		int getRaim = (int) Math.pow(10, digit);
		int getRemainder = num % getRaim;
		int getDigit = (int) Math.pow(10, digit-1);
		result = (int)getRemainder / getDigit;
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
		int [] test = initializeArray(100,999);
		System.out.println(Arrays.toString(test));
		
//		radixSortIte(test);
		
		radixSortRec(test);
		
		System.out.println(Arrays.toString(test));

	}

}
