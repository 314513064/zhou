package ti32;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 从上到下打印二叉树
 * 从上到下打印二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
		
		solve(root);
	}
	public static void solve(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode tmp = queue.poll();
			if(tmp.leftChild != null)
				queue.offer(tmp.leftChild);
			if(tmp.rightChild != null)
				queue.offer(tmp.rightChild);
			System.out.print(tmp.val + "  ");
		}
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