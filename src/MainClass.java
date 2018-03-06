import Classes.ListNode;
import March.LeetCode1;

public class MainClass {

	public static void main(String[] orgs) {
		LeetCode1 l1 = new LeetCode1();
		
		String[] strs = new String[] {"aa","a"};
		int[] nums = new int[] {1,2,3,4,5};
		
		ListNode node = ListNode.getNode(nums);
		
		System.out.print(l1.isValid("[]"));
	}
}
