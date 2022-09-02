package swtest;

import java.util.*;
import java.io.*;

public class SWEA_2819_격자판붙이기 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static String[][] board;
	static Set<String> set = new HashSet<>();
	static Deque<String[]> dq = new ArrayDeque<>();
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			board = new String[4][];
			for (int i = 0; i < 4; i++) {
				board[i] = br.readLine().split(" ");
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					check(i, j, board[i][j]);
				}
			}
			System.out.println("#" + testcase + " " + set.size());
		}
	}
	// 4방탐색 시작
	private static void check(int i, int j, String string) {
		dq.add(new String[] { Integer.toString(i), Integer.toString(j), string });
		visited = new boolean[4][4];
		while (dq.size() > 0) {
			String[] now = dq.removeFirst();
			for (int d = 0; d < 4; d++) {
				int nx = Integer.parseInt(now[0]) + dx[d];
				int ny = Integer.parseInt(now[1]) + dy[d];
				String temp = now[2];
				if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !visited[nx][ny]) {
					//길이가 7이되면 set에 추가
					if (temp.length() == 7)
						set.add(temp);
					else // 계속 길이 붙여보기
						dq.addLast(new String[] { Integer.toString(nx), Integer.toString(ny), temp + board[nx][ny] });
				}
			}
		}
		dq.clear();
	}
}
