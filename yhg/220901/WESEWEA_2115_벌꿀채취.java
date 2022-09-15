package swtest;

import java.io.*;
import java.util.*;

public class WESEWEA_2115_벌꿀채취 {
	static int[][] board;
	static List<int[]> posi = new ArrayList<>();
	static List<int[]> posi_c = new ArrayList<>();
	static int[] honey1;
	static int[] honey2;
	static int[] honey;
	static List<Integer> clist = new ArrayList<>();
	static int h1, h2;
	static int result;
	static int x1_1, y1_1, x2_1, y2_1;
	static int x1_2, y1_2, x2_2, y2_2;
	static int N, M, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);

			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				for (int j = 0; j < input.length; j++) {
					board[i][j] = Integer.parseInt(input[j]);
				}
			}

			makeposi();

			result = 0;
			honey1 = new int[M];
			honey2 = new int[M];
			honey = new int[M * 2];
			com(0);
			posi.clear();
			posi_c.clear();
			clist.clear();
			System.out.println("#" + testcase + " " + result);
		}
	}

	private static void com(int index) {
		if (posi_c.size() == 2) {
			if (isOkay())
				play();
			return;
		}

		for (int i = index; i < posi.size(); i++) {
			posi_c.add(posi.get(i));
			com(i + 1);
			posi_c.remove(posi_c.size() - 1);
		}
	}

	private static void play() {

		int oneisOkay = 0;
		int twoisOkay = 0;
		int index = 0;

		for (int i = 0; i < M; i++) {
			honey1[i] = board[x1_1][y1_1 + i];
			honey[index] = board[x1_1][y1_1 + i];
			index += 1;
		}
		for (int i = 0; i < M; i++) {
			honey2[i] = board[x1_2][y1_2 + i];
			honey[index] = board[x1_2][y1_2 + i];
			index += 1;
		}
		Arrays.sort(honey);
		int hsum = 0;
		int onesum = 0;
		int twosum = 0;

		h1 = 0;
		for (int i = 1; i <= M; i++) {
			check(0, i, honey1, 1);
		}
		hsum += h1;
		clist.clear();
		h2 = 0;
		for (int i = 1; i <= M; i++) {
			check(0, i, honey2, 2);
		}
		hsum += h2;
		clist.clear();

		clist.clear();
		result = Math.max(result, hsum);

	}

	private static void check(int depth, int len, int[] h, int id) {
		if (clist.size() == len) {
			int r = 0;
			int sum = 0;
			for (int i : clist) {
				sum += i;
				r += i * i;
			}
			if (sum > C)
				return;

			if (id == 1)
				h1 = Math.max(h1, r);
			else
				h2 = Math.max(h2, r);
			return;
		}

		for (int i = depth; i < h.length; i++) {
			clist.add(h[i]);
			check(i + 1, len, h, id);
			clist.remove(clist.size() - 1);
		}
	}

	private static boolean isOkay() {
		x1_1 = posi_c.get(0)[0];
		y1_1 = posi_c.get(0)[1];
		x2_1 = posi_c.get(0)[2];
		y2_1 = posi_c.get(0)[3];
		x1_2 = posi_c.get(1)[0];
		y1_2 = posi_c.get(1)[1];
		x2_2 = posi_c.get(1)[2];
		y2_2 = posi_c.get(1)[3];

		if (x1_1 != x1_2)
			return true;
		for (int i = y1_1; i <= y2_1; i++) {
			if (i >= y1_2 && i <= y2_2)
				return false;
		}
		return true;
	}

	private static void makeposi() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				if (j + M - 1 < N)
					posi.add(new int[] { i, j, i, j + M - 1 });
			}
		}
	}
}
