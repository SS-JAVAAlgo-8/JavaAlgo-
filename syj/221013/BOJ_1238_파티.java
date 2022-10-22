/*
다익스트라

- 다익스트라를 통해 최단경로를 구해서 저장
- 모든 집을 돌면서 파티장소까지 다녀오는 거리의 최대값을 갱신하여 결과값 출력
*/
package study.day1013;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {	// 메모리 : 24,396 kb		실행시간 : 2,756 ms
	static int N, M, X, ans = Integer.MIN_VALUE;
	static int[][] map;
	static int[][] len;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];	// 인접행렬
		for (int i = 0; i < M; i++) {	// 인접행렬 입력받기
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		len = new int[N + 1][N + 1];	// 최단거리 저장 배열
		for (int i = 0; i <= N; i++) {
			Arrays.fill(len[i], Integer.MAX_VALUE);	// 최대값으로 초기화 
		}
		int minW, minV;
		for (int i = 1; i <= N; i++) {
			len[i][i] = 0;	// 시작정점
			visited = new boolean[N + 1];
			for (int n = 1; n <= N; n++) {
				minW = Integer.MAX_VALUE; 
				minV = -1;
				for (int j = 1; j <= N; j++) {
					if(!visited[j] && minW > len[i][j]) {
						minW = len[i][j];
						minV = j;
					}
				}
				if(minV == -1) continue;
				visited[minV] = true;
				
				for (int j = 1; j <= N; j++) {
					if(!visited[j] && map[minV][j] != 0 && len[i][j] > len[i][minV] + map[minV][j]) {
						len[i][j] = len[i][minV] + map[minV][j];
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if(i == X) continue;
			ans = Math.max(ans, len[i][X] + len[X][i]);
		}
		System.out.println(ans);
	}
}
