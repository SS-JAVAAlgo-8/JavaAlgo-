package com.ssafy.recur.swea.sol5650;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 걸린시간 : 1시간 30분 ~ 2시간
public class Solution {
	static class Pinball {
		int x, y, direc;

		public Pinball(int x, int y, int direc) {
			this.x = x;
			this.y = y;
			this.direc = direc;
		}
	}

	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int startx, starty, max_value, N;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
        // 초기 데이터 입력받기
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
				}
			}

			max_value = Integer.MIN_VALUE;
            // 맵을 전체 탐색하면서 핀볼을 내려놓을 수 있는 경우 각 방향마다 게임 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int l = 0; l < 4; l++) {
							startx = i;
							starty = j;
							Pinball p = new Pinball(i, j, l);
							gamestart(p, 0);
						}
					}
				}
			}
			System.out.println("#" + tc + " " + max_value);
		}
	}

	
	
	
	
	private static void gamestart(Pinball p, int score) {
		while (true) {
            // 1일경우 공의 방향이 0 -> 3 , 1 -> 0 , 2 -> 1 , 3 -> 2
			if (map[p.x][p.y] == 1) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 3;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 1;
					break;
				case 3:
					p.direc = 2;
				}
			}
            // 2일경우 공의 방향이 0 -> 1 , 1 -> 3 , 2 -> 0 , 3 -> 2
			if (map[p.x][p.y] == 2) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 3;
					break;
				case 2:
					p.direc = 0;
					break;
				case 3:
					p.direc = 2;
				}
			}
            // 3일경우 공의 방향이 0 -> 1 , 1 -> 2 , 2 -> 3 , 3 -> 0
			if (map[p.x][p.y] == 3) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 2;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 0;
				}
			}
            // 4일경우 공의 방향이 0 -> 2 , 1 -> 0 , 2 -> 3 , 3 -> 1
			if (map[p.x][p.y] == 4) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 2;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 1;
				}
			}
            // 5일 경우 반대방향
			if (map[p.x][p.y] == 5) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 2;
				}
			}
            // 설정한 방향 기준 한 칸 이동
			p.x += dx[p.direc]; 
			p.y += dy[p.direc];
			// 이동 한 방향이 범위 밖이면 반대로 방향바꾸고 한 칸 이동
			if (isIn2(p.x,p.y)) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 2;
				}
				p.x += dx[p.direc];
				p.y += dy[p.direc];
			}
            // 웜홀을 만날경우 맵을 전체탐색해서 위치가 같지않고 값이 같은 곳을 찾아서 그곳으로 핀볼 위치 이동
			if (map[p.x][p.y] > 5) {
				outer: for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[p.x][p.y] == map[i][j] && (p.x != i || p.y != j)) {
							p.x = i;
							p.y = j;
							break outer;
						}
					}
				}
			}
			// 핀볼이 시작위치로 돌아오거나 웜홀을 만날경우 점수 갱신 후 탈출
			if ((p.x == startx && p.y == starty) || map[p.x][p.y] == -1) {
				max_value = Math.max(max_value, score);
				return;
			}
		}
	}
	
	private static boolean isIn2(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return true;
		return false;
	}
}
