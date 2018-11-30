package ti24;


/*
 * ��ת����
 * ����һ������������һ�������ͷ��㣬��ת������
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.nextNode = new ListNode(2);
		head.nextNode.nextNode = new ListNode(3);
		head.nextNode.nextNode.nextNode = new ListNode(4);
		head.nextNode.nextNode.nextNode.nextNode = new ListNode(5);
		head.nextNode.nextNode.nextNode.nextNode.nextNode = new ListNode(6);
		
		solve(head);
	}
	public static void solve(ListNode head) {
		ListNode pre = null;
		ListNode next = head.nextNode;
		while(next != null) {
			head.nextNode = pre;
			
			pre = head;
			head = next;
			next = next.nextNode;
		}
		head.nextNode = pre;
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