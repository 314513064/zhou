package sort;
/*
 * 堆排序
 * 时间复杂度nlogn
 * 空间复杂度O(1)
 */
public class HeapSort {
	public static int parent(int i) {	//父节点
		return i/2;
	}
	
	public static int left(int i) {	//左孩子
		return 2*i+1;
	}
	
	public static int right(int i) {	//右孩子
		return 2*i+2;
	}
	
	public static void maxHeapify(int[] array,int index,int heapSize) {
		int leftIndex = left(index);
		int rightIndex = right(index);
		int largestIndex = index;
		if(leftIndex < heapSize && array[leftIndex] > array[index]) {
			largestIndex = leftIndex;
		}
		if(rightIndex < heapSize && array[rightIndex] > array[largestIndex]) {
			largestIndex = rightIndex;
		}
		if(largestIndex != index) {
			int tmp = array[index];
			array[index] = array[largestIndex];
			array[largestIndex] = tmp;
			maxHeapify(array,largestIndex,heapSize);
		}
	}
	
	public static void buildHeap(int[] array) {
		for(int i = array.length/2;i >= 0;i--) {
			maxHeapify(array,i,array.length);
		}
	}
	
	public static void heapSort(int[] array) {
		if(array == null || array.length <= 0)
			return;
		buildHeap(array);
		for(int i = array.length-1;i > 0;i--) {
			int tmp = array[0];
			array[0] = array[i];
			array[i] = tmp;
			maxHeapify(array,0,i);
		}
	}
	
	public static void main(String[] args) {
		int[] array = {37, 40, 38, 42, 461, 5, 7, 9, 12};
		heapSort(array);
		for(int x:array)
			System.out.print(x+"  ");
	}
}
