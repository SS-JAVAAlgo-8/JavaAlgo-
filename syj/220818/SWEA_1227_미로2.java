/*
 * bfs + 완전탐색
 * 
 * 방문체크 + 재귀
 * boolean type return을 통해 도착하면 종료할 수 있도록 구현
 * 
 */
package study.day0825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_1227_미로2 {	// 메모리 : 23964 kb	실행시간 : 121 ms
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/1227.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			map = new int[100][100];
			boolean[][] isVisited = new boolean[100][100];
			int r = 0;
			int c = 0;
			for (int i = 0; i < 100; i++) {
				char[] ch = br.readLine().toCharArray();
				for (int j = 0; j < 100; j++) {
					map[i][j] = ch[j] - '0';
					if(map[i][j] == 2) {	// 시작위치 저장
						r = i;
						c = j;
					}
				}
			}
			isVisited[r][c] = true;	// 시작위치 방문체크
			sb.append("#" + T + " " + (maze(r, c, isVisited) ? 1 : 0) + "\n");	// 도착지에 도착할 수 있는지 여부에 따라 결과 저장
		}
		System.out.println(sb);
	}
	private static boolean maze(int r, int c, boolean[][] isVisited) {	// 미로찾기 시작
		if(map[r][c] == 3) return true;	// 도착지에 도달했을 경우 true 반환
		for (int i = 0; i < 4; i++) {	// 4방탐색
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(isIn(nr, nc) && !isVisited[nr][nc] && map[nr][nc] != 1) {	// 인덱스가 유효하고 1이 아닐 경우 이동
				isVisited[nr][nc] = true;
				if(maze(nr, nc, isVisited)) return true;
			}
		}
		return false;
	}
	private static boolean isIn(int nr, int nc) {	// 인덱스 유효성 검사
		if(nr >= 0 && nr < 100 && nc >= 0 && nc < 100) return true;
		return false;
	}
}