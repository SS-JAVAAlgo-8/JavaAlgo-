/*
N^2도 안되는 문제 ...

N 또는 NlogN 정도로 줄여야 함
- 투 포인터를 쓰면 2N만에 끝낼 수 있음
*/
package study.day1020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 {		// 메모리 : 22,464 kb		실행시간 : 236 ms
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		int len = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
			
		}
		
		int left = 0;
		int right = 1;
		while(right <= N) {
			if(arr[right] - arr[left] >= S) {
				len = Math.min(len, right - left);
				left++;
			} else {
				right++;
			}
		}
		
		System.out.println(len == Integer.MAX_VALUE ? 0 : len);
	}
}
