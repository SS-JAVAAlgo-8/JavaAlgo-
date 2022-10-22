package study.day221013.problem;

import java.io.*;
import java.util.*;

public class BOJ_4485_GreenisZELDA {

	static int N, tc;
	static int ans;
	static int [][] map;
	static int [][] mz;
	
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		tc = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) {
				System.out.print(sb);
				return;
			}
			
			ans = Integer.MAX_VALUE;
			map = new int[N][N];
			mz = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					mz[i][j] = Integer.MAX_VALUE;
				}
			}
			
			BFS();
			
			sb.append("Problem " + ++tc + ": " + ans + "\n");
		}
	}

	private static void BFS() {
		Queue<Point> qu = new ArrayDeque<>();
		mz[0][0] = map[0][0];
		
		qu.offer(new Point(0, 0));
		
		while(!qu.isEmpty()) {
			
			Point p = qu.poll();
			
			int r = p.x;
			int c = p.y;
			
			if(r == N-1 && c == N-1) ans = Math.min(ans, mz[N-1][N-1]);
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(isIn(nr, nc) && mz[nr][nc] > mz[r][c] + map[nr][nc]) {
					mz[nr][nc] = mz[r][c] + map[nr][nc];
					qu.add(new Point(nr, nc));
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr < N & nc < N & nr >= 0 & nc >= 0;
	}
}
