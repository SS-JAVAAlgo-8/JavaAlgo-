/*
DP
- K가 N보다 작은 경우는 그 값의 차이만큼 출력

- K가 N보다 큰 경우
* 우선 순위 : 2배로 건너뛰기 > 1칸 앞 = 1칸 뒤 *
1. 0부터 N까지는 N까지의 거리만큼 채워놓기
2. N + 1 부터 DP
- 짝수일 경우 : (-1칸의 값 + 1)과 (i / 2 칸)의 값을 비교해서 더 작은 값을 저장
- 홀수의 경우 : (-1칸의 값 + 1)과 ((i + 1) / 2 칸의 값 + 1)과 ((i - 1) / 2 칸의 값 + 1)의 값을 비교해서 더 작은 값을 저장
3. K번째에 저장된 값 출력
*/
package study.day1013;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질3 {	// 메모리 : 11,968 kb		실행시간 : 84 ms
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(K < N) {
			System.out.println(N - K);
			return;
		}
		
		int[] dp = new int[K + 1];
		
		for (int i = N - 1; i >= 0; i--) {
			dp[i] = dp[i + 1] + 1;
		}
		
		for (int i = N + 1; i <= K; i++) {
			dp[i] = dp[i - 1] + 1;
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2]);
			}
			if(i % 2 != 0) {
				dp[i] = Math.min(Math.min(dp[i], dp[(i - 1) / 2] + 1), dp[(i + 1) / 2] + 1);
			}
		}
		System.out.println(dp[K]);
	}
}
