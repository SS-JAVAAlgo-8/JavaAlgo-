package algostudy.day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.19 10:30
 * 	풀이 종료 시간: 22.10.19 11:20
 *  풀이 시간 : 50분
 *  
 * 	목표: 최소 금액 찾기
 * 
 *	조건
 * 	1. 동굴은 NxN 크기이며 각 칸마다 도둑 루피의 크기 K가 있음
 *  2. 칸에 도착하면 K만큼의 루피를 잃음
 *  3. 모든 정수는 0 이상 9 이하인 한 자리 수
 *  4. (0,0) -> (N-1, N-1) 좌표로 이동해야 함
 *  
 *  풀이 계획
 *  - BFS
 *  
 * 	풀이 방법
 *  1. 0,0 에서 N-1, N-1까지 BFS를 통해 탐색
 *  2. visited 배열에 이동할 때마다 최소 금액을 저장하며 다음 방문 할 때 비교하여 더 작은 값이 나올때만 탐색
 *  
 */

public class Main_G4_4485_녹색옷입은애가젤다지 { // 30472kb 264ms

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int t = 1;
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		
		while(true){
			int N = Integer.parseInt(br.readLine());
			if(N==0) break;
			int[][] map = new int[N][N]; // 동굴 각 칸의 정보 저장할 배열
			int[][] visited = new int[N][N]; // 이동할 때 최소 금액 저장할 배열
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				Arrays.fill(visited[i], Integer.MAX_VALUE); // 최소를 구해야 하므로 초기값으로 max 입력
			}
			
			Queue<int[]> q = new LinkedList<>();
			visited[0][0] = map[0][0]; // 초기 시작 위치 (0, 0)
			q.add(new int[] {0, 0});
	
			int min = Integer.MAX_VALUE;
			while(!q.isEmpty()) {
				int[] arr = q.poll();
				int x = arr[0];
				int y = arr[1];
				
				// 목적지에 도착한 경우 최소 금액 갱신
				if(x==N-1 && y==N-1) {
					min = Math.min(min, visited[x][y]);
					continue;
				}
				
				for(int i=0; i<4; i++) {
					int dx = x+dr[i];
					int dy = y+dc[i];
					
					// 범위를 넘어가면 탐색 X
					if(dx<0 || dy<0 || dx>=N || dy>=N) continue;
					// 기존 값보다 큰 경우 그 이후 경로 탐색 X
					if(visited[dx][dy] <= visited[x][y]+map[dx][dy]) continue;
					
					visited[dx][dy] = visited[x][y]+map[dx][dy];
					q.add(new int[] {dx, dy}); 
				}
			}
			sb.append("Problem ").append(t++).append(": ").append(min).append("\n");
		}
		System.out.println(sb);
	}
}