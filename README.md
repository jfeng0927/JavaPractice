# JavaPractice

## Algorithm
practice algorithm implementation. algorithms are from:
1. famous algorithm
2. introduction to algorithm
3. geeksforgeeks
4. articles

## Backtracking
special practice for backtracking algorithm

## Basic
JAVA basic concept understanding.

## cc150
Cracking Code Interview Problems

## Database
JAVA Database Manager and Connection

## Design Patterns
Design pattern for JAVA. Original ideas are from:
1. GoF Design Pattern: Elements of Reusable Object-Oriented Software
2. Head First Design Pattern
3. ISBN: 9787302162063

Details:
- Factory
- Strategy

## leetcode
Leetcode Algorithm problems

##### Hash Table
1. Two Sum<br>
Q: Given an array of integers, find two numbers such that they add up to a specific target number.<br>
A: use HashMap to map value to its index. O(n)<br>
lookup map for target-value. O(1)<br>

```
public int[] twoSum(int[] nums, int target) {
  int[] res = new int[2];
  HashMap<Integer, Integer> map = new HashMap<>();
  
  for(int i=0; i<nums.length; i++) {
      if(map.containsKey(target-nums[i])) {
          int idx = map.get(target-nums[i]);
          res[0] = (idx>i)?i+1:idx+1;
          res[1] = (idx>i)?idx+1:i+1;
      }
      map.put(nums[i], i);
  }
  
  return res;
}
```

2. Longest Substring Without Repeating Characters <br>
Q: Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.<br>
A: use hash map to record each character latest index. mark starting point of current substring. calculate length and compare<br>

```
public int lengthOfLongestSubstring(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    int len = 0;
    int start = 0;
    
    for(int i=0; i<s.length(); i++) {
        char cur = s.charAt(i);
        if(map.containsKey(cur)) {
            start = Math.max(map.get(cur)+1, start);
        }
        map.put(cur, i);
        len = Math.max(len, i-start+1);
    }
    
    return len;
}
```

##### Linked List
1. Add Two Numbers<br>
Q: You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.<br>
A: iterative add from left to right, use flag to mark over 10 sum. O(n)<br>

```
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
   ListNode dummy = new ListNode(0);
   ListNode pnt = dummy;
   dummy.next = pnt;
   
   boolean overflow = false;
   while(l1!=null && l2!=null) {
       int sum = (overflow)?1+l1.val+l2.val:l1.val+l2.val;
       if(sum>=10) {
           sum -= 10;
           overflow = true;
       } else {
           overflow = false;
       }
       ListNode tmp = new ListNode(sum);
       pnt.next = tmp;
       pnt = tmp;
       l1 = l1.next;
       l2 = l2.next;
   }
   ListNode l = (l1!=null)?l1:l2;
   while(l!=null) {
       int sum = (overflow)?1+l.val:l.val;
       if(sum>=10) {
           sum -= 10;
           overflow = true;
       } else {
           overflow = false;
       }
       ListNode tmp = new ListNode(sum);
       pnt.next = tmp;
       pnt = tmp;
       l = l.next;
   }
   if(overflow) {
       ListNode tmp = new ListNode(1);
       pnt.next = tmp;
   }
   
   return dummy.next;
}
```
##### String

1. Longest Palindromic Substring
Q: Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
A: My thought is to use dp to store the possible substring that is palindrome. With incresing length, we can mark the later one as the longest palindrome.
```
public String longestPalindrome(String s) {
    int len = s.length();
    boolean[][] dp = new boolean[len][len];
    int start = 0;
    int end = 0;
    
    for(int i=0; i<len; i++) {
        dp[i][i] = true;
        start = i;
        end = i+1;
    }
    
    for(int i=1; i<len; i++) {
        for(int j=0; j<len && i+j<len; j++) {
            if(s.charAt(j)==s.charAt(i+j)) {
                dp[j][i+j] = true;
                if(i>1) {
                    dp[j][i+j] &= dp[j+1][i+j-1];
                }
                if(dp[j][i+j]) {
                    start = j;
                    end = i+j+1;
                }
            }
        }
    }
    
    return s.substring(start, end);
}
```

2. ZigZag Conversion
Q: The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
```
public String convert(String s, int numRows) {
    if(numRows == 1) {
        return s;
    }
    int len = s.length();
    List<StringBuilder> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    
    for(int i=0; i<numRows; i++) {
        list.add(new StringBuilder());
    }
    for(int i=0; i<len; i++) {
        int idx = i%(numRows+numRows-2);
        if(idx>=numRows) {
            idx = numRows+numRows-2-idx;
        }
        list.get(idx).append(s.charAt(i));
    }
    for(int i=0; i<numRows; i++) {
        sb.append(list.get(i));
    }
    
    return sb.toString();
}
```

3. Longest Common Prefix
```
public String longestCommonPrefix(String[] strs) {
    if(strs.length <= 0) {
        return "";
    }
    
    for(int i=1; i<strs.length; i++) {
        if(strs[i].length()<strs[0].length()) {
            strs[0] = strs[0].substring(0, strs[i].length());
        }
        for(int j=0; j<strs[i].length(); j++) {
            if(j<strs[0].length() && strs[0].charAt(j)!=strs[i].charAt(j)) {
                strs[0] = strs[0].substring(0, j);
                break;
            }
        }
    }
    return strs[0];
}
```

