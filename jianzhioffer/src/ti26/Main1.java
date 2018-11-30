package ti26;


/*
 * 树的子结构
 * 输入两颗二叉树A和B，判断B是不是A的子结构。
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
		
		TreeNode target = new TreeNode('c');
		target.leftChild = new TreeNode('f');
		target.rightChild = new TreeNode('g');
		
		System.out.println(solve(root, target));
	}
	
	public static boolean solve(TreeNode root, TreeNode target) {
		boolean flag = false;
		if(root != null && target != null) {
			flag = judge(root, target);
			if(!flag)
				flag = judge(root.leftChild, target);
			if(!flag)
				flag = judge(root.rightChild, target);
		}
		return flag;
	}
	
	public static boolean judge(TreeNode root, TreeNode target) {
		if(target == null)
			return true;
		if(root == null)
			return false;
		boolean flag = false;
		if(root.val == target.val) {
			flag = judge(root.leftChild, target.leftChild);
			if(flag)
				flag = judge(root.rightChild, target.rightChild);
		}
		return flag;
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