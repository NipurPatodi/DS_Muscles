package org.dreambig.dsmuscles.leetcode;

import java.util.*;

public class PriorityExpiryCache {
    static class Item {
        public Item(String key, String value, int priority, int expireAfter) {
            this.preference = priority;
            this.expireAfter = expireAfter;
            this.key = key;
            this.value = value;
        }

        int preference;
        int expireAfter;
        String key;
        String value;
    }

    static public class ListNode<T> {
        T data;
        ListNode<T> next;
        ListNode<T> prev;

        ListNode(T data) {
            this(null, data, null);
        }

        ListNode(ListNode<T> prev, T data, ListNode<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    static @SuppressWarnings("unchecked")
    public class DoublyLinkedList<T> {

        private ListNode<T> front;
        private ListNode<T> end;
        private int size;

        public DoublyLinkedList() {
            end = front = null;
        }

        public ListNode<T> addFront(T x) {
            ListNode<T> retVal;
            if (size == 0) {
                front = new ListNode<>(x);
                end = front;
                retVal = front;
            } else {
                ListNode<T> newNode = new ListNode<>(null, x, null);
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
                retVal = newNode;
            }
            size++;
            return retVal;
        }


        ListNode<T> removeLast() {
            ListNode<T> item = end;
            end = end.prev;
            size--;
            return item;
        }

        public void removeNode(ListNode node) {
            if (size == 0) {
                return;
            }

            if (size == 1) {
                end = front = node = null;
            } else {
                ListNode prev = node.prev;
                ListNode next = node.next;

                if (prev != null)
                    prev.next = next;

                if (next != null)
                    next.prev = prev;

                node = null;
            }

            size--;
        }

        public int size() {
            return size;
        }
    }

    int maxSize;
    int currSize;

    PriorityQueue<ListNode<Item>> pqByExpiryTime = new PriorityQueue<>((a, b) -> a.data.expireAfter - b.data.expireAfter);
    PriorityQueue<ListNode<Item>> pqByPreference = new PriorityQueue<>((a, b) -> a.data.preference - b.data.preference);
    HashMap<Integer, DoublyLinkedList<Item>> preferrenceToList = new HashMap<>();
    HashMap<String, ListNode<Item>> keyToItemNode = new HashMap<>();


    public PriorityExpiryCache(int maxSize) {
        this.maxSize = maxSize;
        this.currSize = 0;
        LinkedList<Item> l = new LinkedList<>();
    }

    public Set<String> getKeys() {
        return keyToItemNode.keySet();
    }


    /**
     * 1. Remove all expired items first
     * 2. If none are expired, evict the ones with lowest preference
     * 3. If there's a tie on items with least preference, evict the ones
     * which are least recently used.
     */
    public void evictItem(int currentTime) {

        if (currSize == 0) return;

        currSize--;

        // Check expired items first
        if (pqByExpiryTime.peek().data.expireAfter < currentTime) {

            ListNode<Item> node = pqByExpiryTime.poll();
            Item item = node.data;

            DoublyLinkedList<Item> dList = preferrenceToList.get(item.preference);
            dList.removeNode(node);

            // Remove from hashmap too
            if (dList.size() == 0) {
                preferrenceToList.remove(item.preference);
            }

            // Remove from hashmap
            keyToItemNode.remove(item.key);

            // Remove from preference queue too
            pqByPreference.remove(item);

            return;
        }

        // Next check if preference items are to be removed
        int preference = pqByPreference.poll().data.preference;

        DoublyLinkedList<Item> dList = preferrenceToList.get(preference);

        // Remove the end
        ListNode<Item> leastRecentlyUsedWithLeastPreference = dList.removeLast();
        keyToItemNode.remove(leastRecentlyUsedWithLeastPreference.data.key);

        // Remove from the expiry queue
        pqByExpiryTime.remove(leastRecentlyUsedWithLeastPreference);


        if (dList.size() == 0) {
            // Remove the dList too
            preferrenceToList.remove(dList);
        }
    }

    /**
     * Get the value of the key if the key exists in the cache and isn't expired.
     */
    public Item getItem(String key) {

        if (keyToItemNode.containsKey(key)) {
            ListNode<Item> node = keyToItemNode.get(key);
            Item itemToReturn = node.data;

            DoublyLinkedList<Item> dList = preferrenceToList.get(itemToReturn.preference);

            dList.removeNode(node);
            dList.addFront(itemToReturn);

            return itemToReturn;
        }

        return null;
    }

    /**
     * update or insert the value of the key with a preference value and expire time.
     * Set should never allow more items than maxItems to be in the cache. When evicting
     * we need to evict the lowest preference item(s) which are least recently used.
     */
    public void setItem(Item item, int currentTime) {

        if (currSize == maxSize) {
            evictItem(currentTime);
        }

        // Get the linkedlist for the preference queue
        DoublyLinkedList<Item> dlist = null;
        if (preferrenceToList.containsKey(item.preference)) {
            dlist = preferrenceToList.get(item.preference);
        } else {
            dlist = new DoublyLinkedList<>();
            preferrenceToList.put(item.preference, dlist);
        }

        ListNode<Item> node = dlist.addFront(item);
        keyToItemNode.put(item.key, node);

        // Update the expiry time pqueue
        pqByExpiryTime.add(node);

        // Update the preference pqueue
        pqByPreference.add(node);

        currSize++;
    }

    public static void main(String[] args) {
        PriorityExpiryCache priorityExpiryCache = new PriorityExpiryCache(5);
        priorityExpiryCache.setItem(new Item("A", "val1", 5, 100), 0);
        priorityExpiryCache.setItem(new Item("B", "val2", 15, 3), 0);
        priorityExpiryCache.setItem(new Item("C", "val3", 5, 10), 0);
        priorityExpiryCache.setItem(new Item("D", "val4", 1, 15), 0);
        priorityExpiryCache.setItem(new Item("E", "val5", 5, 150), 0);

        priorityExpiryCache.getItem("C");

        System.out.println(priorityExpiryCache.getKeys());

        priorityExpiryCache.evictItem(5);
        System.out.println(priorityExpiryCache.getKeys());

        priorityExpiryCache.evictItem(5);
        System.out.println(priorityExpiryCache.getKeys());

        priorityExpiryCache.evictItem(5);
        System.out.println(priorityExpiryCache.getKeys());

        priorityExpiryCache.evictItem(5);
        System.out.println(priorityExpiryCache.getKeys());
    }
}
