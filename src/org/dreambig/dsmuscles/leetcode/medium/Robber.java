package org.dreambig.dsmuscles.leetcode.medium;

/***
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * link => https://leetcode.com/problems/house-robber/description/
 */
public class Robber {
    public int rob(int[] nums) {
        if (nums.length==0)
           return 0;
        if (nums.length==1)
            return nums[1];
        int[] cache= new int [nums.length+1];

        return Math.max(rob(0, nums, cache), rob(1, nums,cache));
    }

    public int rob(int idx, int [] nums, int []cache){
        if (idx>=nums.length)
            return 0;
        if(cache[idx]!=-1){
            return cache[idx];
        }

        int res = Math.max(rob(idx+2, nums, cache)+nums[idx], rob(idx+1,nums,cache));
        cache[idx]=res;
        return res;
    }

}
