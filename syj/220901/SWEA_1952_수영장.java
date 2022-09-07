/*
 * 완전탐색 + 중복순열
 * 
 * 필요한 배열 : 
 * int[] arr = new int[12]	--> 12개월 이용 계획
 * boolean[] check = new boolean[12]	--> 이용하는 달인지 확인
 * int[] num = new int[0이 아닐 달 개수]	--> 이용권 순열 저장
 * 
 * 1. 가격 입력받기
 * 2. 12개월 이용 계획 입력받기 & 동시에 이용하는 달 체크하기
 * 3. 이용권 중복순열 구하기 (1일 : 0, 1달 : 1, 3달 : 2)
 * 4. 순열 구해지면 가격 계산하며 최소값 찾기
 * 5. 마지막에 구한 최소값과 1년 이용권 가격 비교해서 최종값 저장
 */

package study.day0901;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장 {	// 메모리 : 20552 kb	실행시간 : 541 ms
	static int[] price, arr, num;
	static boolean[] check;
	static int use, ans;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 결과값 초기화
			ans = Integer.MAX_VALUE;
			
			// 가격 입력받기
			price = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			// 12개월 이용 계획 입력받기 & 동시에 이용하는 달 체크하기
			arr = new int[12];	// 12개월 이용 계획
			check = new boolean[12];	// 이용하는 달 체크
			use = 0;	// 이용하는 달 개수 세기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] != 0) {
					use++;
					check[i] = true;
				}
			}
			// 이용권 저장할 배열 선언
			num = new int[use];
			
			// 이용권 중복순열 구하러 가기
			perm(0);
			
			// 출력값 저장
			sb.append("#" + t + " " + (ans < price[3] ? ans : price[3]) + "\n");
		}
		// 결과 출력
		System.out.println(sb);
	}
	private static void perm(int r) {
		if(r == use) {
			int sum = 0;
			for (int i = 0, idx = 0; i < 12; i++) {
				// 이용하지 않는 달은 건너뛰기
				if(!check[i]) continue;
				int type = num[idx];
				// 이용권에 따라 가격 계산
				switch (type) {
				case 0:
					sum += arr[i] * price[0];
					break;
				case 1:
					sum += price[1];					
					break;
				case 2:
					sum += price[2];
					// 3달 이용권인 경우 뒤에 3달은 무시
					int index = i;
					for (int j = index + 1; j < index + 3; j++) {
						if(j < 12 && check[j]) {
							i++;
							idx++;
						}
					}
					break;
				}
				idx++;
			}
			// 이용권 최소가격 저장
			ans = Math.min(ans, sum);
			return;
		}
		
		// 이용권 저장
		for (int i = 0; i < 3; i++) {
			num[r] = i;
			perm(r + 1);
		}
	}
}