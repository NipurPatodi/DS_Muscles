package org.dreambig.dsmuscles.leetcode.easy;

/***
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 *
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
 *
 * Given an integer array nums, return true if the given array is monotonic, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3]
 * Output: true
 * Example 2:
 *
 * Input: nums = [6,5,4,4]
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,3,2]
 * Output: false
 * Link => https://leetcode.com/problems/monotonic-array/description/
 */
public class MonotonicArray {

// Please note that altough problem looks simple but it has very intresting corner cased. Please do take care of this.


    private boolean isMono(int a, int b, boolean increasing) {
        if (increasing)
            return a <= b;

        else
            return b <= a;

    }


    public boolean isMonotonic(int[] nums) {
        if (nums.length < 2)
            return true;

        Boolean increasing = null;


        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1] && increasing == null) {
                continue;
            } else if (increasing == null)
                increasing = nums[i] < nums[i + 1];


            if (!isMono(nums[i], nums[i + 1], increasing))
                return false;
        }
        return true;

    }
}
