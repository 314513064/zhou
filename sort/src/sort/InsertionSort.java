package sort;
/*
 * 插入排序
 * 时间复杂度n^2
 * 空间复杂度O(1)
 */
public class InsertionSort {
	public static void main(String[] args) {
		int[] array = {37, 40, 38, 42, 461, 5, 7, 9, 12};
		insertionSort(array);
		for(int x:array)
			System.out.print(x+"  ");
	}
	
	public static void insertionSort(int[] array) {
		if(array == null || array.length<=0)
			return;
		for(int i = 1;i < array.length;i++) {
			int index = i-1;
			int key = array[i];
			while(index>=0 && array[index] > key) {
				array[index+1] = array[index];
				index--;
			}
			array[index+1] = key;
		}
	}
}
