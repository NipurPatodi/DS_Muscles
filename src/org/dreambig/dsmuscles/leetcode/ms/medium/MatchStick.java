package org.dreambig.dsmuscles.leetcode.ms.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/***
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 *
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 * link=> https://leetcode.com/problems/matchsticks-to-square/description/
 */
public class MatchStick {

  public boolean splitTo4(int [] nums, int currSum, int targetedSum, int idx, char[] visited, int cnt, Map<String, Boolean> cache){

    if( cnt==3 &&currSum== targetedSum) return true;

    if(currSum>targetedSum) return false;

    if(currSum==targetedSum){
      splitTo4(nums, 0, targetedSum, 0, visited, cnt+1, cache);
    }

    String visitedStr= String.valueOf(visited);
    if( cache.containsKey(visitedStr)){
      return cache.get(visitedStr);
    }

    for( int i=idx; i<nums.length;i++){
      if(visited[i]!='1'){
        visited[i]='1';
        if(splitTo4(nums, currSum+nums[i], targetedSum, idx+1, visited, cnt, cache)) {
          cache.put(String.valueOf(visited), true);
          return true;}
        visited[i]='0';
        }
      }
    cache.put(String.valueOf(visited), false);
    return false;


    }








  public boolean makesquare(int[] matchsticks) {
      int sum = 0;
      for (int i=0;i<matchsticks.length;i++){
        sum+=matchsticks[i];
      }
      //cannot split it into 4 cuts
      if(sum %4 !=0) return false;

      int pSum = sum/4;



      Arrays.sort(matchsticks);
      int[] finalNums = matchsticks;
      matchsticks = IntStream.rangeClosed(1, finalNums.length).map(i -> finalNums[finalNums.length - i]).toArray();

      // base case
      Map<String, Boolean> cache= new HashMap<>();

      return splitTo4(matchsticks,0, pSum, 0, new char[matchsticks.length],0,cache);

    }


    public static void main(String[] args) {
        int [] nums= {1,1,2,2,2};
        MatchStick init= new MatchStick();
        System.out.println(init.makesquare(nums));
    }


}
