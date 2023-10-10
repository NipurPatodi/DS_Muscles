package org.dreambig.dsmuscles.leetcode.medium;

/***
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 */
public class MatrixMultiplication {
  public int[][] multiply(int[][] mat1, int[][] mat2) {
    int n= mat1.length;
    int k = mat1[0].length;
    int m = mat2[0].length;
    int [][] res= new int [mat1.length][mat2[0].length];
    for (int rowIdx=0;rowIdx<n;rowIdx++){
      for (int elementIdx=0;elementIdx<k;elementIdx++){

      if (mat1[rowIdx][elementIdx] !=0)
        for (int colIdx =0;colIdx<m;colIdx++ ){
          res[rowIdx][colIdx]+= mat1[rowIdx][elementIdx]*mat2[elementIdx][colIdx];
        }


      }
    }
    
    return res;
  }
}
