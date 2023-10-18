package org.dreambig.dsmuscles.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityExpiryCache {

    // First creating Item Payload class
    public static class Item {

        public final String key, value;
        private final int priority, expireAfter;

        public Item(String key, String value, int priority, int expireAfter) {
            this.key = key;
            this.value = value;
            this.priority = priority;
            this.expireAfter = expireAfter;

        }
    }

    // Lets Create Node Class which will be wrapper around this Item

    public static class Node {
        public Node prev, next;
        public final Item data;

        public Node(Item data) {
            this.data = data;
        }
    }

    // Lets create A doubly linkList class with few usefull methods
    public static class DoublyLinkList {
        private int size;
        private Node head, tail;

        public DoublyLinkList(Node node) {
            this.head = node;
            this.tail = head;
            size++;
        }

        public void add(Node node) {
            if (size == 0) {
                this.head = node;
                this.tail = head;
            } else {
                // Always add to head
                node.next = head;
                head.prev = node;
                head = node;
            }
            size++;
        }

        public void remove(Node node) {
            if (size == 1) {
                head = null;
                tail = null;
            }
            if (node.prev != null)
                node.prev.next = node.next;
            if (node.next != null)
                node.next.prev = node.prev;
            node = null;
            size--;
        }

        public Node removeFromEnd() {
            Node n = tail;
            if (size == 1) {
                head = null;
                tail = null;
                return n;
            }
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return n;
        }
    }

    // Now constructing Data structures for PEC

    private final Map<String, Node> keyItemMap = new HashMap<>();
    private final Map<Integer, DoublyLinkList> prefToNodeListMap = new HashMap<>();
    private final Queue<Node> expiryHeap = new PriorityQueue<>((a, b) -> a.data.expireAfter - b.data.expireAfter);
    private final Queue<Node> prefHeap = new PriorityQueue<>((a, b) -> a.data.priority - b.data.priority);
    private final int maxSize;
    private int currSize;

    public PriorityExpiryCache(int size) {
        this.maxSize = size;
        this.currSize = 0;
    }

    // First Operation is get
    public String get(String key) {
        // Key is missing
        if (!keyItemMap.containsKey(key)) {
            return null;
        } else {
            Node node = keyItemMap.get(key);
            // this need to be movedin pref Queue
            DoublyLinkList prefDLL = prefToNodeListMap.get(node.data.priority);
            // Removing node 0(1)
            prefDLL.remove(node);
            // Adding it to top of list
            prefDLL.add(node); //0(1)
            return node.data.value;
        }
    }

    // Second is evict operation
    public void evict(int currentTS) {

        if (currSize == 0) return;
        // first check of expired Nodes
        Node expNode = expiryHeap.peek();
        // if expired
        currSize--;
        if (expNode.data.expireAfter < currentTS) {
            expiryHeap.remove(expNode);
            prefHeap.remove(expNode);
            DoublyLinkList prefDLL = prefToNodeListMap.get(expNode.data.priority);
            prefDLL.remove(expNode);
        }
        // else find node with lowest pref and based on LRU rule
        else {
            Node prefNode = prefHeap.peek(); //0(1)
            DoublyLinkList prefDLL = prefToNodeListMap.get(prefNode.data.priority);
            Node delNode = prefDLL.removeFromEnd();
            expiryHeap.remove(delNode);
            prefHeap.remove(delNode);

        }

    }

    // Thrid is put methods
    public void put(String key, String value, int priorty, int expireAfter, int currTS) {
        Item item = new Item(key, value, priorty, expireAfter);

        if (currSize == maxSize) {
            evict(currTS);
        }
        Node node = new Node(item);
        // First put in all DS
        keyItemMap.put(key, node);
        //Put in expiryHeap
        expiryHeap.add(node);
        //Pref Heap
        prefHeap.add(node);
        if (!prefToNodeListMap.containsKey(priorty)) {
            DoublyLinkList prefDLL = new DoublyLinkList(node);
            prefToNodeListMap.put(priorty, prefDLL);
        } else {
            DoublyLinkList prefDLL = prefToNodeListMap.get(priorty);
            prefDLL.add(node);
        }

        currSize++;

    }


    public static void main(String[] args) {
        PriorityExpiryCache cache = new PriorityExpiryCache(10);
        cache.put("1", "A", 1, 10,0);
        cache.put("2", "B", 2, 3,0);

    }


}
