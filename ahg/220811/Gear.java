import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] magnet;
	static int[][] mag_see = new int[4][2];
	static boolean[] spine;

	public static void main(String[] args) throws Exception {
		magnet = new char[4][8];
		for (int i = 0; i < 4; i++) {
			magnet[i] = br.readLine().toCharArray();
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < 4; i++) {
			mag_see[i][0] = 2;
			mag_see[i][1] = 6;
		}
		for (int i = 0; i < K; i++) {
			spin();
		}
		int score = 0;
		if (magnet[0][(mag_see[0][1] + 2) % 8] == '1') {
			score += 1;
		}
		if (magnet[1][(mag_see[1][1] + 2) % 8] == '1') {
			score += 2;
		}
		if (magnet[2][(mag_see[2][1] + 2) % 8] == '1') {
			score += 4;
		}
		if (magnet[3][(mag_see[3][1] + 2) % 8] == '1') {
			score += 8;
		}
		System.out.println(score);
	}

	static void spin() throws IOException {
		st = new StringTokenizer(br.readLine());
		int mag_num = Integer.parseInt(st.nextToken()) - 1;
		int direc = Integer.parseInt(st.nextToken());
		check();
		move(direc, mag_num);
	}

	static void move(int direc, int mag_num) {
		if (mag_num < 0 || mag_num > 3) {
			return;
		}
		if (direc == 1) {
			if (--mag_see[mag_num][0] < 0)
				mag_see[mag_num][0] = 7;
			if (--mag_see[mag_num][1] < 0)
				mag_see[mag_num][1] = 7;
		} else {
			mag_see[mag_num][0] = (mag_see[mag_num][0] + 1) % 8;
			mag_see[mag_num][1] = (mag_see[mag_num][1] + 1) % 8;
		}

		if (spine[mag_num]) {
			spine[mag_num] = false;
			move(direc * -1, mag_num - 1);
		}
		if (spine[mag_num + 1]) {
			spine[mag_num + 1] = false;
			move(direc * -1, mag_num + 1);
		}
	}

	static void check() {
		spine = new boolean[5];
		for (int i = 0; i < 3; i++) {
			if (magnet[i][mag_see[i][0]] != magnet[i + 1][mag_see[i + 1][1]]) {
				spine[i + 1] = true;
			}
		}
	}
}
