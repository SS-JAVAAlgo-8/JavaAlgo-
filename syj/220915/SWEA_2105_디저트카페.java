/*
 	3번 방향틀기
	원점으로 돌아오기
	대각선으로만 이동가능
	밑에 2줄, 오른쪽 1줄, 왼쪽 1줄 사각형 생성 불가능 ...
	
	dfs + 완탐
	(모서리 제외 모든 위치에서 dfs 돌리기)

	인덱스 유효범위 체크 & 디저트 수 중복 체크(자동으로 가게 방문체크도 됨)
	
	dx = {1, 1, -1, -1} 
	dy = {1, -1, -1, 1}
	4방향 탐색 - 시계방향 (우하 -> 좌하 -> 좌상 -> 우상)
	
	매개변수 : r, c, change(방향바꾼횟수,, 즉 델타배열 인덱스), count
	
	change == 3 && 원점 도착 =====> 최대값 갱신
	
	- 무조건 같은 방향으로 보내보기
	- change < 3   ===> 방향 바꿔서도 보내기

	
 */
package study.day0915;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페 {	// 메모리 : 31148 kb	실행시간 : 138 ms
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int N, maxAns, x, y;
	static int[][] map;
	static boolean[] dessert;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/2105.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			maxAns = -1;	// 최소값 초기화
			dessert = new boolean[101];		// 디저트 종류 체크 초기화
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {	// 맵 입력받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N-3; i++) {	// 모든 가게에 대해서 완탐 (사각형을 만들 수 있는 범위로 지정)
				for (int j = 1; j < N-1; j++) {
					x = i;	// 원점 설정
					y = j;	// 원점 설정
					dessert[map[i][j]] = true;
					dfs(i, j, 0, 1);	// dfs 돌리기
					dessert[map[i][j]] = false;
				}
			}
			sb.append("#" + t + " " + maxAns + "\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int r, int c, int change, int count) {
		// 가게 이동
		int nr = r + dx[change];
		int nc = c + dy[change];
		
		// 방향을 3번 틀어서 사각형이 이루어졌고, 원점에 도착했을 경우 최대값 갱신
		if(nr == x && nc == y && change == 3) {
			maxAns = Math.max(maxAns, count);
			return;
		}
		
		// 인덱스가 유효하고 이미 검사한 부분을 제외한 곳이면
		if(nr >= 0 && nr < N && nc >= 0 && nc < N && nr >= x) {
			// 디저트 중복되지 않을 경우
			if(!dessert[map[nr][nc]]) {
				// 디저트 체크해주고
				dessert[map[nr][nc]] = true;
				// 같은 방향으로 진행
				dfs(nr, nc, change, count + 1);
				// 만약 아직 사각형이 되지 않았으면 방향 바꿔서 보내보기
				if(change < 3) dfs(nr, nc, change + 1, count + 1);
				// 돌아왔으니 디저트 체크 해제
				dessert[map[nr][nc]] = false;
			}else return;
			
		}else return;
	}
}
