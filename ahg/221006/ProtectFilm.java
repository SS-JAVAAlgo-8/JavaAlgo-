package com.ssafy.recur.swea.sol2112;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 걸린시간 : 1시간
public class Solution {
	static int D, W, K;
	static int[][] film;
	static int tc;
	static int min_value;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
        // 초기값 입력받기
		for (tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min_value = Integer.MAX_VALUE;
            // 부분 조합 메서드
			subset(0,0,film);
			sb.append("#" + tc + " " + min_value).append("\n");
		}
		System.out.println(sb);
	}

	private static void subset(int idx,int injection,int[][] film) {
        // 행만큼 검사를 하거나 이미 약물투입이 현재 최소값보다 적을 경우 검사 후 탈출
		if (idx == D || injection>=min_value) {
			if(check2(film)) {
				min_value=Math.min(injection, min_value);
			}
			return;
		}
        // film2에 film을 복사
		int[][] film2 = new int[D][W];
		for (int i =0; i <D; i ++) {
			film2[i]=film[i].clone();
		}
        // 아무것도 변하지 않은 상태, 해당 줄을 A로 바꾼상태 , 해당 줄을 B로 바꾼상태로 분기를 나눠서 재귀
		subset(idx + 1,injection,film2);
		for (int i = 0; i < W; i++) {
			film2[idx][i] = 1;
		}
		subset(idx + 1,injection +1,film2);
		for (int i = 0; i < W; i++) {
			film2[idx][i] = 0;
		}
		subset(idx + 1,injection +1,film2);

	}

	private static boolean check2(int[][] film2) {
        // 2중 for문으로 열을 검사 
		outer: for (int i = 0; i < W; i++) {
			int check = 0; // 얼마나 연속됐는지 체크하기 위한 변수
            // 처음 값을 temp값에 저장 
			int temp = film2[0][i]; // 이전값을 저장할 변수
			boolean kcheck = false; // 해당 열이 테스트에 통과 하는지 체크할 변수
			for (int j = 0; j < D; j++) {
                // 이전값과 현재값이 다르면
				if (film2[j][i] != temp) {
                    // 이전 값 갱신 후
					temp = film2[j][i];
                    // 연속된 값 체크 변수를 1로 설정
					check = 1;
				} else {
					check++;
				}
                // 연속된 값이 테스트 통과 기준 값보다 높을경우 통과 체크를 해주고 다음 열 검사
				if (check >= K) {
					kcheck = true; 
					continue outer;
				}
			}
			if (!kcheck) {
				return false;
			}
		}
		return true;
	}

}
