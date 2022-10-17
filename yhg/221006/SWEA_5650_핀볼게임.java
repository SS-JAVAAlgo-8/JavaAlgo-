package swtest;

import java.io.*;
import java.util.*;

public class SWEA_5650_핀볼게임 {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] board;
	static List<int[][]> wormhole;
	static int N;
	static int result;
	static Deque<Ball> dq;

	static class Ball {
		int x;
		int y;
		int startX;
		int startY;
		int direction;
		int score;

		public Ball(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.startX = x;
			this.startY = y;
			this.score = 0;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int testcase = 1; testcase <= T; testcase++) {
			N = sc.nextInt();
			result = Integer.MIN_VALUE;
			board = new int[N][N];
			wormhole = new ArrayList<>();
			for (int i = 0; i < 11; i++) {
				int[][] temp = new int[2][2];
				temp[0][0] = -1;
				temp[0][1] = -1;
				temp[1][0] = -1;
				temp[1][1] = -1;
				wormhole.add(temp);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int temp = sc.nextInt();

					if (temp >= 6 && temp <= 10) {
						if (wormhole.get(temp)[0][0] == -1 && wormhole.get(temp)[0][1] == -1) {
							wormhole.get(temp)[0][0] = i;
							wormhole.get(temp)[0][1] = j;
						} else {
							wormhole.get(temp)[1][0] = i;
							wormhole.get(temp)[1][1] = j;
						}
					}

					board[i][j] = temp;
				}
			}

			

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 0) {
						play(i,j,0);
						play(i,j,1);
						play(i,j,2);
						play(i,j,3);
					}
				}
			}

			System.out.println("#" + testcase + " " + result);
		}

	}

	private static void play(int i, int j, int direction) {
		Ball now = new Ball(i, j, direction);
        while (true) {
			int x = now.x;
			int y = now.y;
			int d = now.direction;
			int s = now.score;
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N){
				d = turn(d, -1);
				now.direction = d;
				now.x = nx;
				now.y = ny;
				now.score = s + 1;
				continue;
			}
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (nx == now.startX && ny == now.startY) {
					result = Math.max(result, s);
					return;
				}
				if (board[nx][ny] == -1) {
					result = Math.max(result, s);
					return;
				}
				if (board[nx][ny] == 0) {
					now.x = nx;
					now.y = ny;
				}

				else if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
					d = turn(d, board[nx][ny]);
					now.x = nx;
					now.y = ny;
					now.score = s + 1;
					now.direction = d;
				}

				else if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
					int[] posi = jump(board[nx][ny], nx, ny);
					now.x = posi[0];
					now.y = posi[1];
				}
			} 
			
		}
	}

	private static int[] jump(int i, int x, int y) {
		int[][] info = wormhole.get(i);
		if (info[0][0] == x && info[0][1] == y) {
			return new int[] { info[1][0], info[1][1] };
		} else {
			return new int[] { info[0][0], info[0][1] };
		}
	}

	private static int turn(int d, int type) {
		// 0 --> 위 , 1--> 오른쪽 , 2--> 아래 , 3--> 왼쪽
		if (type == 1) {
			if (d == 0)
				return 2;
			if (d == 1)
				return 3;
			if (d == 2)
				return 1;
			if (d == 3)
				return 0;
		}
		if (type == 2) {
			if (d == 0)
				return 1;
			if (d == 1)
				return 3;
			if (d == 2)
				return 0;
			if (d == 3)
				return 2;
		}
		if (type == 3) {
			if (d == 0)
				return 3;
			if (d == 1)
				return 2;
			if (d == 2)
				return 0;
			if (d == 3)
				return 1;
		}
		if (type == 4) {
			if (d == 0)
				return 2;
			if (d == 1)
				return 0;
			if (d == 2)
				return 3;
			if (d == 3)
				return 1;
		}
		if (type == 5 || type == -1) {
			if (d == 0)
				return 2;
			if (d == 1)
				return 3;
			if (d == 2)
				return 0;
			if (d == 3)
				return 1;
		}
		return 0;
	}

}
