package algostudy.day1020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.19 03:30
 * 	풀이 종료 시간: 22.10.19 04:13
 *  풀이 시간 : 40분
 *  
 * 	목표: 연속되는 수들의 부분합 중 그 합이 S 이상이 되는 것 중 가장 잛은 것의 길이 구하기
 * 
 *	조건
 * 	1. N 길이의 수열이 주어짐
 *  2. N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)
 *  3. 수열의 각 원소는 공백으로 구분되고, 10000 이하의 자연수
 *  4. S 이상이 되는 합을 만드는 것이 불가능하면 0 출력
 *  
 *  풀이 계획
 *  - 누적합
 *  - 투포인터
 *  
 * 	풀이 방법
 *  1. 입력받을 때 누적합 만들기
 *  2. 누적합을 투포인터로 
 *  
 */

public class Main_G4_1806_부분합{ // 22520kb 216ms

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] num = new int[N+1];
		st = new StringTokenizer(br.readLine());
		
		// 누적합 만들기
		for(int i=1; i<=N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			num[i] = num[i-1] + tmp;
		}
		
		// start, end 투포인터 사용
		int start = 1;
		int end = 1;
		int min = Integer.MAX_VALUE;
		if(num[N-1]>=S) min = N;
		while(end <= N) {
			// ex) 5 1 3 5 10 수열이 주어질 때
			// 부분합 배열은 5 6 9 14 24
			// 5를 1번째 원소라고 가정하면
			// 2번째 ~4번째 수의 부분합은 num[4]-num[1] = 14-5 = 9
			if(num[end]-num[start-1]>=S) {
				min = Math.min(min, end-start+1);
				start++;
			}
			else end++;
		}
		
		System.out.println((min == Integer.MAX_VALUE) ? 0 : min);		
	}
}