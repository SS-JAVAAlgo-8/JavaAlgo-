package BOJ;

import java.io.*;
import java.util.*;
public class BOJ_1238_파티 {
    static int N,M,X;
    static int result = Integer.MIN_VALUE;
    static ArrayList<ArrayList<info>> graph = new ArrayList<ArrayList<info>>();
    
    
    static class info implements Comparable<info>{
        int now;
        int dist;
        
        public info(int now , int dist) {
            this.now = now;
            this.dist = dist;
        }
        @Override
        public int compareTo(info o) {
            
            return this.dist - o.dist;
        }
        
        
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        
        for (int i = 0; i < M; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            int cost = sc.nextInt();
            
            graph.get(left).add(new  info(right, cost));
        }
        
        for (int i = 1; i < N+1; i++) {
            result = Math.max(result, dsk(i,X) + dsk(X,i));
        }
        System.out.println(result);
    }

   
    private static int dsk(int start, int end) {
        int[] visited = new int[N+1];
        Arrays.fill(visited,Integer.MAX_VALUE);
        PriorityQueue<info> dq = new PriorityQueue<>(); 
        
        visited[start]  = 0;
        dq.add(new info(start, 0));
        
        while(dq.size() > 0) {
            info now = dq.poll();
            int s = now.now;
            int d = now.dist;
            
            if(visited[s] < d) continue;
            
            
            for (int i = 0; i < graph.get(s).size(); i++) {
                info next = graph.get(s).get(i);
                int cost = d + next.dist;
                
                if(visited[next.now] > cost) {
                    visited[next.now] = cost;
                    dq.add(new info(next.now , cost));
                }
            }
        }
        return visited[end];
    }
}
