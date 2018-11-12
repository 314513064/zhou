package ti06;
/*
 * 翻转链表（单向链表）
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head = new ListNode(6);
		head.nextNode = new ListNode(5);
		head.nextNode.nextNode = new ListNode(4);
		head.nextNode.nextNode.nextNode = new ListNode(3);
		
		ListNode pre = null;
		ListNode next = head.nextNode;
		
		while(true) {
			head.nextNode = pre;
			
			pre = head;
			if(next == null)
				break;
			head = next;
			next = next.nextNode;
		}
		
		while(head!=null) {
			System.out.print(head.val+" ");
			head = head.nextNode;
		}
	}
}
class ListNode{
	int val;
	ListNode nextNode;
	public ListNode(int val) {
		this.val = val;
	}
}
