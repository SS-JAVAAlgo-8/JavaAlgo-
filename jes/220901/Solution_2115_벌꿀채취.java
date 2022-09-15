package algostudy.day0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 풀이 시작 시간: 22.09.08 00:30
 * 풀이 종료 시간: 22.09.08 02:10
 * 
 * 목표: 두 일꾼이 C를 넘지 않게 벌꿀 채취하여 얻을 수 있는 최대 비용 구하기
 * 
 * 조건
 * 1. 일꾼 2명이 겹치지 않게 가로로 연속되는 M개 벌통 선택
 * 2. 각각 선택한 벌통에서 꿀 채취. 이때, 1개의 벌통은 1개의 용기에 담을 수 있고 일부만 담기 불가능
 * 3. 최대로 채취할 수 있는 양 C를 넘게 채취할 수 없음
 * 4. 각 용기에 있는 꿀의 양의 제곱을 합한 값 = 수익
 * 
 * 풀이 계획
 * 모든 경우의 수 탐색 -> DFS 이용
 * 1. 일꾼1이 먼저 벌통을 선택 -> 일꾼 1이 선택한 벌통 이후의 벌통에서 선택
 * 2. DFS로 일꾼 1이 벌통 선택할 때마다 일꾼 2가 최대의 수익을 내는 벌통 탐색
 * 3. 일꾼 1이 벌통 선택을 반복하며 최대 수익 갱신
 * -> (초기)만약 최대양 C를 넘긴 경우 가장 꿀의 양이 많은 것부터 용기에 담기 -> X
 * -> (보완)가장 많은 것부터 X -> 부분집합에서 C를 넘지 않고 제곱의 합이 가장 큰 값 
 */

public class Solution_2115_벌꿀채취 { // 23,600kb 131ms
	static boolean[] isSelected;
	static int[] worker1, worker2;
	static int[][] honey;
	static int N, M, C, tmp, maxcost, result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 총 테스트 케이스 수
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벌통틀의 크기
			M = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통 개수
			C = Integer.parseInt(st.nextToken()); // 꿀 채취할 수 있는 최대 양
			
			result = 0; // 결과값(최대 수익)
			isSelected = new boolean[M];
			honey = new int[N][N]; // 벌통틀
			worker1 = new int[M]; // 일꾼 1
			worker2 = new int[M]; // 일꾼 2
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 일꾼 1의 벌꿀 채취
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					maxcost = 0; // 일꾼2의 최대 비용
					// 가로줄에 더이상 M개의 연속된 벌통이 없으면 다음줄 탐색
					if(j+M-1 >= N) continue;
					int sum = 0;
					for(int k=j; k<j+M; k++) {
						worker1[k-j] = honey[i][k];
						sum += worker1[k-j];
					}
					dfs(i, j+M-1);
					result = Math.max(result, calc(sum, worker1)+maxcost);
				}
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	// 일꾼 2의 벌꿀 채취
	// r: 벌통틀 행, c: 벌통틀 열 -> 일꾼1이 이미 선택한 부분은 제외
	private static void dfs(int r, int c) {
		for(int i=r; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==r) j+=c+1;
				if(j+M-1>=N) continue;
				int sum = 0;
				for(int k=j; k<j+M; k++) {
					worker2[k-j] = honey[i][k];
					sum += worker2[k-j];
				}
				maxcost = Math.max(maxcost, calc(sum, worker2));
			}
		}
	}

	// 수익 계산
	private static int calc(int sum, int[] worker) {
		int psum = 0;
		if(sum <= C) {
			for(int i=0; i<M; i++) {
				psum += Math.pow(worker[i], 2);
			}
			return psum;
		}
		
		subset(0, worker);
		psum = tmp;
		tmp = 0;
		return psum;
	}
	
	// 
	private static void subset(int index, int[] input) {
		if(index == M) {
			int sum = 0;
			int psum =0;
			for(int i=0; i<M; i++) {
				if(isSelected[i]) {
					if(sum + input[i]> C) return;
					sum += input[i];
					psum += Math.pow(input[i], 2);
				}
			}
			tmp = Math.max(tmp, psum);
			return;
		}
		
		isSelected[index] = false;
		subset(index+1, input);
		isSelected[index] = true;
		subset(index+1, input);
	}
}
