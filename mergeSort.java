import java.util.ArrayList;
import java.util.Arrays;

public class mergeSort {

	/**
	 * Iterative MergeSort
	 * 
	 * Idea: Create a container use -> ArrayList<ArrayList<Integer>> bigTemp 
	 *       split elements from the original Array to single one (make it to be a ArrayList size = 1)
	 *       then put those elements to BigArrayList 
	 *       Start at top,get top of two elements.  Do merge by sort !!!!
	 *       then put result to the bottom. So on.
	 *       Until the BigTemp size = 1, which mean the merge is finished
	 * @param Entry
	 */
	public static void mergeSortIte(int [] Entry){
		
		ArrayList<ArrayList<Integer>> bigTemp = new ArrayList<ArrayList<Integer>>(); //create Container
		
		for(int i=0; i<Entry.length;i++){     //split elements from the original Array to single one
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(Entry[i]);
			bigTemp.add(temp);
		}
		
		while (bigTemp.size() > 1) {  // when The BigTemp size = 1, merge sort finished
			
			ArrayList<Integer> temp2 = new ArrayList<Integer>();//Create a container to save merge result
			ArrayList<Integer> pos0= bigTemp.get(0);         //pick 1st from the ArrayList
			ArrayList<Integer> pos1= bigTemp.get(1);         //pick 2nd from the ArrayList
			
			/*
			 * Do merge and  Sort.
			 * Example:
			 *        1st = {2,7,10}    2nd = {1,5}    Container = {}
			 *     => 1st = {2,7,10}    2nd = {5}      Container = {1}
			 *     => 1st = {7,10}      2nd = {5}      Container = {1,2}
			 *     => 1st = {7,10}      2nd = {}       Container = {1,2,5}
			 *     => 1st = {}          2nd = {}       Container = {1,2,5,7,10}
			 */
			while(!pos0.isEmpty() || !pos1.isEmpty()){
				if(pos0.isEmpty()){        //if 1st is empty, Copy remaining elements from 2nd
					temp2.add(pos1.get(0));
					pos1.remove(0);
				}
				else if(pos1.isEmpty()){   //if 2nd is empty, Copy remaining elements from 2nd
					temp2.add(pos0.get(0));
					pos0.remove(0);
				}
				else{                     //put small number to the container
					if(pos0.get(0) > pos1.get(0)){
						temp2.add(pos1.get(0));
						pos1.remove(0);
					}
					else {
						temp2.add(pos0.get(0));
						pos0.remove(0);
					}
				}
			}
			bigTemp.remove(0);     //after get result, delete top two of the Arraylist that we used
			bigTemp.remove(0);
			bigTemp.add(temp2);    //put result to the bottom
		}
		
		/*
		 * Take out each elements from the BigTemp
		 * then replace them to the original Array
		 */
		ArrayList<Integer> result = bigTemp.get(0);
		for (int i = 0; i < result.size(); i++) {
			Entry[i] = result.get(i);
		}
	}
	
	/**
	 * Recursion mergeSort (one argument)
	 * @param Entry
	 */
	public static void mergeSortRec(int [] Entry){
		mergeSortRecursion(Entry,0,Entry.length-1);
	}
	
	/**
	 * Recursion mergeSort(three arguments)
	 * @param Entry
	 * @param firstIndex
	 * @param endIndex
	 */
	public static void mergeSortRecursion(int [] Entry,int firstIndex,int endIndex){
		if(firstIndex >= endIndex){ //if first Index >= end Index, return (cause we can not find middle point anymore)
			return;
		}
		int mid = (firstIndex + endIndex) >> 1;  //Binary shift to find middle Index
		mergeSortRecursion(Entry,firstIndex,mid);
		mergeSortRecursion(Entry,mid+1,endIndex);
		inplaceSort(Entry,firstIndex,mid,mid+1,endIndex);  //do inplaceSort(see method explanation)
	}
	
	/**
	 * Pass in Original Entry and four position from array;
	 * 
	 * Example:        Array[] = {    2     ,     7     ,     10     ,    1    ,    5    ,  ....... }
	 *                                ^                        ^          ^         ^
	 *                                |                        |          |         |
	 *                            part1Start                part1End  part2Start  part2End
	 * 
	 * then do merge and sort
	 * 
	 * Example:
	 *        1st = {2,7,10}    2nd = {1,5}    Container = {}
	 *     => 1st = {2,7,10}    2nd = {5}      Container = {1}
	 *     => 1st = {7,10}      2nd = {5}      Container = {1,2}
	 *     => 1st = {7,10}      2nd = {}       Container = {1,2,5}
	 *     => 1st = {}          2nd = {}       Container = {1,2,5,7,10}
	 *     
	 *    put those elements back to original Entry
	 * @param Entry
	 * @param part1Start
	 * @param part1End
	 * @param part2Start
	 * @param part2End
	 */
	public static void inplaceSort(int [] Entry,int part1Start,int part1End,int part2Start,int part2End){
		int [] temp = new int[part2End-part1Start+1]; //create a temp to save result
		int i= part1Start;
		int j= part2Start;
		int k = 0;
		if(temp.length <= 2){          //if there only has 2 elements or only 1
			if(Entry[part1Start] > Entry[part2End]){    // if 2 element, check first then swap
			  swap(Entry,part1Start,part2End);
			}
			return;                    //if only 1 element, do nothing
		}
		while(i<=part1End || j <= part2End){
			if(i>part1End){           //if 1st is empty, Copy remaining elements from 2nd
				temp[k] = Entry[j];
				k++;
				j++;
			}
			else if(j >part2End){    //if 2nd is empty, Copy remaining elements from 2nd
				temp[k] = Entry[i];
				k++;
				i++;
			}
			else{                  //put small number to the container (merge Sort)
				if(Entry[i]>Entry[j]){
					temp[k] = Entry[j];
					k++;
					j++;
				}
				else{
					temp[k] = Entry[i];
					k++;
					i++;
				}
			}
		}
		
		/*
		 * replace elements from the temp container
		 */
		for(int r = part1Start,l=0; l<temp.length;r++,l++){
			Entry[r] = temp[l];
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
		
//		mergeSortIte(test);
		
		mergeSortRec(test);
		
		System.out.println(Arrays.toString(test));

	}

}
