package org.dreambig.dsmuscles.leetcode.medium;

/***
 * You are given an n x n 2D matrix representing an image, rotate the image by 90
 * degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * link => https://leetcode.com/problems/rotate-image/description/
 */
public class RotatingImage {

  private int [][] transpose(int [][]mtx){
    for (int i=0;i<mtx.length;i++){
      for (int j=i;j<mtx[i].length;j++){
        int tmp = mtx[i][j];
        mtx[i][j]=mtx[j][i];
        mtx[j][i]=tmp;
      }
    }

    return mtx;
  }

  private int [][] reverse(int [][] mtx){
    for (int i=0;i<mtx.length;i++){
    int s = 0, e=mtx[i].length-1;
    while(s<e){
      int tmp = mtx[i][s];
      mtx[i][s]=mtx[i][e];
      mtx[i][e]= tmp;
      s++; e--;
    }

    }

    return mtx;

  }


  // Solution is to transpose and reverse
  // matrix
  // or to choose math for this
  public void rotate(int[][] matrix) {
    reverse(transpose(matrix));
  }
}
