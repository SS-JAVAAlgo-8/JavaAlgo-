package algostudy.day0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.06 09:30
 * 	풀이 종료 시간: 22.10.06 10:26
 *  풀이 시간 : 설계 10분 + 코딩 20분 + 디버깅 10분 = 약 40분
 * 	목표: 활주로 설치 가능한 열, 행 개수 구하기
 * 
 *	조건
 * 	1. NxN 지형에 가로 또는 세로 방향으로 활주로 건설
 *  2. 각 셀의 숫자는 지형의 높이
 *  3. 활주로는 높이가 동일한 구간에서 건설 가능하나 높이가 다를 때 경사로 설치 가능
 *  4. 경사로의 높이는 항상 1이며 경사로 길이만큼 높이가 연속되는 곳에 설치 가능
 *  5. 경사로는 세로로 설치할 수 없음
 *  6. 경사로는 여러개 설치 가능하고, 겹치게 설치할 수 없음
 * 
 *  풀이 계획
 *  1. 한 행씩 보면서 오른쪽으로 한칸씩 이동하며 탐색
 *  2. 활주로 설치 불가능할 때 다음 행 탐색
 *  
 * 	풀이 방법
 * 	1. 원래 상태의 지도, 행과 열을 바꾼 지도를 생성함
 *  2. 이전 칸의 값과 비교하며 경사로 설치 여부 판단
 *  3. 경사로 설치해야되는 경우 올라가는 경사인지 내려가는 경사인지 판단
 *  4. 경사로 설치 가능한지 판단 -> 불가능할 경우 다음 행 탐색
 *  5. 경사로 설치할 필요 없는 경우 이전 칸이 현재 칸을 가리키고 다음 칸 탐색
 *  6. 반복하면서 활주로 설치 가능한 경우 ans++
 * 
 */

public class Solution_4014_활주로건설 { // 31608kb 118ms

	static int N, X, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한 변의 크기
			X = Integer.parseInt(st.nextToken()); // 경사로의 길이
			ans = 0;
			int[][] map = new int[N][N];
			int[][] map2 = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int value = Integer.parseInt(st.nextToken());
					map[i][j] = value;
					map2[j][i] = value;
				}
			}
			
			search(map);	// 가로 탐색
			search(map2);   // 세로 탐색
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void search(int[][] map) {
		outer: for(int i=0; i<N; i++) {
			boolean isPossible = true; // 활주로 건설 가능 여부
			int cnt = 1;
			int pre = map[i][0];
			for(int j=1; j<N; j++) {
				if(map[i][j] == pre) { // 이전과 같은 높이인 경우
					cnt++;
					pre = map[i][j]; // 현재 높이를 이전 높이에 넣어줌
					continue;
				}
				else if(map[i][j] == pre+1) { // 이전보다 한 칸 높은 경우(올라가는 경사)
					if(cnt<X) { // 경사로를 만들 수 없는 경우
						isPossible = false;
						continue outer; // 다음 행으로 넘어감
					}
					cnt = 1;
					pre = pre+1; // 경사로를 통해 한 칸 올라감 표현
				}
				else if(map[i][j] == pre-1) { // 이전보다 한 칸 낮은 경우(내려가는 경사)
					for(int k=j; k<j+X; k++) { // 경사로 길이만큼 다음 칸 높이 탐색
						if(k>=N || map[i][k] != pre-1) { // 경사로를 만들 수 없는 경우
							isPossible = false;
							continue outer;
						}
					}
					j += X-1; // 다음 값 탐색한만큼 이동
					cnt = 0;
					pre = pre-1; // 경사로를 통해 한 칸 내려감 표현	
				}
				else { // 경사로를 높을 수 없는 경우
					isPossible = false;
					continue outer; // 다음 행으로 넘어감
				}
			}
			if(isPossible) ans++;
		}
	}

}
