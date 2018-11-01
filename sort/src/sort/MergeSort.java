package sort;
/*
 * 归并排序
 * 时间复杂度nlogn
 * 空间复杂度n
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] array = {37, 40, 38, 42, 461, 5, 7, 9, 12};
		mergeSort(array,0,array.length-1);
		for(int x:array)
			System.out.print(x+"  ");
	}
	public static void mergeSort(int[] array,int low,int high) {
		if(array == null || array.length <= 0)
			return;
		if(low < high) {
			int mid = (high-low)/2+low;
			mergeSort(array,low,mid);
			mergeSort(array,mid+1,high);
			merge(array,low,mid,high);
		}
	}
	
	public static void merge(int[] array,int low,int mid,int high) {
		int[] left = new int[mid-low+2];
		int[] right = new int[high-mid+1];
		for(int i = 0;i < left.length-1;i++) {
			left[i] = array[low+i];
		}
		for(int i = 0;i < right.length-1;i++) {
			right[i] = array[mid+i+1];
		}
		left[left.length-1] = Integer.MAX_VALUE;
		right[right.length-1] = Integer.MAX_VALUE;
		int indexLeft = 0;
		int indexRight = 0;
		for(int i = low;i <= high;i++) {
			if(left[indexLeft] < right[indexRight]) {
				array[i] = left[indexLeft];
				indexLeft++;
			}else {
				array[i] = right[indexRight];
				indexRight++;
			}
		}
	}
}

