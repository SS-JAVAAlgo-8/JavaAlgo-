package swtest;

import java.io.*;
import java.util.*;

public class SWEA_5656_벽돌깨기 {
	static int N, W, H;
	static int[][] board;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static List<Integer> c_list = new ArrayList<>();
	static Deque<int[]> dq = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			String[] input = br.readLine().split(" ");
			int min = Integer.MIN_VALUE;
			N = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			H = Integer.parseInt(input[2]);

			board = new int[H][W];

			for (int i = 0; i < H; i++) {
				String[] temp = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(temp[j]);
				}
			}

			com();
			if (min == -1) // 벽돌이 모두 깨진 경우
				System.out.println("#" + testcase + " " + 0);
			else
				System.out.println("#" + testcase + " " + min);
		}

	}

	private static void com() {
		if (c_list.size() == N) {
			play();
			System.out.println(Arrays.toString(c_list.toArray()));
			return;
		}

		for (int i = 0; i < W; i++) {
			c_list.add(i);
			com();
			c_list.remove(c_list.size() - 1);
		}
	}

	private static void play() {
		int[][] cboard = copy();
		int[][] visited = new int[H][W];
		for (int i = 0; i < c_list.size(); i++) {
			int[] start = find(c_list.get(i), cboard);
			if (start[0] == -1)
				continue;

			dq.add(start);

			while (dq.size() > 0) {

				int[] now = dq.removeFirst();
				int x = now[0];
				int y = now[1];
				int b = now[2];
				visited[x][y] = 1;
				if (b == 1) {
					cboard[x][y] = 0;
					continue;
				}

				for (int k = 0; k < 4; k++) {
					int nx = 0;
					int ny = 0;
					for (int d = 0; d < cboard[x][y]; d++) {
						nx = x + dx[k] * d;
						ny = y + dy[k] * d;
						if (nx >= 0 && nx < H && ny >= 0 && ny < W && visited[nx][ny] == 0 && cboard[nx][ny] > 0) {
							visited[nx][nx] = 1;
							dq.add(new int[] { nx, ny, cboard[nx][ny] });
							cboard[nx][ny] = 0;
						}
					}
				}

			}

		}
	}

	private static int[] find(int w, int[][] cboard) {
		for (int i = 0; i < H; i++) {
			if (cboard[i][w] != 0)
				return new int[] { i, w, cboard[i][w] };
		}
		return new int[] { -1, -1 };
	}

	private static int[][] copy() {
		int[][] c = new int[H][];
		for (int i = 0; i < H; i++) {
			c[i] = Arrays.copyOf(board[i], W);
		}
		return c;
	}

	static void move(int[][] arr) {
		for (int j = 0; j < W; j++) {
			for (int i = H - 1; i > 0; i--) {
				if (arr[i][j] == 0) {
					int k = i - 1;
					while (k >= 0) {
						if (arr[k][j] != 0) {
							arr[i][j] = arr[k][j];
							arr[k][j] = 0;
							break;
						}
					}
					if (k == -1)
						break;
				}
			}
		}
	}

	static void crush(int x, int y, int[][] arr) {
		dq = new ArrayDeque<>();
		dq.add(new int[] { x, y, arr[x][y] });
		arr[x][y] = 0;

		while (!dq.isEmpty()) {
			int[] now = dq.remove();
			for (int i = 0; i < 4; i++) {
				int nx = now[0];
				int ny = now[1];
				int num = now[3];
				for (int j = 0; j < num - 1; j++) {
					nx += dx[i];
					ny += dy[i];
					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						break;
					if (arr[nx][ny] == 0)
						continue;
					if (arr[nx][ny] > 1)
						dq.add(new int[] { nx, ny, arr[nx][ny] });
					arr[nx][ny] = 0;
				}

			}
		}
	}
}
