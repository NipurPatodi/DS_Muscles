//**** Tricky --- learned ****
package org.dreambig.dsmuscles.leetcode.ms.medium;

import java.util.HashMap;
import java.util.Map;

/***
 * Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 *
 * Note that:
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,9,12]
 * Output: 4
 * Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
 * Example 2:
 *
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation:  The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 *
 * Input: nums = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:  The longest arithmetic subsequence is [20,15,10,5].
 */
public class LongestArthSubSequence {

    public int longestArithSeqLength(int[] nums) {
        // this is tricky question where we need DP
        //  we need to find arth sum
        // But please note ... things are tough till you let them tobe
        // lets burn this problem ...
        // ahhoo ahhoo ahhoo
        // logic is
        //dp[right][diff]= dp[left][diff]+1;

        // So we need DP
        int maxLen= Integer.MIN_VALUE;
        Map<Integer,Integer>[] cache = new Map[nums.length+1];
        for (int r=0;r<nums.length;r++){
            HashMap<Integer,Integer> right = new HashMap<>();
            cache[r]=right;
            for (int l=0;l< nums.length;l++){
                int diff = nums[r]-nums[l];
                cache[r].put(diff, cache[l].getOrDefault(diff,1)+1);
                maxLen= Math.max(maxLen, cache[r].get(diff));
            }
        }
        return maxLen;

    }
}
