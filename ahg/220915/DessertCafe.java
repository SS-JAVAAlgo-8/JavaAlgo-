package com.ssafy.recur.swea.sol2105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 한 변의 길이가 N인 정사각형 모양
	// 원 안 숫자는 해당 디저트 카페에서 팔고 있는 디저트의 종류
	// 대각선 방향으로 움직이기 가능 -> 사각형 돌고 다시 돌아와야함
	// 범위 벗어나기 x, 같은거 먹기 x, 같은 숫자 x
	// 대각선 우측아래,좌측아래에 데이터가 있을 경우에만 포함됨 (위부터 순차적으로 검색했을
	// 범위 밖으로 나가기 전까지 dfs돌리면 될거같은데..
	// 최대 20*20의 판 종류는 1~100; 400
	static int N; // 맵의 길이를 받을 변수
	static int[][] map; //맵의 정보를 저장할 변수
	static int max_value; // 최종 결과값 담을 변수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		// 초기값 입력받기
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max_value= Integer.MIN_VALUE;;
			// 세로는 첫줄부터 마지막줄 -1 까지, 가로는 1 부터 N-1 까지 탐색하면서 검사
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					dfs(i+1, j+1,new boolean[101],0,1,i,j);
				}
			}
			System.out.println("#" + tc  +  " " + (max_value == Integer.MIN_VALUE ? -1 : max_value ));
		}
	}

	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1 ,- 1, 1 };
	// checkNum : 방문한 집을 체크할 배열
	// idx : 방향을 꺾는 횟수를 셀 변수
	// cnt : 방문한 카페를 카운팅할 변수
	// startx,starty : 시작 좌표를 저장할 변수
	// x,y : 현재 좌표를 저장할 변수
	private static void dfs(int x, int y,boolean[] checkNum,int idx,int cnt,int startx,int starty) {
		if(idx>3 || checkNum[map[x][y]]) return; //4번이상 꺾었거나 (사각형이 되었거나),이미 방문한곳일경우 리턴
		if (x==startx && y==starty) { // 시작지점과,현재 위치가 같을 경우
			max_value = Math.max(max_value, cnt); // 최대값 갱신
			return;
		}
		checkNum[map[x][y]]=true;
		int nx = x+dx[idx];
		int ny = y+dy[idx];
		if (idx<3 && !Isin(x+dx[idx+1],y+dy[idx+1])) dfs(x+dx[idx+1],y+dy[idx+1],checkNum.clone(),idx+1,cnt+1,startx,starty); // 꺾은 칸이 범위 안이 아닐경우 꺾은곳으로 dfs탐색
		if (!Isin(nx,ny)) dfs(nx,ny,checkNum.clone(),idx,cnt+1,startx,starty); // 다음칸이 범위 안일경우 계속 탐색 
	}

	private static boolean Isin(int x, int y) {
		if (x >=N || x<0 || y>=N || y<0) return true;
		return false;
	}
}
