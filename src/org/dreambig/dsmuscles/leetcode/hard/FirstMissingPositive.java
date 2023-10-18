package org.dreambig.dsmuscles.leetcode.hard;
/** Tricky ***/



public class FirstMissingPositive {

  public int firstMissingPositive(int[] nums) {
    boolean onePresent = false;
    for (int i: nums){
        if( i==1){
          onePresent=true;
          break;
        }
    }
    if(!onePresent) return 1;

    // Now cleaning 0, -ve and >n numbers
    int n = nums.length;

    for( int i=0;i<n; i++){
      if(nums[i]<=0 || nums[i]>n){
          nums[i]=1;
      }
    }

    // Now Hashing
    // Please note hash is i-1
    for( int i=0;i<n; i++){
      int curr = Math.abs(nums[i]);
      nums[curr-1]= - Math.abs(nums[curr-1]);
    }

    for ( int i=0;i<n;i++){
      if(nums[i]>0){
        // since hash was i-1 , result is i+1
          return i+1;
        }
    }
    return n+1;

    }
}
