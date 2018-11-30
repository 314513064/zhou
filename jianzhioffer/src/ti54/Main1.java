package ti54;


/*
 * 二叉搜索树的第k大节点
 * 给定一颗二叉搜索树，请找出其中第k大的节点。
 */
public class Main1 {
	static int count = 0;
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(8);
		
		int k = 4;
		solve(root,k);
	}
	
	public static void solve(TreeNode root , int k ) {
		if(root.left != null)
			solve(root.left, k);
		count++;
		if(count == k) {
			System.out.println(root.val);
			return;
		}
		
		if(root.right != null)
			solve(root.right, k);
	}
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) {
		this.val = val;
	}
}
