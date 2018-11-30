package ti28;


/*
 * 对称的二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 如果一颗二叉树和它的镜像一样，那么它是对称的。
 */
public class Main1 {
	public static void main(String[] args){
		TreeNode root = new TreeNode('a');
		root.leftChild = new TreeNode('b');
		root.leftChild.leftChild = new TreeNode('d');
		root.leftChild.rightChild = new TreeNode('e');
		root.leftChild.rightChild.leftChild = new TreeNode('h');
		root.leftChild.rightChild.rightChild = new TreeNode('i');
		root.rightChild = new TreeNode('c');
		root.rightChild.leftChild = new TreeNode('f');
		root.rightChild.rightChild = new TreeNode('g');
		
		solve(root);
	}
	
	public static void solve(TreeNode root) {
		System.out.println(compare(root,root));
	}
	
	public static boolean compare(TreeNode node1, TreeNode node2) {
		if(node1 == null && node2 == null)
			return true;
		if(node1 == null || node2 == null)
			return false;
		
		if(node1.val == node2.val) {
			return compare(node1.leftChild, node2.rightChild) && compare(node1.rightChild, node2.leftChild);
		}
		return false;
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