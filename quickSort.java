import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class quickSort {

	/**
	 * Iterative quickSort
	 * 
	 * Idea:  
	 * 
	 *     Array[] = {6,8,1,2,5}         stack container = Stack<ArrayList<Integer>> temp ;
	 *     
	 *     first loop result :  Array[1,2,5,6,8};    pivotIndex =  2
	 *     
	 *     cut this array to 3 peaces, put those to container
	 *     
	 * container-> |           |       then take out top of stack          |           |  <- container
	 *             |           |        do same thing cut 3 peaces         |    {1}    |
	 *             |  {1,2}    |           put those to stack =>           |    {2}    |
	 *             |   {5}     |                                           |    {5}    |
	 *             |  {6,8}    |                                           |   {6,8}   |
	 *             |___________|                                           |___________|                                  
	 *    top of stack size = 1  which mean is this element is the smallest number from stack
	 *    then place it to original Entry.
	 *    Do same thing until stack is empty
	 * @param Entry
	 */
	public static void quickSortIte(int [] Entry){
		
		Stack<ArrayList<Integer>> temp = new Stack<ArrayList<Integer>>(); //stack container
		ArrayList<Integer> T = new ArrayList<Integer>();                  //the template for save the elements from Entry 
		
		for(int i=0; i<Entry.length;i++){      //copy to template
			T.add(Entry[i]);
		}
		temp.push(T);   //push template to stack container
		
		int replaceIndex = 0;
		while(!temp.empty()){
			if(temp.peek().size()==1){ //top of stack size = 1  which mean is this element is the smallest number from stack
				Entry[replaceIndex] = temp.pop().get(0); //replace this small number to front of Entry
				replaceIndex++;  //move replace index
			}
			else{
				ArrayList<Integer> loopTemp = new ArrayList<Integer>(temp.pop());
				int stayIndex = loopTemp.size()-1;       //pick last element from entry
				int moveFrontIndex = 0;                  //pick first element from entry
				int moveRearIndex = stayIndex-1;         //pick second to last element from entry
				
				while(moveFrontIndex != moveRearIndex){  // loop end until left index move to same position with right index
					if(loopTemp.get(moveFrontIndex) > loopTemp.get(stayIndex)){ //if the left element is smaller than last element
						arrayListSwap(loopTemp,moveFrontIndex,moveRearIndex);  //swap left element and right element
						if(moveFrontIndex <= moveRearIndex){
							moveRearIndex--;              //move right Index to left with 1 position
						}
					}
					else{
						if(moveFrontIndex <= moveRearIndex){  //if the left element is bigger than last element
						    moveFrontIndex++;                //move left Index to right with 1 position
						}
					}
				}
				
				if(loopTemp.get(moveFrontIndex) > loopTemp.get(stayIndex)){ // if the pivot index element is bigger than last element
					pushPart(temp,loopTemp,moveFrontIndex,stayIndex);      //push 3 peaces to stack
					pushPart(temp,loopTemp,stayIndex,stayIndex+1);
					pushPart(temp,loopTemp,0,moveFrontIndex);
				}
				else{                                                // if the pivot index element is smaller than last element
					pushPart(temp,loopTemp,moveFrontIndex+1,stayIndex);   //push 3 peaces to stack
					pushPart(temp,loopTemp,stayIndex,stayIndex+1);
					pushPart(temp,loopTemp,0,moveFrontIndex+1);
				}
			}
		}
		
	}
	
	/**
	 * Recursion quickSort( one argument)
	 * @param Entry
	 */
	public static void quickSortRec(int [] Entry){
		quickSortRecursion(Entry,0,Entry.length-1);
	}
	
	/**
	 * recursion quickSort (three argument)
	 * @param Entry
	 * @param firstIndex
	 * @param endIndex
	 */
	public static void quickSortRecursion(int [] Entry,int firstIndex,int endIndex){
		if(firstIndex>=endIndex){
			return;
		}
		int pivotIndex = getPivotIndex(Entry,firstIndex,endIndex);
		quickSortRecursion(Entry,firstIndex,pivotIndex - 1);
		quickSortRecursion(Entry,pivotIndex + 1,endIndex);
	}
	
	/**
	 * Pass Entry and two Index( first and end)
	 * after sort, return pivot Index(pick last element from the Entry).
	 * @param Entry
	 * @param firstIndex
	 * @param endIndex
	 * @return
	 */
	public static int getPivotIndex(int [] Entry,int firstIndex,int endIndex){
		int result = endIndex;             //pick last element from the Entry
		int indexFromLeft = firstIndex;    //pick first element from the Entry
		int indexFromRight = endIndex -1;  //pick second to last element from the Entry

		while(indexFromLeft != indexFromRight){     // loop end until left index move to same position with right index
			if(Entry[indexFromLeft] > Entry[result]){      //if the left element is smaller than last element
				swap(Entry,indexFromLeft,indexFromRight);  //swap left element and right element
				if(indexFromLeft <= indexFromRight){    
					indexFromRight--;                    //move right Index to left with 1 position
				}
			}
			else{
				if(indexFromLeft <= indexFromRight){    //if the left element is bigger than last element
					indexFromLeft++;                    //move left Index to right with 1 position
				}
			}
		}
		if(Entry[indexFromLeft] > Entry[result]){      // if the pivot index element is bigger than last element
			swap(Entry,indexFromLeft,result);          // put bigger number to right
			result = indexFromLeft;                    // this position is pivot index
		}
		else{                                          // if the pivot index element is smaller than last element
			swap(Entry,indexFromLeft+1,result);        // put last element on the left of pivot index
			result = indexFromLeft+1;                  // set this position be pivot Index
		}
		return result;
	}
	
	/**
	 * Pass in two ArrayList and two Index
	 * 
	 * Example:  
	 *     tList-> |           |                                           |           |  <- tStack
	 *             |     9     |                       put it to tStack    |           |
	 *             |     7     | <- endIndex                 =>            |     7     |
	 *             |     5     |                                           |     5     |
	 *             |     3     | <-firstIndex                              |     3     |
	 *             |___________|                                           |___________|                                  
	 * @param tStack
	 * @param tList
	 * @param firstIndex
	 * @param endIndex
	 */
	public static void pushPart(Stack<ArrayList<Integer>> tStack,ArrayList<Integer> tList,int firstIndex,int endIndex){
		ArrayList<Integer> Part = new ArrayList<Integer>( tList.subList(firstIndex, endIndex));
		if(!Part.isEmpty()){
		      tStack.push(Part);//
		}
	}

	/**
	 * ArrayList swap method
	 * pass in original arrayList, and two position(i,j) do swap
	 * @param T
	 * @param i
	 * @param j
	 */
	public static void arrayListSwap(ArrayList<Integer> T,int i,int j){
		Collections.swap(T, i, j);
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
		
		quickSortIte(test);
		
//		quickSortRec(test);
		
		System.out.println(Arrays.toString(test));

	}

}
