package BOJ;


import java.io.*;
import java.util.*;


public class BOJ_4485_녹옷입애젤 {
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1}; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = 0;
        while(true) {
            testcase +=1;
            int N = sc.nextInt();
            if(N == 0) break;
            int[][] board = new int[N][N];
            int[][] visited = new int[N][N];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            
            for (int i = 0; i < visited.length; i++) {
                Arrays.fill(visited[i], 10000000);
            }
            visited[0][0] = board[0][0];
            Deque<int[]> dq = new ArrayDeque<>();
            
            
            dq.add(new int[] {0,0});
            
            while(dq.size()>0) {
                int[] now = dq.removeFirst();
                int x = now[0];
                int y = now[1];
                
                if(x == N-1 && y == N-1) {
                    continue;
                }
                
                for (int d = 0; d < 4 ; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    
                    if(nx>=0 && nx< N && ny>=0 && ny < N) {
                        if(visited[nx][ny] > board[nx][ny] + visited[x][y]) {
                            visited[nx][ny] = board[nx][ny] + visited[x][y];
                            dq.add(new int[] {nx,ny});
                        }
                    }
                    
                        
                }
                
            }
            
            System.out.println("Problem" + testcase + ": " + visited[N-1][N-1]);
            
            
            
        }
    }
}
