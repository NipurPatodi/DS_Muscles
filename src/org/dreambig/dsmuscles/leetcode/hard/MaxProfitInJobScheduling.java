package org.dreambig.dsmuscles.leetcode.hard;

import java.util.*;

public class MaxProfitInJobScheduling {
    static class Job{
        public final int startTime;
        public final int endTime;
        public final int profit;

        public Job (int startTime, int endTime, int profit){
            this.startTime =startTime;
            this.endTime = endTime;
            this.profit= profit;
        }
    }

    // total number of jobs can be 5e^4
    private int [] cache= new int [50001];

    private boolean canAdd(Job job, int endTime){
        return endTime<=job.startTime;
    }

    private int binarySearch(int [] startTime, int endTime){
        int start=0; int end= startTime.length-1; int nextIdx=startTime.length;
        while(start<=end){
            int mid= (start+end)/2;

            if(startTime[mid]>=endTime){
                nextIdx=mid;
                end=mid-1;
            }else{
                start= mid+1;
            }

        }
        return nextIdx;

    }

    private int maxProfit(List<Job>jobs, int idx, int [] startTime){
        // if idx=n we have reached end
        if(idx == jobs.size()) return  0;
        // using cache if already exist
        if(cache[idx]!=-1)
            return cache[idx];
         int nextIdx = binarySearch ( startTime,  jobs.get(idx).endTime);

         int maxProfit= Math.max( jobs.get(idx).profit+ maxProfit(jobs, nextIdx,startTime),
                 maxProfit(jobs, idx+1,startTime)
                 );
         cache[idx]=maxProfit;
         return cache[idx];
    }



    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs= new ArrayList<>(startTime.length);
        for (int i=0;i<startTime.length;i++){
            Job job = new Job(startTime[i], endTime[i], profit[i]);
            jobs.add(job);
        }
        jobs.sort(Comparator.comparingInt(a -> a.startTime));
        // or I could have sorted
        for ( int  i=0;i<startTime.length;i++){
            startTime[i]= jobs.get(i).startTime;

        }
        Arrays.fill(cache, -1);

        return maxProfit(jobs, 0, startTime);
    }

}
