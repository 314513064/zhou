package ti32;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 分行从上到下打印二叉树
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印
 * 每一层打印到一行
 */
public class Main2 {
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
		int count = 1;
		int nextCount = 0;
		while(!queue.isEmpty()) {
			nextCount = 0;
			for(int i = 0; i < count;i++) {
				TreeNode tmp = queue.poll();
				System.out.print(tmp.val + "  ");
				if(tmp.leftChild != null) {
					queue.offer(tmp.leftChild);
					nextCount++;
				}
				if(tmp.rightChild != null) {
					queue.offer(tmp.rightChild);
					nextCount++;
				}
			}
			System.out.println();
			count = nextCount;
		}
	}
}
