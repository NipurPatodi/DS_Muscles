// ************ Tricky ********** //
package org.dreambig.dsmuscles.leetcode.ms.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import java.util.Map;
import java.util.HashMap;

/***
 *  Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 */

public class CanPartitionKSubset {

    private static boolean canPartitionKSubsetRec(int[] nums, final int targetedSum, int currSum, int idx, int count, int k, char[] visited, Map<String, Boolean> cache) {
        if (count == k - 1 && currSum == targetedSum) return true;

        if (currSum == targetedSum) {
            return canPartitionKSubsetRec(nums, targetedSum, 0, 0, count + 1, k, visited, cache);
        }

        if (currSum > targetedSum) return false;

        String tknStr = String.valueOf(visited);
        if(cache.containsKey(tknStr)){
            return cache.get(tknStr);
        }

        for (int i = idx; i < nums.length; i++) {
            if (visited[i] != '1') {
                visited[i] = '1';
                if (canPartitionKSubsetRec(nums, targetedSum, currSum + nums[i], idx + 1, count, k, visited, cache)) {
                    cache.put(String.valueOf(visited), true);
                    return true;
                }
                visited[i] = '0';
            }
        }
        cache.put(String.valueOf(visited),false);
        return false;
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;

        // # 1 is divisible by k ?
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        int[] finalNums = nums;
        nums = IntStream.rangeClosed(1, nums.length).map(i -> finalNums[finalNums.length - i]).toArray();
        Map<String, Boolean> cache= new HashMap<>();

        // Now base cases
        return canPartitionKSubsetRec(nums, sum / k, 0, 0, 0, k, new char[nums.length], cache);


    }

    public static void main(String[] args) {
        int [] nums= {1,1,2,2,2};
        System.out.println(canPartitionKSubsets(nums,4));
    }

}
