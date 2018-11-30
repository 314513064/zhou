package ti34;

/*
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点一直到叶节点所经过的节点形成一条路径。
 */
public class Main1 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.leftChild = new TreeNode(2);
		root.leftChild.leftChild = new TreeNode(4);
		root.leftChild.rightChild = new TreeNode(5);
		root.leftChild.rightChild.leftChild = new TreeNode(6);
		root.leftChild.rightChild.rightChild = new TreeNode(7);
		root.rightChild = new TreeNode(3);
		root.rightChild.leftChild = new TreeNode(8);
		root.rightChild.rightChild = new TreeNode(9);
		
		int target = 15;
		
		solve(root, target, 0 ,"");
	}
	
	public static void solve(TreeNode root, int target,int curVal, String route) {
		int val = curVal+root.val;
		if(root.leftChild == null && root.rightChild == null) {
			if(val == target) {
				System.out.println(route+" " + root.val);
			}
		}
		if(val > target)
			return;
		if(root.leftChild != null)
			solve(root.leftChild, target, val, route+" "+root.val);
		if(root.rightChild != null)
			solve(root.rightChild, target, val, route+" "+root.val);
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