package org.dreambig.dsmuscles.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseMatrixMultiplication {
    private List<Map<Integer,Integer>> compress(int [][] mat1){
        List<Map<Integer,Integer>> res= new ArrayList<>(mat1.length);
        for (int rowIdx=0; rowIdx<mat1.length;rowIdx++){
            Map<Integer,Integer> currRow=null;
            for(int colIdx=0;colIdx<mat1[rowIdx].length;colIdx++){
                if(mat1[rowIdx][colIdx]!=0){
                    if(currRow==null){
                        currRow= new HashMap<>();
                    }
                    currRow.put(colIdx,mat1[rowIdx][colIdx]);
                }
            }

            res.add(currRow);
        }
        return  res;
    }

    public int[][] multiply(int[][] mat1, int[][] mat2) {
        List<Map<Integer,Integer>> cMat1= compress(mat1);
        List<Map<Integer,Integer>> cMat2= compress(mat2);
        int n= mat1.length;

        int m = mat2[0].length;
        int[][] res= new int [n][m];

        for (int rowIdx=0;rowIdx<n;rowIdx++){

            if(cMat1.get(rowIdx)!= null){

                for (int elementIdx: cMat1.get(rowIdx).keySet())
                {
                    if(cMat2.get(elementIdx)!=null)
                        for(int colIdx =0;colIdx<m;colIdx++){
                            res[rowIdx][colIdx]+= cMat1.get(rowIdx).get(elementIdx)*cMat2.get(elementIdx).getOrDefault(colIdx,0);
                        }
                }
            }
        }
        return res;
    }
}
