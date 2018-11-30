package ti33;

/*
 * 二叉搜索树的后序遍历序列
 * 输入一个正数数组，判断该数组是不是某二叉搜索树的后续遍历结果。
 */
/*
 * 二叉搜索树的后续遍历，根节点放在最后，根节点之前有两部分
 * 一部分比根节点小，为左子树
 * 一部分比根节点大，为右子树
 */
public class Main1 {
	public static void main(String[] args) {
		int[] arr1 = {7,4,6,5};
		
		System.out.println(solve(arr1,0,arr1.length-1));
	}
	public static boolean solve(int[] arr,int startIndex, int endIndex) {
		if(startIndex == endIndex)
			return true;
		int leftIndex = startIndex;
		boolean res = false;
		while(arr[leftIndex] < arr[endIndex]) {
			leftIndex++;
		}
		for(int i = leftIndex;i < endIndex;i++) {
			if(arr[i] < arr[endIndex])
				return false;
		}
		res = solve(arr,startIndex, leftIndex-1);
		if(!res)
			return res;
		res = solve(arr, leftIndex, endIndex-1);
		return res;
	}
}

class TreeNode{
	char val;
	TreeNode leftChild;
	TreeNode rightChild;
	public TreeNode(char val) {
		this.val = val;
	}
}