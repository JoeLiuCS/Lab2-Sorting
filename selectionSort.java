import java.util.Arrays;

public class selectionSort {
	
	/**
	 * Iterative selectionSort
	 * @param Entry
	 */
	public static void selectionSortIte(int [] Entry){
			
			int numOfLength = Entry.length;
			
			for(int i = 0; i < numOfLength;i++){
				int smallIndex = i ;                  //temp for save the small number Index
				for(int j = i+1; j < numOfLength;j++){//check every position if those are not sorted
					if(Entry[smallIndex]>Entry[j]){
						smallIndex = j;               //find the small number,save this position
					}
				}
				swap(Entry,i,smallIndex);           //swap this small number and put it to front
			}
	}
	/**
	 * Recursion selectionSort (one argument)
	 * @param Entry
	 */
	public static void selectionSortRec(int [] Entry){
		selectionSortRecursion(Entry,0);
	}
	
	/**
	 * Recursion selectionSort (two argument)
	 * @param Entry
	 * @param position
	 */
	public static void selectionSortRecursion(int [] Entry,int position){
		
		if(position == Entry.length){         //if here has no more elements,return the loop
			return;
		}
		int smallIndex = position;         //temp for save the small number Index
		for(int i=smallIndex+1;i<Entry.length;i++){
			if(Entry[smallIndex]>Entry[i]){
				smallIndex=i;             //find the small number,save this position
			}
		}
		swap(Entry,position,smallIndex); //swap this small number and put it to front

		selectionSortRecursion(Entry,position+1);
		
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
		
		
		selectionSortIte(test);

		
//		selectionSortRec(test);

		
		System.out.println(Arrays.toString(test));
		
	}
	
}
