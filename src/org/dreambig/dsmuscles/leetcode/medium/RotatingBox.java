package org.dreambig.dsmuscles.leetcode.medium;

/***
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 * Example 2:
 *
 *
 *
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 * Example 3:
 *
 *
 *
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 * Link => https://leetcode.com/problems/rotating-the-box/description/
 */
public class RotatingBox {


    private char[][] align(char[][] box){
      int r= box.length, c= box[0].length;

      for (int rowIdx=0;rowIdx<r; rowIdx++){
        int empty= c-1;
        for (int colIdx=c-1; colIdx>=0;colIdx--){
          // Please note that we just dont need to iterate all, every time
          // 1. If Obstracle found
          if (box[rowIdx][colIdx]=='*'){
            empty=colIdx-1; // jump to previous
          }
          else if(box[rowIdx][colIdx]=='#'){
            if(empty>colIdx ){
              box[rowIdx][colIdx]='.';
              box[rowIdx][empty]='#';
            }
            empty--;
          }
        }
      }
      return box;
    }

    private char[][] transpose(char[][]box){
      char[][] res = new char[box[0].length][box.length];
       for (int rowIdx=0;rowIdx<box.length;rowIdx++){
         for (int colIdx=0;colIdx<box[0].length;colIdx++){
           res[colIdx][res[0].length-1-rowIdx]=box[rowIdx][colIdx];

         }

       }
      return res;

    }



    public char[][] rotateTheBox(char[][] box) {
        return transpose(align(box));
    }
}
