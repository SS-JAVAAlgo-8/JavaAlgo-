package swtest;

import java.io.*;
import java.util.*;

public class SWEA_4014_활주로건설 {
	static int N, X;
	static int[][] board_col, board_row;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {

			String[] input = br.readLine().split(" ");

			N = Integer.parseInt(input[0]);
			X = Integer.parseInt(input[1]);

			board_col = new int[N][N];
			board_row = new int[N][N];
			for (int i = 0; i < input.length; i++) {
				input = br.readLine().split(" ");
				for (int j = 0; j < input.length; j++) {
					board_col[i][j] = Integer.parseInt(input[j]);
					board_row[j][i] = Integer.parseInt(input[j]);
				}
			}
			answer = 0;
			answer += check_col();
			answer += check_row();
			
			System.out.println("#" + testcase + " " + answer);
				
		}

	}

	private static int check_row() {
		int result = 0;
		outer: for (int i = 0; i < N; i++) {
			boolean flag = true;

			for (int j = 1; j < N; j++) {
				int checker = board_row[i][j] - board_row[i][j - 1];
				if (checker >= 2 || checker <= -2) {
					flag = false;
					continue outer;
				}
			}

			int prev = board_row[i][0];
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if (prev == board_row[i][j])
					cnt++;
				else if (prev < board_row[i][j]) {
					if (cnt < X) {
						flag = false;
					}
					prev = board_row[i][j];
					cnt = 1;
				} else {
					prev = board_row[i][j];
					cnt = 1;
					for (int k = 1; k < X; k++) {
						if (j + k < N) {
							if (prev == board_row[i][j + k])
								cnt++;
						}
					}
					if (cnt != X) flag = false;
					j = j + X - 1;
					cnt = 0;
				}
			}
			if (flag)
				result +=1;
		}
		
		return result;
	}

	private static int check_col() {
		int result = 0;
		outer: for (int i = 0; i < N; i++) {
			boolean flag = true;

			for (int j = 1; j < N; j++) {
				int checker = board_col[i][j] - board_col[i][j - 1];
				if (checker >= 2 || checker <= -2) {
					flag = false;
					continue outer;
				}
			}

			int prev = board_col[i][0];
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if (prev == board_col[i][j])
					cnt++;
				else if (prev < board_col[i][j]) {
					if (cnt < X) {
						flag = false;
					}
					prev = board_col[i][j];
					cnt = 1;
				} else {
					prev = board_col[i][j];
					cnt = 1;
					for (int k = 1; k < X; k++) {
						if (j + k < N) {
							if (prev == board_col[i][j + k])
								cnt++;
						}
					}
					if (cnt != X) flag = false;
					j = j + X - 1;
					cnt = 0;
				}
			}
			if (flag)
				result +=1;
		}
		
		return result;
	}
}
