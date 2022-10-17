package algostudy.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.11 00:00
 * 	풀이 종료 시간: 22.10.11 01:10
 *  풀이 시간 : 설계 10분 + 코딩 20분 + 디버깅 20분 = 50분
 *  
 * 	목표: 성능 검사 통과하기 위한 약품 투입 횟수의 최소 구하기
 * 
 *	조건
 * 	1. D: 보호필름 두께, W: 보호필름 가로크기, K: 합격기준
 *  2. 약품 투입 없이도 성능 검사 통과하는 경우 0
 *  3. 셀이 가질 수 있는 특성은 A, B 2가지
 *  4. 단면의 모든 세로 방향에 대해 동일한 특성의 셀이 K개 이상 연속된 경우 성능검사 통과
 *  
 *  풀이 계획
 *  - 부분 집합으로 하려다가 0 추가해서 중복 순열해도 되지않나 싶어서 했다가 ^_^ 시간초과
 *  - 다시 부분 집합으로 돌리니까 테케 29개만 맞길래 뭐지 ? 했는데 Arrays.fill하는 부분에서 A, B를 0, 1로 줘야되는데 0, 0으로 줘서 그랬다.
 *  
 * 	풀이 방법
 *  1. 부분집합으로 약을 투입할 행 구하기
 *  2. 약 투입(dfs()) -> 약 투입할 행에 약품 A, 약품 B 각각 투입해보기
 *  3. 최소 약품 투입 횟수보다 많아지면 return
 *  4. N행까지 약품 투입을 마치면 성능 검사(check())
 *  5. 성능 검사가 통과하면 최소 약품 투입 횟수 갱신
 *  
 */

public class Solution_2112_보호필름 { // 107160kb 627ms
	static int N, W, K, ans;
	static int[] num;
	static boolean[] isSelected;
	static int[][] map, copymap;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][W];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isSelected = new boolean[N];
			ans = Integer.MAX_VALUE;
			num = new int[N];

			subset(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");			
		}
		System.out.println(sb);
	}
	
	private static void subset(int cnt) {
		if(cnt==N) {
			// 맵 복사
			copyMap();
			// 약품 처리
			dfs(0, 0);
			return;
		}
		
		// true: 약품 처리 , false: 약품 처리 X
		isSelected[cnt]=false;
		subset(cnt+1);
		isSelected[cnt]=true;
		subset(cnt+1);
	}
	
	// 
	private static void dfs(int cnt, int idx) {
		if(cnt >= ans) return;
		// 모든 행 다 돌고왔을 때 -> 종료조건
		if(idx == N) {
			if(check()) ans = Math.min(ans, cnt);
			return;
		}
		
		// 약품 처리할 행
		if(isSelected[idx]) {
			Arrays.fill(copymap[idx], 0);
			dfs(cnt+1, idx+1);
			
			Arrays.fill(copymap[idx], 1);
			dfs(cnt+1, idx+1);
		}
		else dfs(cnt, idx+1);
	}

	// 성능 검사
	private static boolean check() {
		outer: for(int i=0; i<W; i++) {
			int k=1;
			int prev = copymap[0][i]; // 0행 i열
			for(int j=1; j<N; j++) {
				if(copymap[j][i] == prev) { // 이전 값이랑 같은 경우
					k++;
				}
				else { // 이전 값이랑 다른 경우
					k=1;
					prev = copymap[j][i];
				}
				// 이미 해당 열 성능이 기준을 통과한 경우 -> 다음 열 검사
				if(k==K) continue outer;
			}
			// 해당 열 성능이 기준을 통과하지 못한 경우 -> false 반환
			if(k<K) return false;
		}
		// 모든 열이 성능 검사에서 통과한 경우
		return true;
	}

	// 맵 복사
	private static void copyMap() {
		copymap = new int[N][W];
		for(int i=0; i<N; i++) {
			copymap[i] = map[i].clone();
		}
	}
}
