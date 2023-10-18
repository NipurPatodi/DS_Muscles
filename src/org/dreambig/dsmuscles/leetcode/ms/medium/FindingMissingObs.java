package org.dreambig.dsmuscles.leetcode.ms.medium;

/***
 * You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6. n of the observations went missing, and you only have the observations of m rolls. Fortunately, you have also calculated the average value of the n + m rolls.
 *
 * You are given an integer array rolls of length m where rolls[i] is the value of the ith observation. You are also given the two integers mean and n.
 *
 * Return an array of length n containing the missing observations such that the average value of the n + m rolls is exactly mean. If there are multiple valid answers, return any of them. If no such array exists, return an empty array.
 *
 * The average value of a set of k numbers is the sum of the numbers divided by k.
 *
 * Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.
 *
 *
 *
 * Example 1:
 *
 * Input: rolls = [3,2,4,3], mean = 4, n = 2
 * Output: [6,6]
 * Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
 * Example 2:
 *
 * Input: rolls = [1,5,6], mean = 3, n = 4
 * Output: [2,3,2,2]
 * Explanation: The mean of all n + m rolls is (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3.
 * Example 3:
 *
 * Input: rolls = [1,2,3,4], mean = 6, n = 4
 * Output: []
 * Explanation: It is impossible for the mean to be 6 no matter what the 4 missing rolls are.
 * link => https://leetcode.com/problems/find-missing-observations/description/
 */
public class FindingMissingObs {

  private boolean isSafe(int idx, int sum, int []sol, int val){
    // can't add this
    if( val>sum ) return false;
    if(idx== sol.length-1 && val != sum) return false;
    return true;
  }


    private boolean computeSolution(int sum, int []sol, int idx){
      if(idx==sol.length && sum >0) return false;

      if(idx==sol.length && sum ==0 )return true;

      for (int i=1;i<=6;i++){
        if(isSafe(idx, sum, sol,i )){
          sol[idx]=i;
          if(computeSolution(sum-i, sol, idx+1)) return true;
          sol[idx]=0;
        }
      }
      return false;

    }


    // Please note that this is back tracking solution is obiviously not very optimized .
    // Better solution for this is using math and reminder
    public int[] missingRollsNonOptimized(int[] rolls, int mean, int n) {
       int sum = mean * (rolls.length+n) ;

       for (int i=0;i<rolls.length;i++)
            sum-= rolls[i];

      int [] sol= new int [n];
      int [] res ={};
      if(computeSolution(sum, sol,0)){
        return sol;
      }
      return res;
  }

  /********************************
  Please note trick implementation
  ********************************/
    public int[] missingRolls(int[] rolls, int mean, int n){
      int sum = mean * (rolls.length+n) ;

      for (int i=0;i<rolls.length;i++)
           sum-= rolls[i];

      // base case to accept this is impossible
      if(sum <n || sum >6*n) return new int[0];

      int part = sum / n;
      int rem = sum%n;
      int[]res=new int [n];
      for (int i=0;i<n;i++){
        res[i]=part;
      }
      for (int i=0;i<rem; i++)
        res[i]++; // increasing equally

      return res;

    }







}
