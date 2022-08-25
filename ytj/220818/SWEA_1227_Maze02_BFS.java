package study.day220818.problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1227_Maze02_BFS {

	static int [] dy = {-1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int [] dx = {0, 1, 0, -1};
	static int [][] maze;
	
	static int r, c, isPossible;
	static boolean isVisited[][];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("data/maze02.txt"));
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			
			int TC = Integer.parseInt(br.readLine());
			
			maze = new int[100][100];
			isVisited = new boolean[100][100];
			isPossible = 0;
			
			for (int i = 0; i < maze.length; i++) {
				char [] arr = br.readLine().toCharArray();
				for (int j = 0; j < maze.length; j++) {
					maze[i][j] = arr[j] - '0';
					if(maze[i][j] == 2) {
						r = i;
						c = j;
					}
				}
			}
			
			//findRoute(r, c); // DFS
			//bfs();
			bfs2();
			System.out.println("#" + TC + " " + isPossible);
		}
		
	}
	
    static void bfs() {
        // 도구 준비
        Queue<Node> q = new LinkedList<>();
        boolean [][] visited = new boolean[100][100];
        // 초기 설정
        q.offer(new Node(r, c));
        
        while(!q.isEmpty()) {
            // 대장 데려오기.
            Node head = q.poll();
            // 사용하기
            // bfs의 특성상 담을 때는 아직 미방문이었지만. 다른 경로에서 탐색 과정에서 방문 처리된 녀석들이 있을수 있다.
            if(visited[head.r][head.c]) {
                continue;
            }
            visited[head.r][head.c] = true;
            
            // 자식노드 탐색
            for(int d=0; d < 4; d++) {
                int nr = head.r + dx[d];
                int nc = head.c + dy[d];
                
                if(maze[nr][nc] == 0 && !visited[nr][nc]) {
                    q.offer(new Node(nr, nc));
                }else if(maze[nr][nc] == 3) {
                    isPossible = 1;
                    return;
                }
            }
        }
    }
    
    static void bfs2() {
        // 도구 준비
        Queue<Node> q = new LinkedList<>();
        boolean [][] visited = new boolean[100][100];
        // 초기 설정
        q.offer(new Node(r, c));
        visited[r][c]=true;
        int turn = 0;
        
        while(!q.isEmpty()) { //queue가비어있지 않을 때까지 계속 동작!!!
            int size = q.size();
            while(size-- > 0) {
                // 대장 데려오기.
                Node head = q.poll();
                // 사용하기
                
                visited[head.r][head.c] = true;
                
                // 자식노드 탐색
                for(int d=0; d < 4; d++) {
                    int nr = head.r + dx[d];
                    int nc = head.c + dy[d];
                    
                    if(maze[nr][nc] == 0 && !visited[nr][nc]) {
                        q.offer(new Node(nr, nc));
                        visited[nr][nc] = true;
                    }else if(maze[nr][nc] == 3) {
                        System.out.println(turn + " 번 만에 목적지에 도착!!");
                        isPossible = 1;
                        return;
                    }
                }
            }// 다음 turn으로!!
            
            turn++;
        }
    }
    
    static class Node{
        int r, c;

        public Node(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "[" + r + "," + c + "]";
        }
        
    }
	
	private static void findRoute(int x, int y) {
		
		if(maze[x][y] == 3) {
			isPossible = 1;
			return;
		}
		
		isVisited[x][y] = true;  // 지나온 좌표 체크
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
//			if( nx >= 0 && ny >= 0 && nx < maze.length && ny < maze.length ) {
//				if( !isVisited[nx][ny] && maze[nx][ny] != 1) {
//					
//					findRoute(nx, ny);
//				}
//			}
			
			if(nx < 0 || nx >= maze.length || ny < 0 || ny >= maze.length) continue; // 좌표 범위 체크

			if(isVisited[nx][ny] || maze[nx][ny] == 1) continue; // 미로거나, 이미 지나온곳 진행 X
			findRoute(nx, ny);								
			
		}
		
	}

}
