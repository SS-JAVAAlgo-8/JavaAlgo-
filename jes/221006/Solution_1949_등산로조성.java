package algostudy.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.10 19:00
 * 	풀이 종료 시간: 22.10.10 20:30
 *  풀이 시간 : 설계 30분 + 코딩 30분 + 디버깅 10분 = 70분
 *  
 * 	목표: 한 군데 K만큼 지형을 깎을 수 있을 때 만들 수 있는 가장 긴 등산로의 길이 출력
 * 
 *	조건
 * 	1. N*N 크기의 부지
 *  2. 등산로 만드는 규칙
 *     2-1. 가장 높은 봉우리에서 시작(가장 높은 곳에서 시작)
 *     2-2. 사방으로 이전보다 낮은 곳에만 등산로 설치 가능(대각선 불가능)
 *     2-3. 단 한군데만 최대 K(최대 공사가능 깊이)만큼 지형을 깎는 공사를 할 수 있음 
 * 
 *  풀이 계획
 *  - BFS로 계획했다가 DFS로 변경
 *  
 * 	풀이 방법
 *  1. 가장 높은 위치 찾아서 DFS
 *  2. 이전 높이보다 현재 위치가 높다면 이전 높이 -1이 가능한지 여부 판단(K)
 *     2-1. 만약 가능하다면 깎는다고 가정하고 다음 DFS
 *     2-2. 불가능하다면 return
 *  3. 이전 높이보다 현재 위치가 낮다면 DFS
 *  4. 작업 마쳤을 때 길이가 기존 등산로 길이보다 길다면 최댓값 갱신
 *  
 */

public class Solution_1949_등산로조성 { // 29176kb 117ms
	static int N, K, ans;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException { // 29176kb 117ms
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한 변의 길이
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
			map = new int[N][N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}

			// 가장 높은 위치(시작점) 찾아서 dfs
			ans = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) { // 가장 높은 위치일 때 시작
						visited[i][j] = true;
						solve(i, j, max, 1, 0);
						visited[i][j] = false;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	private static void solve(int x, int y, int height, int dist, int cut) {
		for (int i = 0; i < 4; i++) {
			int dx = x + dr[i];
			int dy = y + dc[i];
			if (dx < 0 || dx >= N || dy < 0 || dy >= N || visited[dx][dy]) continue;
			
			// 이전 높이보다 현재 높이가 높거나 같은 경우(깎아야 이동 가능)
			if (height <= map[dx][dy]) {
				if(cut == 0) {
					// 현재 높이 = 이전 높이 - 1 가 가능한 경우
					if(map[dx][dy]-height+1 <= K) {
						visited[dx][dy] = true;
						solve(dx, dy, height-1, dist+1, cut+1);
						visited[dx][dy] = false;
					}
				}
			}
			else { // 이전 높이보다 현재 높이가 낮은 경우(이동 가능)
				visited[dx][dy] = true;
				solve(dx, dy, map[dx][dy], dist+1, cut);
				visited[dx][dy] = false;
			}
			
			ans = Math.max(ans, dist);
		}
	}
}
