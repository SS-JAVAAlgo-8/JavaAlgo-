package BOJ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_17070_파이프옮기기 {
	static int[] row;
	static int[] cal;
	static int[] dia;
	static int[][] board;
	static int n;
	static Deque<int[]> dq = new ArrayDeque<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		board = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		int d = 1;
		int result = 0;
		dq.add(new int[] { 0, 1, d });

		while (dq.size() > 0) {
			int[] now = dq.removeFirst();

			int x = now[0];
			int y = now[1];

			if (x == n - 1 && y == n - 1) {
				result += 1;
				continue;
			}
			int pd = now[2];
			if (pd == 1) {
				checkOne(x, y);
				checkTwo(x, y);
			} else if (pd == 2) {
				checkOne(x, y);
				checkTwo(x, y);
				checkThree(x, y);
			} else if (pd == 3) {
				checkTwo(x, y);
				checkThree(x, y);
			}
		}
		System.out.println(result);
	}

	private static void checkOne(int x, int y) {
		int ny = y + 1;
		int nx = x + 1;
		if (ny < n && board[x][ny] == 0)
			dq.addLast(new int[] { x, ny, 1 });
	}

	private static void checkTwo(int x, int y) {
		int ny = y + 1;
		int nx = x + 1;
		if (nx < n && ny < n && board[nx][y] + board[nx][ny] + board[x][ny] == 0)
			dq.addLast(new int[] { nx, ny, 2 });
	}

	private static void checkThree(int x, int y) {
		int ny = y + 1;
		int nx = x + 1;
		if (nx < n && ny - 1 < n && board[nx][ny - 1] == 0)
			dq.addLast(new int[] { nx, ny - 1, 3 });
	}
}
