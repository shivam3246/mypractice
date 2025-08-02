//https://leetcode.com/problems/number-of-islands/description/
import java.util.*;
public class numberOfIslands {
     public static int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    count++;
                    iterativeDfs(grid, i, j);
                }
            }
        }
        return count;
        
    }
    public static void dfs(char [][]grid,int i,int j){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]=='0'){
            return;
        }
        grid[i][j]='0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
    public static void iterativeDfs(char [][]grid,int i,int j){
          int [][]directions = {{1,0},{-1,0},{0,1},{0,-1}};
          Stack<int []> stack = new Stack<>();
          stack.push(new int[]{i,j});
          while(!stack.isEmpty()){
            int []cell = stack.pop();
            int a = cell[0];
            int b = cell[1];
            if(a<0||a>=grid.length||b<0||b>=grid[0].length||grid[a][b]=='0'){
                continue;
            }
            grid[a][b]='0';
            

            for(int []dir:directions){
                int ni = a+dir[0];
                int nj = b+dir[1];
                stack.push(new int[]{ni,nj});
            }

          }
    }
    public static void main(String[] args) {
       char[][] grid = {
    {'1', '1', '1', '1', '0'},
    {'1', '1', '0', '1', '0'},
    {'1', '1', '0', '0', '0'},
    {'0', '0', '0', '0', '0'}
};
System.out.println(numIslands(grid));

    }
}
