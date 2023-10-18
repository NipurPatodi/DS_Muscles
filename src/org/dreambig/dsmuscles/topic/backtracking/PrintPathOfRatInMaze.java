package org.dreambig.dsmuscles.topic.backtracking;

public class PrintPathOfRatInMaze {
    // Now since we need to print path of rat in maze
    // we need backtracking.
    // BT will always have safe function
    // and state to and fro


    private static  boolean isSafe(int [][] maze, int r, int c){
        return r < maze.length && c < maze[0].length && maze[r][c] == 1;
    }


    public static boolean doesPathExist (int [][] maze, int r, int c, int[][] sol){
        // base case if solution is reached
        if ( r== maze.length-1 && c== maze[0].length-1) {
            sol[r][c]=1;
            return true;
        }
        // check for is safe
        if(isSafe(maze,r,c)){
            sol[r][c]=1;
            if(doesPathExist(maze,r+1,c,sol)) return true;
            else if(doesPathExist(maze,r,c+1,sol)) return true;
            else{
                sol[r][c]=0;
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [][] maze={{1,0,0,0}, {1,1,0,1},{0,1,0,0},{1,1,1,1}};
        int [][] sol = new int [4][4];
        if(doesPathExist(maze,0,0,sol)){
            for (int[] ints : sol) {
                for (int j = 0; j < sol[0].length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println(" ");
            }
        }
    }
}