4. String to Integer (atoi)
```
public int myAtoi(String str) {
    int len = str.length();
    if(len==0) {
        return 0;
    }
    int pnt = 0;
    long sum = 0;
    int sign = 1;
    while(pnt<len && str.charAt(pnt)==' ') {
        pnt++;
    }
    if(pnt<len && (str.charAt(pnt)=='+' || str.charAt(pnt)=='-')) {
        sign = (str.charAt(pnt)=='+')?1:-1;
        pnt++;
    }
    while(pnt<len && str.charAt(pnt)>='0' && str.charAt(pnt)<='9') {
        sum = sum*10+str.charAt(pnt)-'0';
        if(sum>Integer.MAX_VALUE || sum*sign<Integer.MIN_VALUE) {
            return (sign<0)?Integer.MIN_VALUE:Integer.MAX_VALUE;
        }
        pnt++;
    }
    return (int)sum*sign;
}
```

##### Math
1. Reverse Integer
Q: Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321


```
public int reverse(int x) {
    long res = 0;
    boolean flag = (x<0)?true:false;
    x = (flag)?-x:x;
    
    while(x>0) {
        res = res*10+(x%10);
        x /= 10;
    }
    
    if(res > Integer.MAX_VALUE) {
        return 0;
    }
    if(-res < Integer.MIN_VALUE) {
        return 0;
    }
    
    return (flag)?(int)-res:(int)res;
}
```

2. Palindrome Number
Q: Determine whether an integer is a palindrome. Do this without extra space.
A: reverse the number and compare.
Better Idea:
ref: https://leetcode.com/discuss/23563/line-accepted-java-code-without-the-need-handling-overflow
reverse half of the number and compare.
```
public boolean isPalindrome(int x) {
    long res = reverse(x);
    if(res>Integer.MAX_VALUE || res<Integer.MIN_VALUE) {
        return false;
    }
    return ((int)res==x);
}

public long reverse(int x) {
    long res = 0;
    while(x>0) {
        res = res*10+(x%10);
        x /= 10;
    }
    return res;
}
```

3. Integer to Roman
Q: Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
```
public String intToRoman(int num) {
    HashMap<Integer, Character> map = new HashMap<>();
    char[] vals = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    int[] keys = new int[] {1, 5, 10, 50, 100, 500, 1000};
    for(int i=0; i<keys.length; i++) {
        map.put(keys[i], vals[i]);
    }
    
    StringBuilder sb = new StringBuilder();
    int[] base = new int[]{1000, 100, 10, 1};
    
    for(int i=0; i<4; i++) {
        int b = base[i];
        int m = num/b;
        while(m>0) {
            if(m==9) {
                num -= 9*b;
                sb.append(map.get(b));
                sb.append(map.get(b*10));
                m-=9;
            } else if(m==4) {
                num -= 4*b;
                sb.append(map.get(b));
                sb.append(map.get(b*5));
                m-=4;
            } else if(m>=5) {
                num -= 5*b;
                sb.append(map.get(b*5));
                m-=5;
            } else {
                num -= 1*b;
                sb.append(map.get(b));
                m--;
            }
        }   
    }
    
    return sb.toString();
}
```

4. Roman to Integer
Q: Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
```
public int romanToInt(String s) {
    int res = 0;
    for(int i=0; i<s.length(); i++) {
        switch(s.charAt(i)) {
            case 'M':
                res += 1000;
                break;
            case 'D':
                res += 500;
                break;
            case 'C':
                if(i+1<s.length() && (s.charAt(i+1)=='M' || s.charAt(i+1)=='D')) {
                    res -= 100;
                } else {
                    res += 100;
                }
                break;
            case 'L':
                res += 50;
                break;
            case 'X':
                if(i+1<s.length() && (s.charAt(i+1)=='L' || s.charAt(i+1)=='C')) {
                    res -= 10;
                } else {
                    res += 10;
                }
                break;
            case 'V':
                res += 5;
                break;
            case 'I':
                if(i+1<s.length() && (s.charAt(i+1)=='V' || s.charAt(i+1)=='X')) {
                    res -= 1;
                } else {
                    res += 1;
                }
                break;
        }
    }
    return res;
}
```

##### Divide and Conquer
1. Median of Two Sorted Arrays
Q: There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
```
public double findMedianSortedArrays(int A[], int B[]) {
    int lenA = A.length;
	int lenB = B.length;
	
	if((lenA+lenB)%2 != 0) {
		return (double)findMedian(A, B, (lenA+lenB)/2, 0, lenA-1, 0, lenB-1);
	} else {
		return (findMedian(A, B, (lenA+lenB)/2, 0, lenA-1, 0, lenB-1)+findMedian(A, B, (lenA+lenB)/2-1, 0, lenA-1, 0, lenB-1))*0.5;
	}
}

public int findMedian(int A[], int B[], int k, int startA, int endA, int startB, int endB) {
	int lenA = endA-startA+1;
	int lenB = endB-startB+1;
	
	if(lenA == 0) {
		return B[startB+k];
	}
	if(lenB == 0) {
		return A[startA+k];
	}
	if(k == 0) {
		return A[startA]<B[startB]?A[startA]:B[startB];
	}
	
	int midA = lenA*k/(lenA+lenB);
	int midB = k-midA-1;
	
	midA = midA+startA;
	midB = midB+startB;
	
	if(A[midA] > B[midB]) {
		k -= (midB-startB+1);
		endA = midA;
		startB = midB+1;
	} else {
		k -= (midA-startA+1);
		endB = midB;
		startA = midA+1;
	}
	
	return findMedian(A, B, k, startA, endA, startB, endB);
}
```

##### Two Pointer
1. Container With Most Water
Q: Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
```
public int maxArea(int[] height) {
    int start=0, end=height.length-1, sum=0, h=0;
    
    while(start<end) {
        if(height[start]>height[end]) {
            h = Math.max(h, height[end]);
            end--;
        } else {
            h = Math.max(h, height[start]);
            start++;
        }
        sum = Math.max(sum, h*(end-start+1));
    }
    
    return sum;
}
```

## lintcode
Lintcode Algorithm problems
