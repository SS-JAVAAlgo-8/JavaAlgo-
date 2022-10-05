package com.ssafy.recur.swea.sol4008;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] operator = new int[4]; // 연산자를 저장할 변수
	static int[] number; // 숫자 목록을 저장할 변수
	static int max_value; // 최대 값을 저장할 변수
	static int min_value; // 최소 값을 저장할 변수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		// 초기값 입력받기
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			number=new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			max_value=Integer.MIN_VALUE;
			min_value=Integer.MAX_VALUE;
			// dfs 탐색 -> 첫 total값을 0으로 설정하면 곱하기,나누기때 문제가 발생할 수 있으므로 처음 검사할 값을 첫번째 숫자로 설정해주고 idx를 1부터 시작
			dfs(1, number[0], operator.clone());
			// 결과값 출력
			System.out.println("#" + tc + " " + (max_value-min_value));
		}
	}

	private static void dfs(int idx, int total, int[] operator2) {
		// 숫자 길이만큼 재귀를 돌면 결과값 갱신
		if (idx == N ) {
			max_value= Integer.max(total, max_value);
			min_value = Integer.min(total, min_value);
			return;
		}
		// 더하기
		if (operator2[0] != 0) {
			operator2[0]--;
			dfs(idx+1,total + number[idx],operator2.clone());
			operator2[0]++;
		}
		// 빼기
		if (operator2[1] != 0) {
			operator2[1]--;
			dfs(idx+1,total - number[idx],operator2.clone());
			operator2[1]++;
		}
		// 곱하기
		if (operator2[2] != 0) {
			operator2[2]--;
			dfs(idx+1,total * number[idx],operator2.clone());
			operator2[2]++;
		}
		// 나누기
		if (operator2[3] != 0) {
			operator2[3]--;
			dfs(idx+1,total / number[idx],operator2.clone());
			operator2[3]++;
		}

	}
}
