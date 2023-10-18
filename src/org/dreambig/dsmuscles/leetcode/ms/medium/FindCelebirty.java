package org.dreambig.dsmuscles.leetcode.ms.medium;


import java.util.HashSet;

public class FindCelebirty {

    boolean knows(int a, int b){
        return false;
    }




    public int findCelebrity(int n) {
     int possibleCeleb =0 ;
     for (int i=1;i<n;i++){
         if(knows(possibleCeleb,i)){
             possibleCeleb=i;
         }
     }
     for (int i=0;i<n;i++){
         if(possibleCeleb==i) continue;
         if(!knows(i, possibleCeleb)||knows(possibleCeleb,i))
             return -1;
     }
     return  possibleCeleb;
    }

}
