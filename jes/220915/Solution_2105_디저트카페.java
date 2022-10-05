package algostudy.day0915;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.09.20 23:00
 * 	풀이 종료 시간: 22.09.21 00:41
 *  풀이 시간 : 설계 10분 + 코딩 30분 + 디버깅 30분 => 약 70분
 * 
 * 	목표: 디저트를 가장 많이 먹을 때 디저트 개수 구하기
 * 
 *	조건
 * 	1. 대각선 방향으로 이동 -> 사각형 만들어야 함
 *  2. 같은 숫자(같은 종류의 디저트)는 만날 수 없음
 *  3. 2종류 이상의 디저트를 먹어야 함
 *  4. 왔던 곳은 되돌아 갈 수 없음
 * 
 * 	풀이 계획
 * 	1. 사각형을 만들기 위해 양옆 각 1줄, 아래 2줄을 제외한 부분에서 탐색
 *  2. dfs 통해 사각형이 만들어 질 수 있는지 탐색
 *     이 때, 종료 조건은 4방향을 모두 돌고나서 도착한 좌표가 시작점인 경우
 *  3. 최대값(디저트 가게 방문 횟수) 갱신이 된 경우 최대값, 안된 경우 -1 출력
 *  
 */

public class Solution_2105_디저트카페 { // 23872kb 178ms
	static Point end;
	static int N, max;
	static int[][] map;
	static boolean[] isChecked; // 종류가 같은지 판단
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			max = -1;
			N = Integer.parseInt(br.readLine());
			
			// 디저트 카페 정보 받아오기
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 디저트 카페 탐방
			// map 아래 2행, 양옆 각각 1열은 사각형을 만들 수 없으므로 범위에서 제외하고 시작
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					// 방문처리할 배열
					isChecked = new boolean[100];
					end = new Point(i, j);
					dfs(0, 0, i, j);
				}
			}
			
			// 사각형이 만들어지지 않은 경우 -1로 처리
			if(max<4) max = -1;
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	
	// 대각선 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위 순서
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
	
	// cnt: 방향 설정 위한 변수, tmp: 먹은 디저트 개수(방문한 디저트 가게 수)
	private static void dfs(int cnt, int tmp, int x, int y) {
		// 방향이 오른쪽 위일 때 시작점(끝점)으로 돌아온 경우 최대값 갱신
		if(cnt==3 && x==end.x && y==end.y) {
			max = Math.max(max, tmp);
			return;
		}
		
		for(int i=cnt; i<4; i++){
			int dx = x+dr[i];
			int dy = y+dc[i];
			
			// 같은 종류 디저트거나 범위를 벗어났을 때 방향 전환
			if(dx<0 || dy<0 || dx>=N || dy>=N || isChecked[map[dx][dy]-1]) {
				continue;
			}

			isChecked[map[dx][dy]-1]=true;
			dfs(i, tmp+1, dx, dy);
			isChecked[map[dx][dy]-1]=false;
		}
	}
}
