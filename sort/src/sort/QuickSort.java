package sort;

/*
 * 快速排序
 * 时间复杂度 nlogn
 * 空间复杂度nlogn
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] array = {37, 40, 38, 42, 461, 5, 7, 9, 12};
		quickSort(array,0,array.length-1);
		for(int x:array)
			System.out.print(x+"  ");
	}
	
	public static void quickSort(int[] array,int low,int high) {
		if(array == null || array.length <= 0)
			return;
		if(low < high) {
			int q = partition(array,low,high);
			quickSort(array,low,q-1);
			quickSort(array,q+1,high);
		}
	}
	
	public static int partition(int[] array,int start,int end) {
		int x = array[end];
		int index = start;
		for(int i = start;i < end;i++) {
			if(array[i] <= x) {
				int tmp = array[i];
				array[i] = array[index];
				array[index] = tmp;
				index++;
			}
		}

		array[end] = array[index];
		array[index] = x;
		
		return index;
	}
}
