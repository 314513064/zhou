package ti40;
/*
 * 最小的k个数
 * 输入n个数，找出其中最小的k个数
 * 
 * 用最大堆解决
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr = {4,5,1,6,2,7,3,8,1};
		int k = 4;
		solve(arr, k);
	}
	public static void solve(int[] arr, int k) {
		buildHeap(arr, k);
		for(int i = k;i < arr.length;i++) {
			if(arr[i] < arr[0]) {
				arr[0] = arr[i];
				maxHeapify(arr, 0, k);
			}
		}
		for(int i = 0;i < k;i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void buildHeap(int[] arr,int heapSize) {
		for(int i = heapSize/2;i >= 0 ;i--) {
			maxHeapify(arr, i, heapSize);
		}
	}
	public static void maxHeapify(int[] arr,int index,int heapSize) {
		int left = index*2+1;
		int right = index*2+2;
		int largestIndex = index;
		if(left < heapSize && arr[left] > arr[largestIndex]) {
			largestIndex = left;
		}
		if(right < heapSize && arr[right] > arr[largestIndex]) {
			largestIndex = right;
		}
		if(largestIndex != index) {
			int tmp = arr[index];
			arr[index] = arr[largestIndex];
			arr[largestIndex] = tmp;
			maxHeapify(arr, largestIndex, heapSize);
		}
	}
}
