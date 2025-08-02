//https://leetcode.com/problems/critical-connections-in-a-network/description/
import java.util.*;
public class criticalConnections{
    private static int timer = 1;
    public static void dfs(int node,int parent,int []vis,List<List<Integer>> graph,int []timeInsertion,int []low,List<List<Integer>> result){
        vis[node] = 1;
        timeInsertion[node]=low[node]=timer;
        timer++;
        for(Integer it:graph.get(node)){
             if(it == parent){
                continue;
             }
             if(vis[it]==0){
                dfs(it,node,vis,graph,timeInsertion,low,result);
                low[node] = Math.min(low[node],low[it]);
                if(low[it]>timeInsertion[node]){
                    result.add(Arrays.asList(it,node));
                }
             }
             else{
                low[node] = Math.min(low[node],low[it]);
             }
        }

    }
     public static List<List<Integer>> criticalConections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i =0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(List<Integer> connection : connections){
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
        int[] timeInsertion = new int[n];
        int[] low = new int[n];
        int [] visited = new int[n];
        dfs(0,-1,visited,graph,timeInsertion,low,result);
        return result;
        
    }
    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));
        System.out.println(criticalConections(n, connections));
        
             
    }
}