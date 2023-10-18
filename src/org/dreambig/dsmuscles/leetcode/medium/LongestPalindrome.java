package org.dreambig.dsmuscles.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

    public int longestPalindrome(String[] words) {
      // 1. Creating hashMap
      Map<String, Integer> wordCountMap= new HashMap<>();

      for (String word:words){
        wordCountMap.put(word,wordCountMap.getOrDefault(word,0)+1 );
      }

      //2. Iterating on all works to get Answer
      int result=0;
      boolean isCentre=false;

      for (String key: wordCountMap.keySet()){
        String word = key;
        int cnt = wordCountMap.get(key);
        // if word itself is palendrom
        if(word.charAt(0)==word.charAt(1)){
            if(cnt%2==0)
              result+=cnt;
            else{
              result+=cnt-1;
              isCentre=true;
            }
        }else if(word.charAt(0)>word.charAt(1)){
          String reverse=""+word.charAt(1)+word.charAt(0);
          result+= 2*Math.min(cnt,wordCountMap.getOrDefault(reverse,0));

        }

      }
      if(isCentre)
      result++;

      return 2*result;

    }

}
