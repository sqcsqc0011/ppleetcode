package March;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import Classes.ListNode;

public class LeetCode1 {

	
	//1. Two Sum
	public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
        		if(maps.containsKey(target - nums[i])) {
        			return new int[] {maps.get(target - nums[i]), i};
        		}
            	maps.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
	
	//2. Add Two Numbers
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(-1);
		ListNode head = res;
		int over = 0;
		while(l1 != null || l2 != null || over != 0) {
			int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + over;
			over = num/10;
			num = num%10;
			head.next = new ListNode(num);
			head = head.next;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
        return res.next;
    }
	
	//3. Longest Substring Without Repeating Characters
	public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, max = 0;
        int[] chs = new int[128];
        for(; j < s.length(); j++) {
			i = Math.max(chs[s.charAt(j)], i);
    			max = Math.max(j + 1 - i, max);
    			chs[s.charAt(j)] = j + 1;
        }
        return max;
    }
	
	//4. Median of Two Sorted Arrays
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length > nums2.length) return findMedianSortedArraysHelper(nums2, nums1);
		return findMedianSortedArraysHelper(nums1, nums2);
    }

	public double findMedianSortedArraysHelper(int[] A, int[] B) {
		int m = A.length, n = B.length, halfLen = (m + n + 1)/2;
		int iMin = 0, iMax = m, i = 0, j = 0;
		while(iMin <= iMax) {
			i = (iMin + iMax)/2;
			j = halfLen - i;
			if(i > iMin && A[i - 1] > B[j]) {
				iMax--;
			} else if (i < iMax && A[i] < B[j - 1]) {
				iMin++;
			} else {
				int MaxL = 0;
				if(i == 0) MaxL = B[j - 1];
				else if(j == 0) MaxL = A[i - 1];
				else MaxL = Math.max(A[i - 1], B[j - 1]);
				if( (m + n)%2 == 1) return MaxL;
				int MinR = 0;
				if(i == m) MinR = B[j];
				else if(j == n) MinR = A[i];
				else MinR = Math.min(A[i], B[j]);
				return (MaxL + MinR)/2.0;
			}
		}
		return 0;
	}
	
	//5. Longest Palindromic Substring
	public String longestPalindrome(String s) {
		int l = 0, r = 0;
		for(int i = 0; i < s.length(); i++) {
			int len1 = longestPalindromeHelper(s, i, i);
			int len2 = longestPalindromeHelper(s, i, i + 1);
			int len = Math.max(len1, len2);
			if(len > r - l + 1) {
				l = i - (len - 1)/2;
				r = i + len/2;
			}
		}
		return s.substring(l, r + 1);
    }
	private int longestPalindromeHelper(String s, int l, int r) {
		while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}
	
	//6. ZigZag Conversion
	public String convert(String s, int r) {
		if(r <= 1) return s;
		String res = "";
        int len = s.length();
        for(int i = 1; i <= r; i++) {
        		int cur = i;
        		while(cur <= len) {
    				res += s.charAt(cur - 1);
        			if((cur - 1)%(r - 1) != 0 && cur + 2 * (r - i) <= len) {
        				res += s.charAt(cur + 2 * (r - i) - 1);
        			}
    				cur += 2 * (r - 1);
        		}
        }
        return res;
    }
	
	//7. Reverse Integer
	public int reverse(int x) {
        int res = 0, sign = res < 0 ? -1 : 1;
        x = x * sign;
        while(x != 0) {
    			if ((res * 10 + x%10 - x%10) / 10 != res) return 0;
        		res = res * 10 + x%10;
        		x = x/10;
        }
        return res * sign;
    }
	
	//8. String to Integer (atoi)
	public static int myAtoi(String str) {
	    if (str.isEmpty()) return 0;
	    int sign = 1, base = 0, i = 0;
	    while (str.charAt(i) == ' ')
	        i++;
	    if (str.charAt(i) == '-' || str.charAt(i) == '+')
	        sign = str.charAt(i++) == '-' ? -1 : 1;
	    while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
	        if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
	            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
	        }
	        base = 10 * base + (str.charAt(i++) - '0');
	    }
	    return base * sign;
	}
	
	//9. Palindrome Number
	//consider the boundry conditions
	public boolean isPalindrome(int x) {
		if(x < 0 || (x % 10 == 0 && x > 0)) return false;
        int revert = 0;
        while(revert <= x) {
        		if(revert == x || revert == x / 10) return true;
        		revert = revert * 10 + x % 10;
        		x = x / 10;
        }
        return false;
    }
	
	//10. Regular Expression Matching
	//dynamic programming, consider the boundry conditions first!!!!!!!!!!!!!!!
	public boolean isMatch(String s, String p) {
		if (s == null || p == null) return false;
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int i = 0; i < p.length(); i++) {
	        if (p.charAt(i) == '*' && dp[0][i - 1]) dp[0][i+1] = true;
	    }
		for(int i = 0; i < s.length(); i++) {
			for(int j = 0; j < p.length(); j++) {
				char si = s.charAt(i), pj = p.charAt(j);
				if(si == pj || pj == '.') {
					dp[i + 1][j + 1] = dp[i][j];
				} else if( pj == '*' && j > 0) {
					if(p.charAt(j - 1) != si && p.charAt(j - 1) != '.') {
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1]; //empty || single || multiple
					}
				}
			}
		}
		return dp[s.length()][p.length()];
    }
	
	//11. Container With Most Water
	public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, res = 0;
        while(l < r) {
        		res = Math.max(res, (r - l) * Math.min(height[l], height[r]));
        		if(height[l] < height[r]) l++;
        		else r--;
        }
        return res;
    }
	
	//12. Integer to Roman
	public String intToRoman(int num) {
		String M[] = {"", "M", "MM", "MMM"};
		String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
	
	//13. Roman to Integer
	public int romanToInt(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			switch (c) {
			case 'I':
				res += (res >= 5 ? -1 : 1);
				break;
			case 'V':
				res += 5;
				break;
			case 'X':
				res += 10 * (res >= 50 ? -1 : 1);
				break;
			case 'L':
				res += 50;
				break;
			case 'C':
				res += 100 * (res >= 500 ? -1 : 1);
				break;
			case 'D':
				res += 500;
				break;
			case 'M':
				res += 1000;
				break;
			}
		}
		return res;
    }
	
	//14. Longest Common Prefix
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 0) return "";
		String res = strs[0];
		int i = 1;
	    while(i < strs.length){
	        while(strs[i].indexOf(res) != 0)
	        		res = res.substring(0, res.length() - 1);
	        i++;
	    }
	    return res;
    }
	
	//15. 3Sum
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(int i = 0; i < nums.length - 2; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) continue;
			int t = -nums[i];
			int l = i + 1, r = nums.length - 1;
			while(l < r) {
				if(nums[l] + nums[r] == t) {
					res.add(Arrays.asList(nums[i], nums[l], nums[r]));
					while (l < r && nums[l] == nums[l + 1]) l++;
					while (l < r && nums[r] == nums[r - 1]) r--;
					l++;
					r--;
				} else if (nums[l] + nums[r] < t) l++;
				else r--;
			}
		}
		return res;
    }
	
	//16. 3Sum Closest
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int res = nums[0] + nums[1] + nums[2], minGap = Math.abs(res - target);
		for(int i = 0; i < nums.length - 2; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) continue;
			int l = i + 1, r = nums.length - 1;
			while(l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if(sum > target) {
					r--;
				} else {
					l++;
				}
				if(Math.abs(sum - target) < minGap) {
					res = sum;
					minGap = Math.abs(res - target);
				}
			}
		}
		return res;
    }
	
	//17. Letter Combinations of a Phone Number
	public List<String> letterCombinations(String digits) {
	    String[] maps = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	    List<String> res = new ArrayList<String>();
	    if(digits.isEmpty()) return res;
	    res.add("");
	    for(int i = 0; i < digits.length(); i++) {
	    		char ch = digits.charAt(i);
	    		if(ch >= '2' && ch <= '9') {
	    			List<String> add = new ArrayList<String>();
	    			for(int j = 0; j < res.size(); j++) {
	    				String chs = maps[ch - '0'];
	    				for(int k = 0; k < chs.length(); k++) {
	    					add.add(res.get(j) + chs.charAt(k));
	    				}
	    			}
	    			res = add;
	    		}
	    }
	    return res;
    }
	
	//18. 4Sum
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		for(int i = 0; i < nums.length - 3; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) continue;
			for(int j = i + 1; j < nums.length - 2; j++) {
				if(j > i + 1 && nums[j] == nums[j - 1]) continue;
				int t = target - nums[i] - nums[j];
				int l = j + 1, r = nums.length - 1;
				while(l < r) {
					if(nums[l] + nums[r] == t) {
						res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
						while(l < r && nums[l] == nums[l + 1]) l++;
						while(l < r && nums[r] == nums[r - 1]) r--;
						l++;
						r--;
					} else if(nums[l] + nums[r] < t) l++;
					else r--;
				}
			}
		}
		return res;
    }
	
	//19. Remove Nth Node From End of List
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode fast = dummy, snow = dummy;
		for(int i = 1; i <= n + 1; i++) {
			fast = fast.next;
		}
		while(fast != null) {
			fast = fast.next;
			snow = snow.next;
		}
		snow.next = snow.next.next;
		return dummy.next;
    }
	
	//20. Valid Parentheses
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
    }
	
	//21. Merge Two Sorted Lists
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(-1);
		ListNode cur = res;
		while(l1 != null || l2 != null) {
			if(l1 == null) {
				cur.next = l2;
				l2 = null;
			}
			else if(l2 == null) {
				cur.next = l1;
				l1 = null;
			}
			else {
				if(l1.val < l2.val) {
					cur.next = l1;
					l1 = l1.next;
				} else {
					cur.next = l2;
					l2 = l2.next;
				}
				cur = cur.next;
			}
		}
		return res.next;
    }
	
	//22. Generate Parentheses
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		generateParenthesis_helper(res, "", 0, 0, n);
		return res;
    }
	private void generateParenthesis_helper(List<String> res, String cur, int open, int close, int t) {
		if(open + close == t * 2) {
			res.add(cur);
			return;
		}
		if(open < t) generateParenthesis_helper(res, cur + "(", open + 1, close, t);
		if(close < open) generateParenthesis_helper(res, cur + ")", open, close, t);
	}
	
	
	
	
	
	
	
	
}
