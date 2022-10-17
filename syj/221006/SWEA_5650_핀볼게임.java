package study.day1006;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5650_핀볼게임 {	// 메모리 : 49,824 kb		실행시간 : 200 ms
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static int[][] hole;
	static int N, ans, startX, startY;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/5650.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N + 2][N + 2];
			hole = new int[5][4];
			// 맵 가장자리 5번 블록으로 채워놓기
			for (int i = 0; i <= N + 1; i++) {
				map[0][i] = 5;
				map[N + 1][i] = 5;
				map[i][0] = 5;
				map[i][N + 1] = 5;
			}
			// 맵 입력 받으면서 웜홀 위치 저장
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6) {
						if(hole[map[i][j] - 6][0] == 0) {
							hole[map[i][j] - 6][0] = i;
							hole[map[i][j] - 6][1] = j;
						}else {
							hole[map[i][j] - 6][2] = i;
							hole[map[i][j] - 6][3] = j;
						}
					}
				}
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][j] == 0) {
//						startX = i; startY = j;
						for (int d = 0; d < 4; d++) {							
							pinball(i, j, d);	// 4방향으로 돌리기
						}
					}
				}
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	private static void pinball(int r, int c, int d) {
		boolean flag = false;
		int score = 0;
		int nr = r + dx[d];
		int nc = c + dy[d];
		while(!flag) {
//			if(nr == startX && nc == startY) {
//				System.out.println(nr + " " + startX + " " + nc + " " + startY);
//				break;
//			}
			// 출발지점 or 블랙홀을 만난 경우 break
			if((nr == r && nc == c) || map[nr][nc] == -1) {
				flag = true;
				continue;
//				ans = Math.max(ans, score);
//				break;
			}
			// 빈 공간을 지나는 경우
			if(map[nr][nc] == 0) {
				nr += dx[d];
				nc += dy[d];
				continue;
			}
			// 블록에 닿은 경우
			if(map[nr][nc] >= 1 && map[nr][nc] <= 5) {
				score++;
				switch (map[nr][nc]) {
				case 1:
					if(d == 0) d = 1;
					else if(d == 1) d = 3;
					else if(d == 2) d = 0;
					else if(d == 3) d = 2;
					break;
				case 2:
					if(d == 0) d = 3;
					else if(d == 1) d = 0;
					else if(d == 2) d = 1;
					else if(d == 3) d = 2;
					break;
				case 3:
					if(d == 0) d = 2;
					else if(d == 1) d = 0;
					else if(d == 2) d = 3;
					else if(d == 3) d = 1;
					break;
				case 4:
					if(d == 0) d = 1;
					else if(d == 1) d = 2;
					else if(d == 2) d = 3;
					else if(d == 3) d = 0;
					break;
				case 5:
					if(d == 0) d = 1;
					else if(d == 1) d = 0;
					else if(d == 2) d = 3;
					else if(d == 3) d = 2;
					break;
				}
				nr += dx[d];
				nc += dy[d];
				continue;
			}
			// 웜홀에 빠진 경우
			if(map[nr][nc] >= 6 && map[nr][nc] <= 10) {
				int num = map[nr][nc];
				if(nr == hole[num - 6][0] && nc == hole[num - 6][1]) {
					nr = hole[num - 6][2];
					nc = hole[num - 6][3];
				}else {
					nr = hole[num - 6][0];
					nc = hole[num - 6][1];
				}
				nr += dx[d];
				nc += dy[d];
				continue;
			}
		}
		ans = Math.max(ans, score);
	}
}
