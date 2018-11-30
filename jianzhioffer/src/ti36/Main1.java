package ti36;

import java.util.Stack;

/*
 * 二叉搜索树与双向链表
 * 输入一颗二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 */
public class Main1 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.leftChild = new TreeNode(3);
		root.leftChild.leftChild = new TreeNode(2);
		root.leftChild.rightChild = new TreeNode(4);
		root.leftChild.leftChild.leftChild = new TreeNode(1);
		root.rightChild = new TreeNode(8);
		root.rightChild.leftChild = new TreeNode(7);
		root.rightChild.leftChild.leftChild = new TreeNode(6);
		root.rightChild.rightChild = new TreeNode(9);
		
		TreeNode head1 = solve(root);
		TreeNode head2 = head1;
		while(head1 != null) {
			System.out.print(head1.val + " ");
			head1 = head1.rightChild;
			if(head1 == null)
				break;
			head2 = head2.rightChild;
		}
		System.out.println();
		while(head2 != null) {
			System.out.print(head2.val + " ");
			head2 = head2.leftChild;
		}
	}
	
	public static TreeNode solve(TreeNode root) {
		TreeNode lastNode = null;
		convert(root, lastNode);
		
		while(root.leftChild != null)
			root = root.leftChild;
		return root;
	}
	public static void convert(TreeNode node,TreeNode lastNode) {
		if(node == null)
			return;
		
		
		if(node.leftChild != null) {
			convert(node.leftChild, lastNode);
			lastNode = node.leftChild;
			while(lastNode.rightChild != null)
				lastNode = lastNode.rightChild;
		}
		
		node.leftChild = lastNode;
		if(lastNode != null)
			lastNode.rightChild = node;
		lastNode = node;
		
		if(node.rightChild != null)
			convert(node.rightChild, lastNode);
	
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