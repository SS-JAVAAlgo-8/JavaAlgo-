package algostudy.day0908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.09.15 20:10
 * 	풀이 종료 시간: 22.09.20 01:34
 *  풀이 시간 : 50분 + 30분 = 80분
 * 
 * 	목표: N개의 구슬 떨어뜨려 최대한 많은 벽돌 제거했을 때 남은 벽돌의 개수 구하기
 * 
 *	조건
 * 	1. 구슬은 좌, 우로만 이동 가능 -> 항상 맨 위에 있는 벽돌만 깨뜨릴 수 O
 * 	2. (벽돌에 적힌 숫자 -1) 칸 만큼 상하좌우로 같이 제거
 *  3. 이 때 제거되는 범위 내 벽돌은 동시 제거
 *  4. 빈 공간 있을 경우 벽돌은 밑으로 떨어짐
 *  5. 구슬 개수 주어짐 : N
 * 
 * 	풀이 계획
 * 	1. 벽돌 정보가 하나라도 1보다 큰 경우 dfs로 모든 경우 탐색 
 *    - 가지치기 -> 벽돌 정보가 모두 1인 경우, 0인 경우 남은 벽돌 개수 : 처음 벽돌 개수 - 구슬 개수
 *    - 종료조건 -> cnt가 구슬 개수(N)인 경우 최소 남은 벽돌 수 갱신 후 return
 *  2. 벽돌 깨기 함수 
 *    - 상하좌우로 (벽돌 숫자-1)칸 탐색하여 방문 처리(0으로 처리)한 후 1보다 크면 큐에 넣어 다시 탐색
 *  3. 벽돌 아래로 내리는 함수
 *    - 큐가 빌 때까지 반복하여 깬 벽돌이 모두 0이 되면 벽돌 아래로 빈공간 없도록 이동
 *  4. 남아있는 벽돌 개수 세는 함수
 *    - 0보다 큰 벽돌 개수 세기
 *    -> 이 개수를 통해 min값 갱신 -> 최종적으로 가장 적게 남았을 때의 벽돌 개수 도출
 */


public class Solution_5656_벽돌깨기 { // 67188kb 243ms
	static class Point{
		int x;
		int y;
		int s;
		
		public Point(int x, int y, int s) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	static int[][] map;
	static int N, W, H, min;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			min = Integer.MAX_VALUE;
			map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#").append(t).append(" ");
			// 깰 벽돌이 없는 경우
			if(count(map)==0) {
				sb.append(0).append("\n");
				continue;
			}
			
			// 깰 벽돌이 있는 경우
			dfs(0, map);
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	// cnt : 구슬 개수 
	private static void dfs(int cnt, int[][] map) {
		// 구슬 N개를 다 사용했을 때 남아있는 벽돌 개수 카운트하여 최대값 갱신
		if(cnt==N) {
			min = Math.min(min, count(map));
			return;
		}

		// 복사 위한 배열
		int[][] copy = new int[H][W];
		
		for(int col=0; col<W; col++) {
			int row =0;
			while(row<H && map[row][col]==0) ++row;
			if(row==H) dfs(cnt+1, map);
			else {
				// 깊은 복사
				for(int j=0; j<H; j++) {
					copy[j] = map[j].clone();
				}
				
				// 상하좌우 벽돌 깨기
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(row, col, copy[row][col]));
				while(!q.isEmpty()) {
					Point point = q.poll();

					copy[point.x][point.y] = 0;
					if(point.s > 1) {
						for (int d = 0; d < 4; d++) {
							int dx = point.x;
							int dy = point.y;
							
							for (int k = 0; k < point.s - 1; k++) {
								dx += dr[d];
								dy += dc[d];
								
								if (dx>=0 && dx<H && dy>=0 && dy<W && copy[dx][dy] != 0){
									q.add(new Point(dx, dy, copy[dx][dy]));
									copy[dx][dy] = 0;
								}
							}
						}						
					}
				}
				
				// 빈 공간 없도록 아래로 벽돌 이동하기
				down(copy);
				
				// 다음 구슬 떨어뜨리기
				dfs(cnt+1, copy);
			}
		}
	}
	
	private static void draw(int[][] map) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// 아래에서 올라와 0인 곳을 찾고(a) 그 위에 1이상의 벽돌이 있으면 아래(a)로 벽돌 이동
	private static void down(int[][] map){
        for (int y = 0; y < W; y++) {
            int x = H-1;
 
            while(x > 0){
                if (map[x][y] == 0){
                    int dx = x-1;
 
                    while(dx > 0 && map[dx][y] == 0) dx--;
 
                    map[x][y] = map[dx][y];
                    map[dx][y] = 0;
                }
                x--;
            }
        }
    }
	
	
	// 개수 세는 함수
	private static int count(int[][] map) {
		int count = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j]>0) count++;
			}
		}
		return count;
	}
}
