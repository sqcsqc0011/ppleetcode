import Classes.ListNode;
import March.LeetCode1;

public class MainClass {

	public static void main(String[] orgs) {
		LeetCode1 l1 = new LeetCode1();
		
		String[] strs = new String[] {"aa","a"};
		int[] nums = new int[] {1,4};
		int[] nums2 = new int[] {1,2,3};
		
		ListNode node = ListNode.getNode(nums);
		ListNode node2 = ListNode.getNode(nums2);
		ListNode[] lists = new ListNode[] {node, node2};
		
		System.out.print(l1.mergeKLists(lists));
	}
}
