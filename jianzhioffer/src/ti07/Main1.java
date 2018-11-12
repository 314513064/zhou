package ti07;
/*
 * 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历结果，请重建该二叉树。
 * 如输入前序遍历为{1,2,4,7,3,5,6,8}
 * 中序遍历为{4,7,2,1,5,3,8,6}
 */
public class Main1 {
	public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] mid = {4,7,2,1,5,3,8,6};
		TreeNode root = solve(pre, mid, 0, pre.length-1, 0, mid.length-1);
	}
	
	public static TreeNode solve(int[] pre,int[] mid,int preStart,int preEnd,int midStart,int midEnd) {
		TreeNode root = new TreeNode(pre[preStart]);
		int leftCount = 0;
		int midIndex = midStart;
		while(pre[preStart] != mid[midIndex]) {
			midIndex++;
			leftCount++;
		}
		if(midStart < midIndex) 
			root.leftChild = solve(pre, mid, preStart+1, preStart+leftCount, midStart, midIndex-1);
		if(midIndex < midEnd)
			root.rightChild = solve(pre, mid, preStart+leftCount+1, preEnd, midIndex+1, midEnd);
		return root;
	}
}

class TreeNode{
	int val;
	TreeNode leftChild;
	TreeNode rightChild;
	public TreeNode(int val) {
		this.val = val;
	}
}