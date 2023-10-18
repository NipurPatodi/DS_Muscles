package org.dreambig.dsmuscles.topic.greedy;

import org.dreambig.dsmuscles.leetcode.medium.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumActivity {

    static  int getMaxActivity(List<Pair<Integer,Integer>> activity){
        activity.sort((a, b) -> a.right - b.right);
        int res=1;
        Pair<Integer, Integer> prev =activity.get(0); // picking zero idx

        for ( int i=1;i<activity.size();i++){
            if(prev.right<=activity.get(i).left){
                res++;
                prev=activity.get(i);
            }
        }
        return res;

    }

    public static void main(String[] args) {
        List<Pair<Integer,Integer>>activity= new ArrayList<>();
        activity.add(new Pair<>(12,25));
        activity.add(new Pair<>(10,20));
        activity.add(new Pair<>(20,30));
        System.out.println(getMaxActivity(activity));
    }
}
