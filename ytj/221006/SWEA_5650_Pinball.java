package study.day221006.problem;

import java.io.*;
import java.util.*;

public class SWEA_5650_Pinball { // 4 : 15 ~ 7 : 32 Debug 1H 30m
	
	static int N, ans;
	static int [][] map;
	
	static int [] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int [] dc = {0, 1, 0, -1};
	
	static Queue<Point> qu;
	
	static class Point {
		int r,c;
		int d;
		int cnt;

		public Point(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
						
			N = Integer.parseInt(br.readLine());
			ans = 0;
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if(map[i][j] == 0) {						
						for (int d = 0; d < 4; d++) {
//							System.out.printf("%d %d %d %d\n",i,j,d,ans);
							Simulate(new Point(i,j,d,0));
						}
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void Simulate(Point point) {
		
		qu = new ArrayDeque<>();
		int start_r = point.r;
		int start_c = point.c;
		// 처음 이동 후 qu 에 넣기
		point.r += dr[point.d];
		point.c += dc[point.d];
		
		qu.offer(point);

		while(!qu.isEmpty()) {
			
			Point p = qu.poll();
			
			int r = p.r;
			int c = p.c;
			int d = p.d;
			int cnt = p.cnt;
			
			if(isIn(r, c)) {
				
				if(map[r][c] == -1 || Start(r, c, start_r, start_c)) { // 블랙홀 이거나 시작 위치일 때
					ans = Math.max(ans, cnt);
					return;
				}
				
				if(map[r][c] == 0) { // 빈공간 그대로 진행
					
					qu.offer(new Point(r + dr[d], c + dc[d], d, cnt));
				}
				
				else if(map[r][c] > 0 && map[r][c] <= 5) { // 1~5 번 블럭을 만났을때
					hitToblock(r, c, d, cnt);
				}
				
				else if(map[r][c] > 5) { // 웜홀일 때
					// 다름 웜홀 위치에서 시작 , 방향 그대로
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							if(map[i][j] == map[r][c] && (i != r || j != c)) {
								
								qu.offer(new Point(i + dr[d], j + dc[d], d, cnt));
								break;
							}
						}
					}
				}
				
			}
			
			else { // 범위 밖으로 나가려고 할때 = 벽에 부딪힐떄 
				cnt += 1;
				if(d < 2) d += 2;
				else d -= 2;
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				qu.offer(new Point(nr, nc, d, cnt)); // 방향과 부딪힌 횟수만 갱신
				
			}
			
		}
		
	}

	private static boolean Start(int nr, int nc, int r, int c) {
		return nr == r && nc == c;
	}

	private static void hitToblock(int r, int c, int d, int n) {
		int cnt = n + 1;
		int nr = r;
		int nc = c;
		int dir = 0;
		
		switch (map[r][c]) {
		case 1:
			if(d == 0) {	 // 위
				nr = r + dr[2]; // 아래로
				nc = c + dc[2];
				dir = 2;
			}
			else if(d == 1) {  // 우
				nr = r + dr[3]; // 왼쪽으로
				nc = c + dc[3];
				dir = 3;
			}
			else if(d == 2) {  // 하
				nr = r + dr[1]; // 오른쪽으로
				nc = c + dc[1];
				dir = 1;
			}
			else if(d == 3) {  // 좌
				nr = r + dr[0]; // 위로
				nc = c + dc[0];
				dir = 0;				
			}
			break;
			
		case 2:
			if(d == 0) {	 // 위
				nr = r + dr[1]; // 오른쪽으로
				nc = c + dc[1];
				dir = 1;
			}
			else if(d == 1) {  // 우
				nr = r + dr[3]; // 왼쪽으로
				nc = c + dc[3];
				dir = 3;
			}
			else if(d == 2) {  // 하
				nr = r + dr[0]; // 위으로
				nc = c + dc[0];
				dir = 0;
			}
			else if(d == 3) {  // 좌
				nr = r + dr[2]; // 아래로
				nc = c + dc[2];
				dir = 2;				
			}
			break;
			
		case 3:
			if(d == 0) {	 // 위
				nr = r + dr[3]; // 왼쪽으로
				nc = c + dc[3];
				dir = 3;
			}
			else if(d == 1) {  // 우
				nr = r + dr[2]; // 아래로
				nc = c + dc[2];
				dir = 2;
			}
			else if(d == 2) {  // 하
				nr = r + dr[0]; // 위쪽으로
				nc = c + dc[0];
				dir = 0;
			}
			else if(d == 3) {  // 좌
				nr = r + dr[1]; // 오른쪽으로
				nc = c + dc[1];
				dir = 1;				
			}
			break;
			
		case 4:
			if(d == 0) {	 // 위
				nr = r + dr[2]; // 아래로
				nc = c + dc[2];
				dir = 2;
			}
			else if(d == 1) {  // 우
				nr = r + dr[0]; // 위쪽으로
				nc = c + dc[0];
				dir = 0;
			}
			else if(d == 2) {  // 하
				nr = r + dr[3]; // 왼쪽으로
				nc = c + dc[3];
				dir = 3;
			}
			else if(d == 3) {  // 좌
				nr = r + dr[1]; // 오른쪽으로
				nc = c + dc[1];
				dir = 1;				
			}
			break;
			
		case 5:
			if(d == 0) {	 // 위
				nr = r + dr[2]; // 아래로
				nc = c + dc[2];
				dir = 2;
			}
			else if(d == 1) {  // 우
				nr = r + dr[3]; // 왼쪽으로
				nc = c + dc[3];
				dir = 3;
			}
			else if(d == 2) {  // 하
				nr = r + dr[0]; // 위쪽으로
				nc = c + dc[0];
				dir = 0;
			}
			else if(d == 3) {  // 좌
				nr = r + dr[1]; // 오른쪽으로
				nc = c + dc[1];
				dir = 1;
			}
			break;

		default:
			break;
		}
		
		qu.offer(new Point(nr, nc, dir, cnt));
	}

	private static boolean isIn(int nr, int nc) {
		return nr < N && nc < N && nr >= 0 && nc >= 0;
	}

}
/*

1
10
0 1 0 3 0 0 0 0 7 0
0 0 0 0 -1 0 5 0 0 0
0 4 0 0 0 3 0 0 2 2
1 0 0 0 1 0 0 3 0 0
0 0 3 0 0 0 0 0 6 0
3 0 0 0 2 0 0 1 0 0
0 0 0 0 0 1 0 0 4 0
0 5 0 4 1 0 7 0 0 5
0 0 0 0 0 1 0 0 0 0
2 0 6 0 0 4 0 0 0 4

1
6
1 1 1 1 1 1
1 1 -1 1 1 1
1 -1 0 -1 1 1
1 1 -1 1 1 1
1 1 1 1 1 1
1 1 1 1 1 1

1
8
0 0 0 3 0 0 0 0
0 0 2 0 0 5 0 0
0 0 5 0 3 0 0 0
0 0 1 1 0 0 0 4
0 0 0 0 0 0 0 0
0 0 0 0 0 0 5 0
0 0 4 0 0 3 1 0
2 0 0 4 3 4 0 0

*/
