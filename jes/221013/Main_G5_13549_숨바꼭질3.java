package algostudy.day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.17 11:35
 * 	풀이 종료 시간: 22.10.17 11:52
 *  풀이 시간 : 15분
 *  
 * 	목표: 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 찾기
 * 
 *	조건
 * 	1. 수빈 위치 : N(0<=N<=100000)
 *  2. 동생 위치 : K(0<=K<=100000)
 *  3. 수빈 이동 방법(현재 위치가 X라고 가정했을 때)
 *    3-1. 걷기 : 1초 후 X+1 또는 X-1
 *    3-2. 순간 이동 : 0초 후 2*X
 *  
 *  풀이 계획
 *  - BFS
 *  
 * 	풀이 방법
 *  1. 초기 수빈 위치를 큐에 담아 BFS
 *  2. 순간 이동은 0초 후부터 가능하므로 순간이동부터 고려
 *  3. 이후 X+1, X-1 고려
 *  4. 큐에서 꺼낸 값이 동생 위치와 같다면 빠져나와서 그 때의 시간 출력
 *  
 */

public class Main_G5_13549_숨바꼭질3{ // 16552kb 104ms

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[100001];
		Queue<int[]> q = new LinkedList<>();
		visited[N] = true;
		q.add(new int[] {N, 0});
		
		int n = 0, t = 0;
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			n = arr[0];
			t = arr[1];
			if(n == K) break;
			else {
				// 순간이동 하는 경우(0초 후)
				if(n*2 > 0 && n*2 <= 100000 && !visited[n*2]) {
					q.add(new int[] {n*2, t});
					visited[n*2] = true;
				}
				// X-1 이동하는 경우(1초 후)
				if(n-1 >= 0 && !visited[n-1]) {
					q.add(new int[] {n-1, t+1});
					visited[n-1] = true;
				}
				// X+1 이동하는 경우(1초 후)
				if(n+1 <= 100000 && !visited[n+1]) {
					q.add(new int[] {n+1, t+1});
					visited[n+1] = true;
				}
			}
		}
		System.out.println(t);
	}
}