package com.wsh.leetcode.binaryTree;

import com.sun.source.tree.Tree;

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

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /*public boolean checkSymmetricTree(TreeNode root) {
        TreeNode current = copyTree(root);
        return isSameTree(current,root);
    }*/

    public TreeNode copyTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode current = new TreeNode(root.val);
        current.left = copyTree(root.right);
        current.right = copyTree(root.left);
        return current;
    }

    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        numColor(root, set);
        return set.size();
    }

    public void numColor(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        numColor(root.left, set);
        numColor(root.right, set);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        return rangeSumBST(root, low, high, 0);
    }

    public int rangeSumBST(TreeNode root, int low, int high, int sum) {
        if (root == null) {
            return sum;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high, sum);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high, sum);
        }
        return root.val + rangeSumBST(root.left, low, high, sum) + rangeSumBST(root.right, low, high, sum);
    }

    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new TreeNode();
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }


    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, left, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, right);
        return node;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode current = root;
        double sum = 0.0d;
        int curLine = 1, curSize = 1, nextLine = 0;
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            curLine--;
            sum += current.val;

            if (current.left != null) {
                queue.offer(current.left);
                nextLine++;
            }
            if (current.right != null) {
                queue.offer(current.right);
                nextLine++;
            }

            if (curLine == 0) {
                list.add((double) (sum / curSize));
                curLine = nextLine;
                curSize = nextLine;
                nextLine = 0;
                sum = 0.0d;
            }
        }
        return list;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode current = root;
        boolean flag = false;
        int curLine = 1, nextLine = 0;
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            curLine--;
            list.add(current.val);

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
                if (flag) {
                    Collections.reverse(list);
                }
                flag = !flag;
                result.add(list);
                list = new ArrayList<>();
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode current = root;
        int curLine = 1, nextLine = 0;
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            curLine--;
            list.add(current.val);

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
                result.add(0, list);
                list = new ArrayList<>();
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode current = root;
        int curLine = 1, nextLine = 0;
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            curLine--;
            list.add(current.val);

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
                result.add(list);
                list = new ArrayList<>();
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            list.add(current.val);
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            list.add(current.val);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                list.add(current.val);
                current = current.right;
            }
        }
        return list;
    }

}
