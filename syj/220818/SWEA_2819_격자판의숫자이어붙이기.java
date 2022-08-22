/*
 * 완전탐색
 */
package study.day0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class SWEA_2819_격자판의숫자이어붙이기 {	// 메모리 : 63816 kb	실행시간 : 208 ms
	static String[][] map = new String[4][4];
	static int[] dx = {0, 0, 1, -1};	// 동 -> 서 -> 남 -> 북
	static int[] dy = {1, -1, 0, 0};
	static HashSet<String> result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = new HashSet<>();
			for (int i = 0; i < 4; i++) {
				String[] temp = br.readLine().split(" ");
				for (int j = 0; j < 4; j++) {
					map[i] = temp.clone();
				}
			}
			
			for (int i = 0; i < 4; i++) {	// 모든 위치에서 7글자 문자 만들어서 list에 저장 (HashSet 사용해서 자동 중복제거)
				for (int j = 0; j < 4; j++) {
					sevenNum(i, j, map[i][j], 0);
				}
			}
			sb.append("#" + t + " " + result.size() + "\n");
		}
		System.out.println(sb);
	}
	private static void sevenNum(int r, int c, String str, int cnt) {
		if(cnt == 6) {
			String temp = str;
			result.add(temp);
			return;
		}
		for (int i = 0; i < 4; i++) {	// 사방탐색
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(isIn(nr, nc)) sevenNum(nr, nc, str + map[nr][nc], cnt + 1);
		}
	}
	private static boolean isIn(int r, int c) {
		if(r >= 0 && r < 4 && c >= 0 && c < 4) return true;
		return false;
	}
}
