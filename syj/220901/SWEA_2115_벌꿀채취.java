/*
 * 부분합 + 부분집합
 * 
 * 1. 부분합을 이용해 M만큼 더해서 미리 저장
 * 2. 부분합을 이용해 모든 그룹에 대해 수익 계산
 * 2-1. 해당 그룹이 C이하일 경우 반복문을 이용해 수익 계산
 * 2-2. 해당 그룹이 C초과일 경우 부분집합을 이용해 C를 넘지 않는 한에서 수익 계산 
 * 3. 최대 수익 2번 찾기
 * 
 * map : 부분합 담아놓은 배열
 * map2 : 원본값 담아놓은 배열 (수익 계산할 때 사용)
 * map3 : 최종 수익 부분합 담아놓은 배열(최대값 2번 찾아내서 합계 출력)
 * arr : 부분집합 돌릴 전체 값 담아놓은 배열
 * num : 선택한 원소 담을 배열
 */

package study.day0901;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {	// 메모리 : 18904 kb		실행시간 : 115 ms
	static int N, M, C;
	static int[][] map, map2, map3;
	static int[] arr, num;
	static int maxSum;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int ans = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// M만큼 부분합 저장
			map = new int[N + M][N + M];	// 부분합
			map2 = new int[N + M][N + M];	// 원본
			map3 = new int[N + M][N + M];	// 최종가격 저장
			
			for (int i = M; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = M; j < map[i].length; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n + map[i][j - 1] - map2[i][j - M];
					map2[i][j] = n;
				}
			}
			
			// 부분집합에 필요한 배열
			arr = new int[M];
			// 수익 부분합 저장 변수
			int sum = 0;
			// 최대값 찾기 위한 변수
			int maxPrice = Integer.MIN_VALUE;
			int max_x = 0;
			int max_y = 0;
			for (int i = M; i < map.length; i++) {
				for (int j = M + (M - 1); j < map[i].length; j++) {
					if(map[i][j] <= C) {
						sum = price(i, j);
					}else {
						// 부분집합 돌릴 그룹의 원소 arr에 담기
						for (int k = j, idx = 0; k > j - M; k--) {
							arr[idx++] = map2[i][k];
						}
						// 부분집합을 통해 C를 넘지 않는 한에서 최대 수익 찾기
						maxSum = Integer.MIN_VALUE;
						for (int n = 1; n < M; n++) {	
							num = new int[n];
							subset(0, 0);
						}
						sum = maxSum;
					}
					// 최대값 저장해두기
					if(sum > maxPrice) {
						maxPrice = sum;
						max_x = i;
						max_y = j;
					}
					// 수익 부분합 배열에 저장
					map3[i][j] = sum;
				}
			}
			// 최대값 결과값에 더하기
			ans += maxPrice;
			
			
			
			// 같은 행에서는 채집 불가 ...? 이거 이상이상이상이상이상 !!!!!!!!!!!!!!!!
			for (int j = 0; j < map3[0].length; j++) {
				map3[max_x][j] = 0;
			}
			/*이게 맞는거임 .................. ㅡㅡ 개빡취네
			 	해당 벌통 포함하는 수익 부분합 0으로 변경
			for (int j = max_y; j < max_y + M; j++) {
				if(j >= 0 && j < map3[0].length) map3[max_x][j] = 0;
			}
			*/
			
			
			// 다음 최대값 찾아서 결과값에 더하기
			maxPrice = Integer.MIN_VALUE;
			for (int i = M; i < map3.length; i++) {
				for (int j = M + (M - 1); j < map3[i].length; j++) {
					maxPrice = Math.max(maxPrice, map3[i][j]);
				}
			}
			
			sb.append("#" + t + " " + (ans + maxPrice) + "\n");
		}
		System.out.println(sb);
	}
	private static void subset(int r, int start) {	// 부분집합 계산하는 함수
		if(r == num.length) {
			int sum = 0;
			for (int i = 0; i < num.length; i++) {	// C를 초과했는지 판별
				sum += num[i];
			}
			if(sum > C) return;
			int price = 0;
			for (int i = 0; i < num.length; i++) {	// 수익 계산해서 최대값 갱신
				price += Math.pow(num[i], 2);
			}
			maxSum = Math.max(maxSum, price);
			return;
		}
		if(start == arr.length) return;
		num[r] = arr[start];
		subset(r + 1, start + 1);
		subset(r, start + 1);
		
	}
	private static int price(int i, int j) {	// 수익계산하는 함수
		int sum = 0;
		for (int k = j; k > j - M; k--) {
			sum += Math.pow(map2[i][k], 2);
		}
		return sum;
	}
}
