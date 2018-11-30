package ti25;


/*
 * �ϲ��������������
 * ����������������������ϲ�����������ʹ������Ҳ�ǵ��������
 */
public class Main1 {
	public static void main(String[] args) {
		ListNode head1 = new ListNode(2);
		head1.nextNode = new ListNode(2);
		head1.nextNode.nextNode = new ListNode(3);
		head1.nextNode.nextNode.nextNode = new ListNode(4);
		head1.nextNode.nextNode.nextNode.nextNode = new ListNode(5);
		head1.nextNode.nextNode.nextNode.nextNode.nextNode = new ListNode(6);
		
		ListNode head2 = new ListNode(1);
		head2.nextNode = new ListNode(2);
		head2.nextNode.nextNode = new ListNode(3);
		head2.nextNode.nextNode.nextNode = new ListNode(4);
		head2.nextNode.nextNode.nextNode.nextNode = new ListNode(5);
		head2.nextNode.nextNode.nextNode.nextNode.nextNode = new ListNode(6);
		
		if(head1.value <= head2.value)
			solve(head1, head2);
		else
			solve(head2, head1);
		
	}
	
	public static void solve(ListNode head1,ListNode head2) {
		if(head1.nextNode == null) {
			head1.nextNode = null;
		}else {
			ListNode cur1 = head1;
			ListNode next1 = head1.nextNode;
			ListNode cur2 = head2;
			while(true) {
				while(next1 != null && cur1.value < cur2.value) {
					cur1 = cur1.nextNode;
					next1 = next1.nextNode;
				}
				cur1.nextNode = cur2;
				if(next1 == null)
					break;
				while(cur2 != null && cur2.value <= next1.value) {
					cur2 = cur2.nextNode;
					cur1 = cur1.nextNode;
				}
				cur1.nextNode = next1;
				cur1 = cur1.nextNode;
				next1 = next1.nextNode;
			}
		}
		while(head1 != null) {
			System.out.print(head1.value + "  ");
			head1 = head1.nextNode;
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