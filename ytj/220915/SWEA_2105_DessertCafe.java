package study.day220915.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2105_DessertCafe {
	
	static int N, cnt, ans;
	static int[][] map;
	static List<Integer> list;
	static boolean[][] visited;
	
	static int[] dr = {1, -1, -1, 1}; 
	static int[] dc = {1, 1, -1, -1};
	
	static int init_r, init_c;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cnt = 0;
			ans = -1;
			
			map = new int[N][N];
			list = new ArrayList<>();
			visited = new boolean[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}				
			}
			// 입력
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					visited[r][c] = true;
					list.add(map[r][c]);  // 방문한 디저트 저장 
					
					init_r = r;			  // 돌아올 위치 저장
					init_c = c;
					DFS(r, c, 0);
					
					list.clear();		 // 다음 투어 경로를 위해 초기화
					visited = new boolean[N][N];
				}
			}
			if(ans < 4) ans = -1;
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void DFS(int r, int c, int dt) {
		for(int d = dt ; d < 4 ; ++d) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if( nr == init_r && nc == init_c) { // 출발 위치로 돌아 왔을때,
//				for(int val : list) {
//					System.out.print(val + " ");
//				}
//				System.out.println();
				cnt = list.size();
				ans = Math.max(cnt, ans);				
				return;
			}
			
			if( isIn(nr, nc) && !visited[nr][nc] && !list.contains(map[nr][nc]) ) {
				
				visited[nr][nc] = true;
				list.add(map[nr][nc]);
				
				DFS(nr, nc, d);
				
				list.remove(list.size() - 1);  // 다른 경로 경우의 수를 위해 반환
				visited[nr][nc] = false;
			}
			
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr < N && nc < N && nr >= 0 && nc >= 0;
	}

}
/*

1               
4                
9 8 9 8
4 6 9 4
8 7 7 8
4 5 3 5

1
5                
8 2 9 6 6
1 9 3 3 4
8 2 3 3 6
4 3 4 4 9
7 4 6 3 5

*/