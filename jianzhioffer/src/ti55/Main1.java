package ti55;


/*
 * 二叉树深度
 * 输入一颗二叉树的根节点，求该树的深度
 */
public class Main1 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(8);
		
		System.out.println(solve(root));
	}
	
	public static int solve(TreeNode root) {
		if(root == null)
			return 0;
		int left = solve(root.left);
		int right = solve(root.right);
		
		return (left > right) ? (1+left) : (1+right);
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
