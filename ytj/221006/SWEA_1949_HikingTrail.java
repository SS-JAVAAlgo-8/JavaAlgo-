package study.day221006.problem;

import java.util.*;
import java.io.*;

public class SWEA_1949_HikingTrail { // 5:14 ~ 6:36  1h 22m | 93,092 kb | 242 ms

	static int N, K, ans;	// 지형 크기, 최대 공사 가능 깊이
	static int [][]	map;
	static int [][]	copy;
	static Queue<Point> qu;
	static List<Point> route;
	
	static int [] dr = {-1, 1, 0, 0}; // 상, 하 , 좌 , 우
	static int [] dc = {0, 0, -1, 1};	
	
	static class Point {
		int r,c;
		int height;
		int cnt;

		public Point(int r, int c, int height, int cnt) {
			this.r = r;
			this.c = c;
			this.height = height;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			
			map = new int[N][N];
			copy = new int[N][N];
			
			route = new ArrayList<>();
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());				
				for (int j = 0; j < N; j++) {
					copy[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
					if(max < map[i][j]) max = map[i][j];
				}
			}
			
			for (int i = 0; i < N; i++) { // 가장 높은 봉우리 위치 저장
				for (int j = 0; j < N; j++) {
					if(max == map[i][j]) route.add(new Point(i, j, max, 1));
				}
			}
			
			boolean one = false;
			// 한곳이 깎인 맵 만들고 -> 길찾기 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					int k;
					for (k = 0; k <= K; k++) { //(one == false ? 0 : 1)
						copy[i][j] -= k;
						BFS();
						copy[i][j] = map[i][j];
					}
					one = true;
				}
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void BFS() {
		
		qu = new ArrayDeque<>();
		// 돌아갈 일이 없으므로 visited 불필요
		
		for (int i = 0; i < route.size(); i++) {
			qu.add(route.get(i)); // 최정상 봉우리 정보 저장
		}
		
		while(!qu.isEmpty()) {
			
			Point p = qu.poll();
			
			int r = p.r;
			int c = p.c;
			int cnt = p.cnt;
			
			ans = Math.max(ans, cnt);
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(isIn(nr, nc) && copy[nr][nc] < copy[r][c]) { // 범위 내 이고, 현재 위치보다 작은 높이일 때 진행
					qu.add(new Point(nr, nc, copy[nr][nc], cnt + 1));
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr < N && nc < N && nr >= 0 && nc >= 0;
	}

}

/*

1
5 1
9 3 2 3 2 
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8

*/