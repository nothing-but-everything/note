package com.wsh.leetcode.list;

import java.util.HashSet;
import java.util.Set;

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

class Node {
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
}

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

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        return null;
    }

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
