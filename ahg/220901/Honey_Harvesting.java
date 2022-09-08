package com.ssafy.recur.swea.sol2115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Honey_Harvesting {
	// 최대한 많은 수익
	// 2중 for문을 돌려서 겹치지 않게 각 일꾼들의 위치를 구하고
	// 구한 위치에서 꿀통 수치로 조합을 돌려 가장 높은 수익을 계산해서 출력
	static int N, M, C; // N -> 벌통 크기 , M -> 선택할 수 있는 벌통의 개수 , C -> 꿀을 채취할 수 있는 양
	static int[][] map; // 벌통의 초기 데이터를 저장할 2차원 배열
	static int max_value; // 테스트케이스마다 결과값을 저장할 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) { // 초기 데이터 입력받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max_value = Integer.MIN_VALUE;
			int[] number = new int[M]; // 첫번째 양봉업자가 선택한 벌꿀통의 꿀 양을저장할 배열
			int[] number2 = new int[M]; // 두번째 양봉업자가 선택한 벌꿀통의 꿀 양을저장할 배열
			// 1번 양봉업자가 채취할 목록, 2번 양봉업자가 추출할 목록 뽑아서 그걸로 조합
			// 그러면 최대 시간복잡도는 O((N^2)^2-1)*(2^(2M)) = N의 최대개수 10개,M의 최대개수 5개 즉, 100*20*1024
			// -> 2,048,000 딱히 터질거 같지 않음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - M + 1; j++) { // Y축은 가로로 M만큼의 범위를 체크해야하기 떄문에 N-M+1까지 탐색해줌 양봉업자는 최소 현재칸 한칸은 꿀을 채취
														// 해야하기때문에(N-M에 +1을 해준다)
					boolean[][] visited = new boolean[N][N]; // 두번째 양봉업자가 첫번째 양봉업자와 꿀을 채취하는 구간이 같은지 다른지 체크해줄 배열
					for (int k = 0; k < M; k++) { // 첫번쨰 양봉업자가 채취할 꿀의 범위를 체크,배열에 저장
						number[k] = map[i][j + k];
						visited[i][j + k] = true;
					}
					for (int a = 0; a < N; a++) { // 두번째 양봉업자 꿀 채취하는 2중for문
						outer: for (int b = 0; b < N - M + 1; b++) { // 마찬가지로 N-M+1
							for (int c = 0; c < M; c++) {
								if (visited[a][b + c])
									continue outer; // 배열을 탐색 하다가 첫번째 양봉업자가 이미 방문한 곳이면 다음 인덱스 탐색
								number2[c] = map[a][b + c];
							}
							comb(0, 0, number, 0, number2, 0); // 둘의 범위가 겹치지 않으면 조합 돌림
						}
					}
				}
			}
			System.out.println("#" + tc + " " + max_value);
		}
	}

	// total -> 각 양봉업자의 꿀 채취 양(?)을 저장하는 변수 , result -> 수익을 저장할 변수, start -> 조합 시작지점을
	// 체크할 변수 , idx -> 두 양봉업자의 꿀채취 상태를 체크하기 위한 변수
	private static void comb(int total, int result, int[] number, int start, int[] number2, int idx) {
		if (idx >= 2) { // 두 양봉업자가 채취를 끝마쳤으면
			max_value = Math.max(result, max_value); // 현재 결과값과 현재까지의 최대 결과값 비교후 갱신
			return;
		}
		for (int i = start; i < M; i++) {
			if (total + number[i] > C)
				continue; // 현재까지 채취한 꿀 + 현재 채취할 꿀이 C이상이면 continue;
			comb(total + number[i], result + (number[i] * number[i]), number, i + 1, number2, idx); // depth를 증가 시킬때마다
																									// total값과 result값을
																									// 갱신
		}
		comb(0, result, number2, 0, number2, idx + 1);// 채취할 꿀이 더이상 없으면(total값 초과 or 전부채취) 두번쨰 양봉업자로 넘어감
	}
}
