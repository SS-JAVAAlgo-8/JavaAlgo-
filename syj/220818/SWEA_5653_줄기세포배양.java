package study.day0825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5653_줄기세포배양  {
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] check;
	static int N, M, K;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/5653.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N + 375][M + 375];	// 최대 넓이의 맵 생성
			copyMap = new int[N + 375][M + 375];
			check = new boolean[N + 375][M + 375];
			for (int i = 0; i < map.length; i++) {	// 맵 모든 칸 -1로 초기화
				Arrays.fill(map[i], -2);
			}
			int num;
			for (int i = 0; i < N; i++) {	// 0이 아닐 때만 값 넣기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					num = Integer.parseInt(st.nextToken());
					if(num == 0) continue; 
					map[375/2 + i][375/2 + j] = num; 
				}
				
			}
			for (int i = 0; i < map.length; i++) {	// 세포배양할 카피맵
				copyMap[i] = Arrays.copyOf(map[i], map[i].length);
			}
			
			for (int i = 0; i < K; i++) {
				stemCells();
			}
			
			int ans = 0;
			for (int i = 0; i < copyMap.length; i++) {
				for (int j = 0; j < copyMap[i].length; j++) {
					if(copyMap[i][j] < 0) continue;
					if(!check[i][j]) {
						if(copyMap[i][j] >= 0) ans++;
					}else {
						if(copyMap[i][j] > 0) ans++;
					}
				}
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	private static void stemCells() {
		for (int i = 0; i < copyMap.length; i++) {	// 활성 상태인 곳(값이 0인 곳)에서 배양함수 불러오기
			for (int j = 0; j < copyMap[i].length; j++) {
				if(copyMap[i][j] < 0) continue;
				if(!check[i][j] && copyMap[i][j] == 0) culture(i, j);
				if(map[i][j] > 0 && copyMap[i][j] >= 0) copyMap[i][j]--;
			}
		}
		
		for (int i = 0; i < copyMap.length; i++) {	// 세포배양 후 배양된 곳 추가
			for (int j = 0; j < copyMap[i].length; j++) {
				if(copyMap[i][j] < 0) continue;
				if(map[i][j] < copyMap[i][j]) map[i][j] = copyMap[i][j];
			}
		}
	}
	private static void culture(int r, int c) {
		for (int i = 0; i < 4; i++) {	// 세포 배양
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(check[nr][nc]) continue;
			if(copyMap[nr][nc] < 0 && map[nr][nc] < 0) copyMap[nr][nc] = map[r][c];
			else if(copyMap[nr][nc] > 0 && map[nr][nc] < 0) {
				 if(copyMap[nr][nc] < map[r][c]) copyMap[nr][nc] = map[r][c]; 
			}
		}
		check[r][c] = true;
		copyMap[r][c] = map[r][c];
	}
}
