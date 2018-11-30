package ti18;
/*
 * 删除链表节点
 * 给定单向链表的头指针和一个节点指针，定义一个函数在O(1)时间内删除该节点。
 */
/*
 * 思路：将目标节点的值改为目标节点下一个节点的值，然后删除下一个节点，如果目标节点是尾节点，还是得遍历链表
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.nextNode = new ListNode(2);
		head.nextNode.nextNode = new ListNode(3);
		head.nextNode.nextNode.nextNode = new ListNode(4);
		ListNode target = head.nextNode.nextNode.nextNode;
		
		if(target.nextNode != null) {
			target.value = target.nextNode.value;
			target.nextNode = target.nextNode.nextNode;
		}else {
			ListNode tmp = head;
			while(tmp.nextNode != target)
				tmp = tmp.nextNode;
			tmp.nextNode = tmp.nextNode.nextNode;
		}
		while(head != null) {
			System.out.print(head.value + "  ");
			head = head.nextNode;
		}
	}
}

class ListNode{
	int value;
	ListNode nextNode;
	public ListNode(int value) {
		this.value = value;
	}
}
