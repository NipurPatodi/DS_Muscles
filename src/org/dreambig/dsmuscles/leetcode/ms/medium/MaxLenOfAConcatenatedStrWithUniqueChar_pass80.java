/*
package org.dreambig.dsmuscles.leetcode.ms.medium;

import java.util.List;
import java.util.Set;

*/
/***
 * You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
 *
 * Return the maximum possible length of s.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All the valid concatenations are:
 * - ""
 * - "un"
 * - "iq"
 * - "ue"
 * - "uniq" ("un" + "iq")
 * - "ique" ("iq" + "ue")
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * Explanation: The only string in arr has all 26 characters.
 *
 *
 * link => https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/description/
 *//*

public class MaxLenOfAConcatenatedStrWithUniqueChar {

    private boolean isUnique(Set<Character> cha*/
/**//*
rSet, String ip){
     return false;

    }

    private void add(Set<Character> charSet, String ip){
      for (char c: ip.toCharArray())
          charSet.add(c);
    }

    private void remove(Set<Character> charSet, String ip){
      for (char c: ip.toCharArray())
          charSet.remove(c);
    }






    private int getMaxLen(List<String> arr, int idx,int currLen, int[] visited, Set<Character> charSet){

      if (idx==arr.length()) return currLen;
      int ans = 0;
      for (int i=idx;i<arr.length();i++){
        if(visited[i]!=1 && isUnique( charSet,  arr.get(i))){
            add(charSet,arr.get(i));
            visited[i]=1;
            ans=Math.max(ans, getMaxLen(arr,  i+1,currLen+arr.get(i).length(),  visited, charSet));
            remove(charSet, arr.get(i));
            visited[i]=0;

        }

      }
      return ans;

    }



    public int maxLength(List<String> arr) {
      if (arr.length==1)
          return arr.length();

      return getMaxLen(arr, 0,new int [arr.length()],new HashSet<Character>() );

    }
}
*/
