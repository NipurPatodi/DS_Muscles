package org.dreambig.dsmuscles.leetcode.medium;

import java.util.*;

public class SnapshotArray {
    TreeMap<Integer, Integer>[] valueVersion;
    int currSnapShot;

    public SnapshotArray(int length) {
        valueVersion = new TreeMap[length];
        currSnapShot = 0;
        for(int i=0;i<3;i++){
          valueVersion[i]= new TreeMap<Integer,Integer>();
          valueVersion[i].put(0,0);
        }
    }

    public void set(int index, int val) {
        valueVersion[index].put(currSnapShot,val);
    }

    public int snap() {
        return currSnapShot++;

    }

    public int get(int index, int snap_id) {
      // this is crucks since when we increase snap , we are not moving data and
      // will not be able to find last valid value of array
        return valueVersion[index].floorEntry(snap_id).getValue();
    }
}
