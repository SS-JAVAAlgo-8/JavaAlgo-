/*
 	걸린시간 : 2시간
 	
	완탐 + bfs
	
	1. 구슬 객체 생성 (구슬을 쳤을 때 맞는 벽돌 위치, 구슬로 인해 바뀌는 맵 저장하기 위한 객체)
	2. 맵 입력받기
	3. 처음 q에 맨 윗줄 넣어두기 (0이 아닌 곳만)
	4. 구슬치기 (m번)
		4-1. 맵 변경해주는 함수 부르기 (영향받는 모든 벽돌 제거, 빈 공간 없애기)
		4-2. 변경된 맵 받아오기
		4-3. 맨 윗줄 넣기 (0이 아닌 곳만)
			----> 이때, 아무곳도 칠 수 없으면 벽돌이 없다는 뜻 ... 반복문 빠져나오기
	5. 구슬을 m번 치거나 벽돌이 없어서 반복문을 빠져나오면
	   q에 들어있는 구슬 객체의 map을 꺼내서 벽돌 개수 세기 ---> 세면서 최소값 갱신
	6. 최소값 출력
 */

package study.day0908;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {	// 메모리 : 111352kb	실행시간 : 501ms
	static int H, W;
	
	// 구슬 객체
	public static class Marble {
		int r;
		int c;
		int[][] map = new int[H][W];
		
		public Marble(int r, int c, int[][] map) {
			this.r = r;
			this.c = c;
			for (int i = 0; i < H; i++) {
				this.map[i] = Arrays.copyOf(map[i], W);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 결과값 초기화
			int minCnt = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			// 벽돌 맵 입력받기
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Marble> q = new ArrayDeque<>();
			
			// 처음에 맨 윗줄 모두 넣기
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H; j++) {
					if(map[j][i] != 0) {
						q.offer(new Marble(j, i, map));
						break;
					}
				}
			}

			// 구슬치기
			outer : for (int m = 0; m < N; m++) {
				
				int size = q.size();
				for (int s = 0; s < size; s++) {
					Marble marble = q.poll();
					
					// 해당 구슬에 대해 변경된 벽돌 맵 받아오기
					int[][] newMap = new int[H][W];
					int[][] map2 = remove(marble.r, marble.c, marble.map).clone();
					for (int i = 0; i < H; i++) {
						newMap[i] = Arrays.copyOf(map2[i], W);
					}
					
					// 벽돌이 없을 경우 반복문을 빠져나오기 위해 해당 부분 판별해줄 변수
					boolean clear = false;
					// 맨 윗줄 전부 넣기
					for (int i = 0; i < W; i++) {
						for (int j = 0; j < H; j++) {
							if(newMap[j][i] != 0) {
								q.offer(new Marble(j, i, newMap));
								clear = true;
								break;
							}
						}
					}
					if(!clear) {
						q.clear();
						break outer;
					}
				}
			}
			
			// 구슬 다 친 뒤에 최소값 찾기
			if(q.isEmpty()) minCnt = 0;
			else {
				int size = q.size();
				for (int s = 0; s < size; s++) {
					int cnt = 0;
					Marble marble = q.poll();
					int[][] map2 = marble.map.clone();
					
					for (int i = 0; i < H; i++) {
						for (int j = 0; j < W; j++) {
							if(map2[i][j] > 0) cnt++;
						}
					}
					minCnt = Math.min(minCnt, cnt);
				}
			}
			sb.append("#" + t + " " + minCnt + "\n");
		}
		System.out.println(sb);
	}
	
	// 구슬쳤을 때 바뀌는 맵 계산하는 함수
	private static int[][] remove(int r, int c, int[][] map) {
		int[][] newMap = new int[H][W];
		
		int range = map[r][c];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c, range});
		
		// 벽돌제거
		while(!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int[] temp = q.poll();
				int nr = temp[0];
				int nc = temp[1];
				int len = temp[2];
				map[nr][nc] = 0;
				for (int d = 1; d < len; d++) {
					if(nr - d >= 0 && nr - d < H && map[nr - d][nc] != 0) {
						if(map[nr - d][nc] > 1) q.offer(new int[] {nr - d, nc, map[nr - d][nc]});
						map[nr - d][nc] = 0;
					}
					if(nr + d >= 0 && nr + d < H && map[nr + d][nc] != 0) {
						if(map[nr + d][nc] > 1) q.offer(new int[] {nr + d, nc, map[nr + d][nc]});
						map[nr + d][nc] = 0;
					}
					if(nc - d >= 0 && nc - d < W && map[nr][nc - d] != 0) {
						if(map[nr][nc - d] > 1) q.offer(new int[] {nr, nc - d, map[nr][nc - d]});
						map[nr][nc - d] = 0;
					}
					if(nc + d >= 0 && nc + d < W && map[nr][nc + d] != 0) {
						if(map[nr][nc + d] > 1) q.offer(new int[] {nr, nc + d, map[nr][nc + d]});
						map[nr][nc + d] = 0;
					}
				}
			}
		}
		
		// 빈 공간 없애기
		for (int i = 0; i < W; i++) {
			int[] num = new int[H];
			int idx = 0;
			for (int j = 0; j < H; j++) {
				if(map[j][i] > 0) {
					num[idx++] = map[j][i];
				}
			}
			for (int k = idx-1, n = H - 1; k >= 0; k--) {
				newMap[n--][i] = num[k];
			}
		}
		return newMap;
	}
}
