package org.dreambig.dsmuscles.leetcode.medium;
/** Tricky **/
/***
iven an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.



Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3


Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.
link=>https://leetcode.com/problems/find-the-duplicate-number/description/
**/
class DuplicateNumber {

  // Solving using binary search
    public int findDuplicate(int[] nums) {
      int low= 1;
      int  high = nums.length-1;
      int duplicates=-1;

      while(low<=high){
        int curr = (low+high)/2;

        int count =0;
        for (int num:nums){
          if(num<=curr){
            count++;
          }
        }
        if(count>curr){
          duplicates=curr;
          high=curr-1;
        }else{
          low=curr+1;
        }

      }
    return duplicates;
}


}
