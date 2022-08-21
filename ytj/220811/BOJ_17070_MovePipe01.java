package study.day220811.problem;

import java.util.Scanner;

public class BOJ_17070_MovePipe01 {

	static int map[][];
	static int pstate[] = {1, 2, 3}; // 가로 (우, 대각), 대각선(우, 대각 , 아래), 세로(대각, 아래)
	static int dx[] = {0, 1, 1};     // 우 , 대각, 아래
	static int dy[] = {1, 1, 0};
	static int pushCnt;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		movepipe(0, 1, N, 0);
		System.out.println(pushCnt);
	}

	private static void movepipe(int x, int y, int N, int state) {
		
		if( x == N-1 && y == N-1) {
			pushCnt++;
			return;
		}
		
		if(x + 1 < N && y + 1 < N && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0 ) // 대각선일때
			movepipe(x + 1, y + 1, N, 1);
		
		if(state == 0) { // 가로 일때, 오른쪽 이동
			
			int nx = x + dx[0];
			int ny = y + dy[0];
			if(ny < N && map[nx][ny] == 0)
				movepipe(nx, ny, N, 0);
		}
		
		else if (state == 1) { // 대각선 일때, 오른쪽 이동, 아래 이동
			int nx = x + dx[0];
			int ny = y + dy[0];
			if(ny < N && map[nx][ny] == 0)
				movepipe(nx, ny, N, 0);
			
			nx = x + dx[2];
			ny = y + dy[2];
			if(nx < N && map[nx][ny] == 0)
				movepipe(nx, ny, N, 2);
		}
		
		else if (state == 2) { // 세로 일때, 1~2
			int nx = x + dx[2];
			int ny = y + dy[2];
			if(nx < N && map[nx][ny] == 0)
				movepipe(nx, ny, N, 2);
		}
		
	}

}
