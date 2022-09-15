package algostudy.day0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 풀이 시작 시간: 22.09.07 00:20
 * 풀이 종료 시간: 22.09.07 00:55
 * 
 * 목표: 4가지 이용권을 적절히 구매하여 가장 적은 비용 찾기
 * 
 * 이용권 종류
 * 1. 1일 이용권 : 1일 이용 가능
 * 2. 1달 이용권 : 1달 이용 가능. 매달 1일부터 시작
 * 3. 3달 이용권 : 연속된 3달 이용 가능. 매달 1일부터 시작.
 * 				다음 해와 연속해서 사용 불가 ex) 11, 12, 1 / 12, 1, 2 -> X
 * 4. 1년 이용권 : 1년 이용 가능. 매년 1월 1일 시작 -> 구매 시 다른 이용권 필요 없음
 * 
 * 풀이 계획
 * 모든 경우의 수 탐색 -> DFS 이용
 * DFS 알고리즘 시간 단축 -> 가지치기(최소 비용을 넘겼을 때)
 * 처음에 1년 이용권 비용을 최소 비용으로 설정하고
 * 1일 이용권부터 순서대로 DFS로 최소 비용 갱신하기
 */

public class Solution_1952_수영장 { // 20,844kb 114ms
	static int[] ticket; // 이용권 가격들
	static int[] plan; // 12개월 이용계획
	static int mincost; // 최소 비용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 총 테스트 케이스 수
		for(int t=1; t<=T; t++) {
			ticket = new int[4];
			plan = new int[12];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			// 최소 비용을 1년 이용권으로 설정한 후 dfs
			mincost = ticket[3];
			dfs(0, 0);
			sb.append("#").append(t).append(" ").append(mincost).append("\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int cost, int month) {
		// 누적 비용이 최소 비용보다 커지면 return
		if(cost > mincost) return;
		// 12월까지 비용 계산한 경우 최소 비용 갱신
		if(month == 12) {
			mincost = Math.min(cost, mincost);
			return;
		}
		
		// 1일 이용권 비용
		dfs(cost+ticket[0]*plan[month], month+1);
		
		// 1달 이용권 비용
		dfs(cost+ticket[1], month+1);
		
		// 3달 이용권 비용
		if(month<10) {
			dfs(cost+ticket[2], month+3);
		}
	}

}
