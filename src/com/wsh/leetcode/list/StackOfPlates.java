package com.wsh.leetcode.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackOfPlates {

    int size;
    int capactiy;
    Stack<Integer> stack;
    List<Stack<Integer>> list;

    public StackOfPlates(int cap) {
        this.size = 0;
        this.capactiy = cap;
        this.stack = new Stack<>();
        this.list = new ArrayList<>();
    }

    public void push(int val) {
        if (this.stack.size() >= this.capactiy) {
            this.list.add(this.stack);
            this.stack = new Stack<>();
        }
        this.size++;
        this.stack.push(val);
    }

    public int pop() {
        if (this.stack == null || this.stack.size() == 0) {
            return -1;
        }
        int val = this.stack.pop();
        if (this.stack.size() == 0) {
            if (this.list.size() == 0) {
                return -1;
            } else {
                this.stack = this.list.get(this.list.size() - 1);
                this.list.remove(this.list.size() - 1);
            }
        }
        return val;
    }

    public int popAt(int index) {
        if (index == this.list.size()) {
            return this.stack.pop();
        }
        return 0;
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */
