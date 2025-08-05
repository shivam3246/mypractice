//https://leetcode.com/problems/rotting-oranges/
import java.util.*;
public class RottingOranges {
    public static int orangesRotting(int[][] grid) {
        if(grid==null||grid.length==1){
            return -1;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int []> q = new LinkedList<>();
        int freshOranges = 0;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++){
                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                }
                else if(grid[i][j]==1){
                    freshOranges++;
                }
            }
        }
        int minutes = 0;
        int [][]directions = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!q.isEmpty()){
            int size = q.size();
            boolean rotted = false;
            for(int i  =0 ;i<size;i++){
                int []current = q.poll();
                int row = current[0];
                int col = current[1];
                for(int []dir:directions){
                    int newRow = row+dir[0];
                    int newCol = col + dir[1];
                    if(newRow>=0&&newRow<rows&&newCol>=0&&newCol<cols&& grid[newRow][newCol]==1){
                        grid[newRow][newCol]=2;
                    
                    q.offer(new int[]{newRow,newCol});
                    freshOranges--;
                    rotted=true;
                }
            }

            }
            if(rotted){
                minutes++;
            }

        }
        return freshOranges==0?minutes:-1;
    }
    public static void main(String[] args) {
        int[][] grid = {
    {2, 1, 1},
    {0, 1, 1},
    {1, 0, 1}
};
System.out.println(orangesRotting(grid));
    }
}
