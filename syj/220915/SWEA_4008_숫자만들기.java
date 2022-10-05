/*
 	N : 숫자 개수
	4개 연산자 : { 0 : +, 1 : -, 2 : *, 3 : / }
	
	숫자 배열에 입력받기
	연산자 배열 생성 [4]
	연산자 개수 배열에 입력받기
	연산자 순열 돌리기 ---> 연산자 중복 순열로 돌리면 중복 제거 가능 (인덱스 넣어서 나중에 해당 연산자 switch-case문으로 계산해주기)
	*그냥 순열로 돌리면  11! 만 해도 4천만번 .. 여기에 +알파 에바쎄바 *
	기저조건에서 수식 계산 --> (0 : +, 1 : -, 2 : *, 3 : /)최대값, 최소값 갱신
	최대값-최소값 출력
 */

package study.day0915;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4008_숫자만들기 {	// 메모리 : 20800 kb	실행시간 : 130 ms
	static int N, maxAns, minAns;
	static int[] arr, c, op;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			maxAns = Integer.MIN_VALUE;
			minAns = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			c = new int[4];
			// 연산자 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				c[i] = Integer.parseInt(st.nextToken());
			}
			// 숫자 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 중복순열 담을 배열
			op = new int[N-1];
			// 중복순열 구하기
			permCal(0);
			// 결과값 계산해서 저장
			sb.append("#" + t + " " + (maxAns - minAns) + "\n");
		}
		System.out.println(sb);
	}
	private static void permCal(int r) {
		if(r == N-1) {
			// 기저조건에서 해당 연산자에 따라 계산하기
			int idx = 0;
			int num = arr[idx++];
			for (int i = 0; i < op.length; i++) {
				switch (op[i]) {
				case 0:
					num += arr[idx++];
					break;
				case 1:
					num -= arr[idx++];				
					break;
				case 2:
					num *= arr[idx++];
					break;
				case 3:
					num /= arr[idx++];
					break;
				}
			}
			maxAns = Math.max(maxAns, num);
			minAns = Math.min(minAns, num);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(c[i] == 0) continue;	// 해당 연산자를 다 사용했을 경우 무시
			op[r] = i;	// 인덱스 넣어주기
			c[i]--;	// 사용했으니 -1
			permCal(r + 1);	
			c[i]++;	// 돌아왔으니 +1
		}
	}
}
