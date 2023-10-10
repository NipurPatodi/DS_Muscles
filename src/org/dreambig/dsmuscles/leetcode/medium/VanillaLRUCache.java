package org.dreambig.dsmuscles.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/***
 * Vanilla Implementation of LRU using Doubly Link list and hashMap
 * Get and Put are O(1) time complexity
 */
public class VanillaLRUCache<K, V> {

    private static class Node<K, V> {
        final K key;
        public V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node<K, V> prev;
        public Node<K, V> next;

        public  String toString(){
            return String.format("Key=%s,Value=%s", key.toString(),value.toString());
        }
    }

    private final Map<K, Node<K, V>> nodeMap;
    private Node<K, V> head = null;
    private Node<K, V> tail = null;
    final int size;

    public VanillaLRUCache(int size) {
        nodeMap = new HashMap<>(size);
        this.size=size;

    }

    private void pushToHead(Node <K,V> node){
        if( node.prev!=null){
            node.prev.next= node.next;
            node.next.prev =node.prev;
            node.prev = null;
        }

        head.prev=node;
        node.next= head;
        head= node;
    }

    private void removeFromTail(){
        Node <K,V> node = nodeMap.get(tail.key);
        node.prev.next= null;
        tail= node.prev;
        nodeMap.remove(node.key);
    }

    public void put(K key, V value) {
        // if first element
        if (nodeMap.isEmpty()){
            Node<K,V> n = new Node<>(key,value);
            this.head =n;
            this.tail =n;
            nodeMap.put(key, n);
        }
        // if already exist
        else if(nodeMap.containsKey(key)){
            Node<K,V> node = nodeMap.get(key);
            node.value = value; // Value might have changed
            pushToHead(node);
        }
        // if Value does not exist
        else {
            // if Size is not available
            if(size == nodeMap.size()){
                removeFromTail();
            }
                Node<K,V> currNode = new Node<>(key,value);
                pushToHead(currNode);
                nodeMap.put(key, currNode);
            }

        }

        public V get(K key){
            if (!nodeMap.containsKey(key)){
                throw  new RuntimeException("Cache miss!");
            }
            Node<K,V> result =nodeMap.get(key);
            pushToHead(result);
            return result.value;
        }

        public void printCacheTrace(){
            Node<K,V> curr = head;
            System.out.println("\n");
            while(curr !=null){
                System.out.print(String.format( "[%s] -> ",curr));
                curr=curr.next;
            }
            System.out.print("null");
        }

    public static void main(String[] args) {
        VanillaLRUCache<Integer, String> cache = new VanillaLRUCache<>(3);
        cache.put(1,"Nipur");
        cache.put(2,"Moksh");
        cache.put(3,"Neera");
        cache.printCacheTrace();
        cache.get(2);
        cache.printCacheTrace();
        cache.put(4,"Rtk");
        cache.printCacheTrace();

    }

}
