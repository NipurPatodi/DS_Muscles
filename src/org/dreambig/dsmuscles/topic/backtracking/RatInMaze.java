package org.dreambig.dsmuscles.topic.backtracking;

public class RatInMaze {

    static boolean pathExist(int [][]maze, int r, int c){
        if (r==maze.length-1 && c== maze[0].length-1)
            return true;
        if ( r>maze.length-1)
            return false;
        if ( c>maze[0].length-1)
            return false;

        if (maze[r][c]==1){ // possible
            return pathExist(maze, r+1,c)||pathExist(maze, r,c+1);
        }else
            return false;
    }

    public static void main(String[] args) {
        int [][] maze={{1,0,0,0}, {1,1,0,1},{0,1,0,0},{1,1,1,1}};
        System.out.println(pathExist(maze,0,0));

        int [][] maze2={{1,0,0}, {0,1,0},{1,1,1,}};
        System.out.println(pathExist(maze2,0,0));
    }
}
