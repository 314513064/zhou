package ti27;


/*
 * 二叉树的镜像
 * 请完成一个函数，输入一颗二叉树，该函数求出它的镜像。
 */
public class Main1 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode('a');
		root.leftChild = new TreeNode('b');
		root.leftChild.leftChild = new TreeNode('d');
		root.leftChild.rightChild = new TreeNode('e');
		root.leftChild.rightChild.leftChild = new TreeNode('h');
		root.leftChild.rightChild.rightChild = new TreeNode('i');
		root.rightChild = new TreeNode('c');
		root.rightChild.leftChild = new TreeNode('f');
		root.rightChild.rightChild = new TreeNode('g');
		
		mirror(root);
	}
	
	public static void mirror(TreeNode root) {
		if(root == null)
			return;
		TreeNode tmp = root.leftChild;
		root.leftChild = root.rightChild;
		root.rightChild = tmp;
		if(root.leftChild != null)
			mirror(root.leftChild);
		if(root.rightChild != null)
			mirror(root.rightChild);
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