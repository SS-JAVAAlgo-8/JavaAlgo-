import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int cnt = 0;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check(0, 1, 0);
		System.out.println(cnt);
	}

	// 파이프는 3가지의 상태
	// 가로 ,세로,대각선
	static void check(int x, int y, int status) {
		if (x == N - 1 && y == N - 1) { // 파이프가 정상적으로 도착점에 도달했을 경우 카운트 증가
			cnt++;
			return;
		}
		if (status == 0) { // 가로일때는 가로와 대각선이 가능한지 체크
			x_check(x,y);
			xy_check(x,y);
		}
		if (status == 1) { // 세로 일 때는 세로와 대각선이 가능한지 체크
			y_check(x,y);
			xy_check(x,y);
		}
		if (status == 2) { // 대각선 일 때는 세방향이 가능한지 체크
			x_check(x, y);
			y_check(x, y);
			xy_check(x, y);
		}
	}

	static void x_check(int x, int y) {
		if (y + 1 < N && map[x][y + 1] == 0) { // 가능하다면 가로로 설치했다고 가정하고 다시 check함수 호출
			check(x, y + 1, 0);
		}
	}

	static void y_check(int x, int y) {
		if (x + 1 < N && map[x + 1][y] == 0) { // 가능하다면 세로로 설치했다고 가정하고 다시 check함수 호출
			check(x + 1, y, 1);
		}

	}

	static void xy_check(int x, int y) { // 가능하다면 대각선으로 설치했다고 가정하고 다시 check함수 호출
		if (y + 1 < N && x + 1 < N && map[x + 1][y + 1] == 0 && map[x +1][y] == 0 && map[x][y+1]==0) {
			check(x + 1, y + 1, 2);
		}

	}
}
