package org.dreambig.dsmuscles.leetcode.medium;

/***
 * The problem statement requires us to design a cache with the following methods:
 *
 * get(String key)
 * set(String key, String value, int priority, int expiry)
 * evictItem(int currentTime)
 * The rules by which the cache operates is are follows:
 *
 * If an expired item is available. Remove it. If multiple items have the same expiry, removing any one suffices.
 * If condition #1 can’t be satisfied, remove an item with the least priority.
 * If more than one item satisfies condition #2, remove the least recently used one.
 * Multiple items can have the same priority and expiry.
 * Untold rules:
 *
 * All of those operations should be O(1) time and space complexity.
 */
public class LRUCache {

    public String get(String key){
        throw  new UnsupportedOperationException("Missing implementation");
    }

    public String set (String key, String value, int priority, int expiry){
        throw  new UnsupportedOperationException("Missing implementation");
    }

    public void evictItem(int currTime){

    }


}
