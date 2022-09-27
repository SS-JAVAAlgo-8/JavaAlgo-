package study.day220908.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  열단위로 벽돌의 위치를 읽어와 첫번째 존재하는 벽돌을 하나씩 선택해서 깨뜨린다.
 *  각 공마다 깨뜨린 벽돌의 갯수를 합산하고, 주어진 공으로 깨뜨리는 모든 경우를 비교하여 깨뜨린 최대 벽돌 갯수를 반환한다.
 *  DFS()를 통해 완전 탐색으로 경우의 수들을 비교
 *  
 *  공을 떨어뜨린 후, 벽돌 위치 정보를 갱신하는 함수의 구현 필요
 *  
 */
public class SWEA_5656_BreakBricks {

	static int N, W, H, ans;
	static int [][] bricks;
	
	static int dr[] = {0, 1, 0, -1}; // 우, 하, 좌, 상
	static int dc[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
		
			ans = Integer.MAX_VALUE;
			
			bricks = new int[H][W];		
			
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					bricks[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0);


			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void dfs(int cnt) {
		if(cnt == N) {
			// 현재 상태의 남은 벽돌 갯수 비교
//			nowbricks();
			int count = 0;
			for(int[] r : bricks) {
				for(int c : r) {
					if(c != 0) count++;
				}
			}
			ans = Math.min(ans, count);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if(bricks[j][i] == 0) continue;
				
				int copy_bricks[][] = new int[H][W];	
				copymap(bricks, copy_bricks); // 현재 벽돌의 상태를 저장해놓는다.
				
				chainboomb(j, i); // 해당위치에 구슬이 명중 했을때 일어나는 모든 폭발 수행
				
				afterboomb();  // 폭발 후, 벽돌들의 상태를 갱신(바닥으로 밀착)
				
				if(nowbricks() == 0) {// 현재 상태 출력
					// 모든 공을 떨어뜨리지도 못하고 중간에 벽돌이 다 없어졌을때, 기저 조건에 가지 않고 dfs가 끝나게됨 따라서 벽돌갯수가 갱신이 안됨
					// 이런 경우 dfs 탈출 후, 마지막 체크 
					ans = 0;
					return;
				}; 
				
				dfs(cnt + 1);  // 다음 번째 공 떨어뜨리기
				
				copymap(copy_bricks, bricks); // 벽돌 상태 되돌리기
				
				break; // 그 다음 경우의 수는 다음 행으로 내려가는게 아니라 열 단위 즉, 옆으로 이동해서 가장 첫번째 벽돌을 깨야한다.
			}
		}
		
		
	}

	private static int nowbricks() {
		int num = 0;
		for(int[] r : bricks) {
			for(int c : r) {
				if(c != 0) num++;
//				System.out.print(c + " ");
			}
//			System.out.println();
		}
		return num;
	}

	private static void afterboomb() {
		for (int i = 0; i < W; i++) {
			for (int j = H-1; j >= 0; j--) {
				if(bricks[j][i] == 0) continue;
				
				for (int k = j+1; k < H; k++) {
					if(bricks[k][i] != 0) {
						int temp = bricks[k-1][i];
						bricks[k-1][i] = bricks[j][i];
						bricks[j][i] = temp;
						break;
					}
					else if(k == H-1 && bricks[k][i] == 0) {
						bricks[k][i] = bricks[j][i];
						bricks[j][i] = 0;
					}
				}				
			}
		}
	}

	private static void copymap(int[][] origin, int[][] copy) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = origin[i][j];
			}
		}
	}

	private static void chainboomb(int r, int c) {
		Queue<Integer> qu = new ArrayDeque<>();
		boolean [][] chaincheck = new boolean[H][W];
		boolean [][] boombcheck = new boolean[H][W];
		
		qu.add(r);
		qu.add(c);
		
		while(!qu.isEmpty()) {
			
			for (int i = 0, size = qu.size(); i < size; i+=2) {
				
				int row = qu.poll();
				int col = qu.poll();
				
				chaincheck[row][col] = true;
				boombcheck[row][col] = true;
				
				if(bricks[row][col] == 1) continue;
				
				for (int d = 0; d < 4; d++) {
					
					for (int m = 1; m < bricks[row][col]; m++) {
						
						int nr = row + dr[d]*m;
						int nc = col + dc[d]*m;
						if(isIn(nr, nc) && bricks[nr][nc] != 0 && !boombcheck[nr][nc]) {
							
							if(bricks[nr][nc] == 1) {
								chaincheck[nr][nc] = true;
								continue;
							}
							
							chaincheck[nr][nc] = true;
							qu.add(nr);
							qu.add(nc);
						}
					}
				}
				
			}
				
		}
		// 연쇄 폭발에 해당하는 위치 check 완료, check된 위치 0 으로 값 변경
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(chaincheck[i][j]) {
					bricks[i][j] = 0;
				}
			}
		}
		
		
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < H && nc < W;
	}

}
/*

1
4 12 15
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9
9 9 9 9 9 9 9 9 9 9 9 9

1
1 3 3
0 1 0
2 1 2
1 2 1



*/
