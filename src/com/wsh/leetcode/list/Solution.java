package com.wsh.leetcode.list;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}

/*class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}*/

/*class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}*/

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    public ListNode sortList(ListNode head) {

    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head, post = head.next;
        while (post != null) {
            int val = gcd(current.val, post.val);
            ListNode node = new ListNode(val);
            node.next = post;
            current.next = node;
            current = post;
            post = post.next;
        }
        return head;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>((int) (nums.length / 0.75 + 1));
        for (Integer num : nums) {
            set.add(num);
        }
        ListNode current = head;
        int sum = 0;
        boolean flag = false;
        while (current != null) {
            if (set.contains(current.val)) {
                flag = true;
            } else {
                if (flag) {
                    sum++;
                    flag = false;
                }
            }
            current = current.next;
        }
        if (flag) {
            sum++;
        }
        return sum;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = new ListNode(), prev = node, left = head, right = node, tail;
        node.next = head;
        while (left != null) {
            for (int i = 0; i < k && right != null; i++) {
                right = right.next;
            }
            if (right == null) {
                break;
            }
            tail = right.next;
            left = reverseList(left, tail);

            left.next = tail;
            prev.next = right;

            left = tail;
            prev = left;
            right = left;
        }
        return node.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head, post;
        while (current != null) {
            post = current.next;
            current.next = prev;
            prev = current;
            current = post;
        }
        return prev;
    }

    public ListNode reverseList(ListNode head, ListNode after) {
        ListNode prev = null, current = head, post;
        while (current != after) {
            post = current.next;
            current.next = prev;
            prev = current;
            current = post;
        }
        return prev;
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode node = new ListNode(Integer.MIN_VALUE), prev = node, current = head, post, after;
        while (current != null) {
            post = current.next;
            current.next = null;

            prev = node;
            while (true) {
                if (prev.next == null) {
                    prev.next = current;
                    break;
                }
                if (current.val >= prev.val && prev.next.val >= current.val) {
                    current.next = prev.next;
                    prev.next = current;
                    break;
                }
                prev = prev.next;
            }

            current = post;
        }
        return node.next;
    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head, post, child, tail;
        while (current != null) {
            if (current.child != null) {
                post = current.next;
                child = current.child;
                tail = child;
                current.next = flatten(current.child);
                while (tail.next != null) {
                    tail = tail.next;
                }

                current.child = null;
                current.next.prev = current;
                tail.next = post;
                if (post != null) {
                    post.prev = tail;
                }
            }
            current = current.next;
        }
        return head;
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>((int) (nums.length / 0.75 + 1));
        for (int num : nums) {
            set.add(num);
        }
        ListNode node = new ListNode(), prev = node, current = head;
        while (current != null) {
            if (!set.contains(current.val)) {
                prev.next = current;
                prev = prev.next;
            }
            current = current.next;
        }
        prev.next = null;
        return node.next;
    }

    public ListNode doubleIt(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(), prev = node, current = reverseList(head);
        node.next = current;
        int sum = 0;
        while (current != null) {
            sum += current.val * 2;
            current.val = sum % 10;
            sum /= 10;
            prev.next = current;
            prev = prev.next;
            current = current.next;
        }
        if (sum != 0) {
            prev.next = new ListNode(sum);
            prev = prev.next;
        }
        return reverseList(node.next);
    }

    public ListNode removeNodes(ListNode head) {
        ListNode current = reverseList(head);
        ListNode node = new ListNode(), prev = node;
        int val = current.val - 1;
        while (current != null) {
            if (current.val >= val) {
                prev.next = current;
                prev = prev.next;
                val = current.val;
            }
            current = current.next;
        }
        prev.next = null;
        return reverseList(node.next);
    }

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        ListNode prev = head, current = head.next, post = current.next;
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (post != null) {
            if (current.val > prev.val && current.val > post.val || current.val < prev.val && current.val < post.val) {
                list.add(i);
            }
            i++;
            prev = prev.next;
            current = current.next;
            post = post.next;
        }
        if (list.size() < 2) {
            return new int[]{-1, -1};
        }
        int max = list.get(list.size() - 1) - list.get(0);
        int min = Integer.MAX_VALUE;
        for (int s = 1; s < list.size(); s++) {
            min = Math.min(min, list.get(s) - list.get(s - 1));
        }
        return new int[]{min, max};
    }

    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        slow = reverseList(slow);
        fast = head;
        int max = 0;
        while (fast != null) {
            max = Math.max(max, slow.val + fast.val);
            slow = slow.next;
            fast = fast.next;
        }
        return max;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode node = new ListNode(), left = node, right = node, prev = null, post = null;
        node.next = list1;
        while (a-- >= 0) {
            prev = left;
            left = left.next;
        }
        while (b-- >= 0) {
            right = right.next;
        }
        post = right.next;

        ListNode tail = list2;
        while (tail.next != null) {
            tail = tail.next;
        }
        prev.next = list2;
        tail.next = post;
        return list1;
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode node = new ListNode(), left = node, right = node, prev = node;
        node.next = head;
        for (int i = 0; i < k; i++) {
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        while (k-- > 0) {
            prev = prev.next;
        }
        int temp = left.val;
        left.val = prev.val;
        prev.val = temp;
        return node.next;
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode node = new ListNode(), prev = node, current = head;
        int sum = 0;
        while (current != null) {
            if (current.val == 0) {
                if (sum != 0) {
                    prev.next = new ListNode(sum);
                    prev = prev.next;
                    sum = 0;
                }
            } else {
                sum += current.val;
            }
            current = current.next;
        }
        return node.next;
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[]{};
        }
        int curLine = 1, nextLine = 0;
        TreeNode current = tree;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(current);
        List<ListNode> list = new ArrayList<>();
        ListNode node = new ListNode(), prev = node;
        while (!queue.isEmpty()) {
            current = queue.poll();
            curLine--;
            prev.next = new ListNode(current.val);
            prev = prev.next;

            if (current.left != null) {
                queue.offer(current.left);
                nextLine++;
            }
            if (current.right != null) {
                queue.offer(current.right);
                nextLine++;
            }

            if (curLine == 0) {
                curLine = nextLine;
                nextLine = 0;
                list.add(node.next);
                prev = node;
            }
        }
        ListNode[] result = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);
        return node;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node = new ListNode(), prev = null, current = node, post = node;
        node.next = head;
        while (right-- >= 0) {
            post = post.next;
        }
        while (left-- > 0) {
            prev = current;
            current = current.next;
        }
        ListNode temp = current;
        current = reverseList(current, post);
        prev.next = current;
        temp.next = post;
        return node.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode lessThan = new ListNode(), l1 = lessThan;
        ListNode moreThan = new ListNode(), l2 = moreThan;
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                l1.next = current;
                l1 = l1.next;
            } else {
                l2.next = current;
                l2 = l2.next;
            }
            current = current.next;
        }
        l2.next = null;
        l1.next = moreThan.next;
        return lessThan.next;
    }

    /*public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        int currentLine = 1, nextLine = 0;
        Node current = root, post = null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            current.next = post;
            post = current;
            currentLine--;

            if (current.right != null) {
                queue.offer(current.right);
                nextLine++;
            }
            if (current.left != null) {
                queue.offer(current.left);
                nextLine++;
            }

            if (currentLine == 0) {
                currentLine = nextLine;
                nextLine = 0;
                post = null;
            }
        }
        return root;
    }*/

    /*public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node node = new Node(0), prev = node, current = head;
        while (current != null) {
            prev.next = new Node(current.val);
            map.put(current, prev.next);
            prev = prev.next;
            current = current.next;
        }
        prev = node;
        current = head;
        while (current != null) {
            prev.next.random = map.get(current.random);
            prev = prev.next;
            current = current.next;
        }
        return node.next;
    }*/

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        slow = reverseList(slow);
        fast = head;
        ListNode node = new ListNode();
        prev = node;
        while (fast != null) {
            prev.next = fast;
            prev = prev.next;
            fast = fast.next;

            prev.next = slow;
            prev = prev.next;
            slow = slow.next;
        }
        prev.next = slow;
        head = node.next;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode odd = new ListNode(), l1 = odd;
        ListNode even = new ListNode(), l2 = even;
        ListNode current = head;
        while (current != null) {
            l1.next = current;
            l1 = l1.next;
            current = current.next;

            if (current == null) {
                break;
            }

            l2.next = current;
            l2 = l2.next;
            current = current.next;
        }
        l2.next = null;
        l1.next = even.next;
        return odd.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = new ListNode(head.val - 1), n = node, prev = node, current = head;
        node.next = head;
        while (current != null) {
            if (current.next == null) {
                if (prev.val != current.val) {
                    n.next = current;
                    n = n.next;
                }
                break;
            }
            if (prev.val != current.val && current.val != current.next.val) {
                n.next = current;
                n = n.next;
            }
            prev = prev.next;
            current = current.next;
        }
        n.next = null;
        return node.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = new ListNode(), left = node, right = node, post;
        int i = 0;
        node.next = head;
        while (left.next != null) {
            i++;
            left = left.next;
        }
        k %= i;
        if (k == 0) {
            return head;
        }
        if (k == 0) {
            return head;
        }
        left = node;
        while (k-- > 0) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        post = left.next;
        left.next = null;
        right.next = head;
        return post;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(), prev = node;
        int sum = 0;
        while (l1 != null && l2 != null) {
            sum += l1.val + l2.val;
            prev.next = new ListNode(sum % 10);
            sum /= 10;
            prev = prev.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            sum += l1.val;
            prev.next = new ListNode(sum % 10);
            sum /= 10;
            prev = prev.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            sum += l2.val;
            prev.next = new ListNode(sum % 10);
            sum /= 10;
            prev = prev.next;
            l2 = l2.next;
        }
        if (sum != 0) {
            prev.next = new ListNode(sum);
        }
        return node.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = new ListNode(), prev = node, left = head, right, post;
        node.next = head;
        while (left != null) {
            right = left.next;
            if (right == null) {
                break;
            }
            post = right.next;

            left.next = post;
            right.next = left;
            prev.next = right;

            prev = left;
            left = post;
        }
        return node.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = new ListNode(), left = node, right = node;
        node.next = head;
        while (n-- > 0) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return node.next;
    }

    public void deleteNode(ListNode node) {
        ListNode current = node, post = node.next;
        while (post.next != null) {
            current.val = post.val;
            current = current.next;
            post = post.next;
        }
        current.val = post.val;
        current.next = null;
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode node = new ListNode(), prev = node, current = head;
        while (current != null) {
            if (!set.contains(current.val)) {
                set.add(current.val);
                prev.next = current;
                prev = prev.next;
            }
            current = current.next;
        }
        prev.next = null;
        return node.next;
    }

    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode node = new ListNode(), left = node, right = node;
        node.next = head;
        while (cnt-- > 0) {
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }

    public int[] reverseBookList(ListNode head) {
        int i = 0;
        ListNode prev = null, current = head, post;
        while (current != null) {
            post = current.next;
            current.next = prev;
            prev = current;
            current = post;
            i++;
        }
        int[] result = new int[i];
        i = 0;
        current = prev;
        while (current != null) {
            result[i++] = current.val;
            current = current.next;
        }
        return result;
    }

    public int getDecimalValue(ListNode head) {
        ListNode current = reverseList(head);
        int sum = 0, i = 0;
        while (current != null) {
            if (current.val == 1) {
                sum += Math.pow(2, i);
            }
            i++;
            current = current.next;
        }
        return sum;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        slow = reverseList(slow);
        fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(), prev = node, current = head;
        while (current != null) {
            if (current.val != val) {
                prev.next = current;
                prev = prev.next;
            }
            current = current.next;
        }
        prev.next = null;
        return node.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(head.val + 1), prev = node, current = head;
        while (current != null) {
            if (current.val != prev.val) {
                prev.next = current;
                prev = prev.next;
            }
            current = current.next;
        }
        prev.next = null;
        return node.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode node = new ListNode(), current = node;
        ListNode l1 = list1, l2 = list2;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }
        return node.next;
    }

}
