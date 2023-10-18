package org.dreambig.dsmuscles.leetcode.medium;

import java.util.List;
import java.util.ArrayList;
public class IP2CIDR {

  public List<String> ipToCIDR(String ip, int n) {
    long x=0; //base256 notation

    String []block=ip.split("\\.");

    for (int i=0;i<block.length;i++){
      x=x*256;
      x+=Integer.parseInt(block[i]);
    }

    List<String> result = new ArrayList<>();

    while(n>0){
      // finding last set bit
      long step = x&(-x);
      if(step==0) {
        step=1;
        while(step<n) step*=2;
      }
      while(step >n) step/=2;
      result.add(longToIp(x, (int) step));
      n-=step;
      x+=step;
    }

    return result;

  }
  String longToIp(long x, int step){
    int [] block= new int [4];
    block[0]=(int)(x&255);
    x=x>>8;
    block[1]=(int)(x&255);
    x=x>>8;
    block[2]=(int)(x&255);
    x=x>>8;
    block[3]=(int)x;
    int len =33;
    while(step>0){
      len--;
      step/=2;
    }
    return block[3]+"."+block[2]+"."+block[1]+"."+block[0]+"/"+len;
  }


}
