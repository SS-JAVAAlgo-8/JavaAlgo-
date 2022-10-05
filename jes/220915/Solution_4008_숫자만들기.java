package algostudy.day0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.09.21 23:10
 * 	풀이 종료 시간: 22.09.22 00:30
 *  풀이 시간 : 설계 10분 + 코딩 30분 + 시간초과 수정 15분 = 약 1시간
 * 
 * 	목표: 수식 계산했을 때 결과가 최대인 수식의 값 - 최소인 수식의 값 구하기
 * 
 *	조건
 * 	1. N개의 숫자 적혀있는 게임판에서 +, -, x, / 연산자 카드 사용
 *  2. 연산자의 우선순위 고려 X 왼쪽부터 오른쪽으로 차례대로 계산
 * 
 * 	풀이 계획
 * 	1. 순열 -> 시간초과 ^__^
 *  2. Next Permutation -> 시간 초과 해결
 *  
 *  풀이 방법
 *  1. 각 연산자 +, -, *, / 를 0, 1, 2, 3으로 표현하여 배열 생성
 *  2. 연산자 배열을 Next Permutation 통해 순열 구하기
 *  3. 구한 연산자 순열을 통해 수식 계산 후 최대, 최소 구하기
 *  4. 최대인 수식의 값 - 최소인 수식의 값 출력
 */

public class Solution_4008_숫자만들기 { // 30896kb 162ms
	static int N, max, min;
	static int[] input, num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			int[] oper = new int[4];
			num = new int[N];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			// 각 연산자의 개수
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			
			// 연산자 +, -, *, / -> 0, 1, 2, 3으로 표현하여 배열 생성
			input = new int[N-1];
			int idx = 0;
			while(idx<N-1) {
				for(int i=0; i<4; i++) {
					while(oper[i]>0) {
						input[idx++] = i;
						oper[i]--;
					}
				}				
			}
						
			// 수식에 사용되는 숫자
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			// 최대 최소 수식의 값 구하기(순열)
			Arrays.sort(input);
			do {
				int res = num[0];
				for(int i=0; i<N-1; i++) {
					res = calc(res, input[i], num[i+1]);
				}
				max = Math.max(max, res);
				min = Math.min(min, res);
			}while(np(input));
			
			sb.append("#").append(t).append(" ").append(max-min).append("\n");
		}
		System.out.println(sb);
	}
	
	
	public static boolean np(int[] numbers) {
		int N = numbers.length;
		
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;

		// 이미 정렬이 끝난 경우 더이상 바꿀 게 없으면 반환
		if(i==0) return false;
		
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) --j;
		
		swap(numbers, i-1, j);
		
		int k = N-1;
		while(i<k) swap(numbers, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
	
	// 식 계산
	private static int calc(int res, int oper, int num) {
		switch(oper) {
		case 0: // +
			res+=num;
			break;
		case 1: // -
			res-=num;
			break;
		case 2: // *
			res*=num;
			break;
		case 3: // /
			res/=num;
			break;
		}
		return res;
	}
}
