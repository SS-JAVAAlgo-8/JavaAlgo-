/*
dfs + 완탐

걸린시간 : 30분

1. 맵 입력받으면서 최대값 저장
2. 이중 포문으로 최대값인 곳이 나오면 dfs 돌리기
< dfs 로직 >
- 4방탐색
- 만약 다음 위치가 현재보다 낮을 경우 그대로 dfs재귀 호출
- 다음 위치가 현재보다 같거나 높을 경우 k만큼 깍으면 작아지는지 확인 후 / flag(공사여부) true로 바꿔서 dfs 호출
- 이 다음부터는 다음 위치가 현재보다 낮을 경우만 이동 가능
- 현재 위치가 0일 경우 등산로 길이 최대값 갱신 후 return
- 4방 탐색 후 등산로 길이 최대값 갱신 후 return
*/
package study.day1006;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {	// 메모리 : 27,432 kb	 	실행시간 : 104 ms
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, K, ans;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			int max = 0;
			// 맵 입력받으면서 봉우리(최대값) 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			
			ans = 0;
			// 봉우리가 나오면 dfs 출발 ~!
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[N][N];	// 방문체크 배열은 봉우리마다 초기화
					visited[i][j] = true;
					if(map[i][j] == max) dfs(i, j, 1, false);	// 좌표, 공사 가능 깊이, 등산로 길이, 공사 했는지 여부
					visited[i][j] = false;
				}
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int r, int c, int cnt, boolean flag) {
		if(map[r][c] == 0) {	// 현재 높이가 0일 경우 더 이상 낮을 수는 없기 때문에 return
			ans = Math.max(ans, cnt);
			return;
		}
		for (int d = 0; d < 4; d++) {	// 4방 탐색
			int nr = r + dx[d];
			int nc = c + dy[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
			if(map[nr][nc] < map[r][c]) {	// 다음 위치가 현재보다 낮을 경우 변동사항 없이 dfs호출
				visited[nr][nc] = true;
				dfs(nr, nc, cnt + 1, flag);
				visited[nr][nc] = false;
			}
			if(map[nr][nc] >= map[r][c]) {	// 다음 위치가 현재보다 같거나 높은 경우
				if(map[nr][nc] - map[r][c] + 1 <= K && !flag) {	// 높이 차이가 공사 가능할 정도인지 판단 && 이전에 공사를 하지 않았을 경우
					visited[nr][nc] = true;
					int n = map[nr][nc];
					map[nr][nc] = map[r][c] - 1;
					dfs(nr, nc, cnt + 1, !flag);	// 공사 했다고 바꾸고 dfs 호출
					map[nr][nc] = n;
					visited[nr][nc] = false;
				}
			}
		}
		ans = Math.max(ans, cnt);
		return;
	}
}
