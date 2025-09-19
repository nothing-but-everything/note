package com.wsh.leetcode.list;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = this.next = null;
        }
    }

    int capacity;
    int size;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public LRUCache() {
        this.capacity = 0;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>((int) ((capacity / 0.75) + 1));
    }

    public int get(int key) {
        Node node = this.map.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        addNodeToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = this.map.get(key);
        if (node == null) {
            node = new Node(key, value);
            this.map.put(key, node);
            addNodeToHead(node);
            this.size++;
            if (this.size > this.capacity) {
                Node last = this.tail.prev;
                this.map.remove(last.key);
                removeNode(this.tail.prev);
                this.size--;
            }
        } else {
            node.val = value;
            this.map.put(key, node);
            removeNode(node);
            addNodeToHead(node);
        }
    }

    private void addNodeToHead(Node node) {
        this.head.next.prev = node;
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next = node;
    }

    private int removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = node.prev = null;
        return node.val;
    }

}
