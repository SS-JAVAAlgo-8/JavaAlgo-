package swtest;

/*
	걸린시간 : 1시간
	
dfs

1. 방문을 처리할 visited배열 , 4가지 대각선 방향배열 , 디저트의 종류를 확인할 배열을 선언
2. 각 방향의 진행정도에서 가지치기 진행
	- 기저조건
	1. 만약 4번을 꺾었다 -> 사각형이 될수 없음
	2. 만약 이미 방문했던 곳이라면? 
		2-1. 처음 시작한곳으로 돌아왔다 -> 3번만 꺾었는지 체크 -> 사각형 조건 완성 -> 디저트 종류개수 결과  저장
		2-1. 처음 시작한 곳이 아니다 -> return
	3. 이미 디저트가 중복되었다
	
3. 각 방문배열과 디저트 배열을 들고다니면서 체크 
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;

public class SWEA_2105_디저트카페 {
	static int N;
	static int[][] visited;
	static int[] result;
	static int[][] board;
	static int[] dx = { -1, 1, 1, -1 };
	static int[] dy = { 1, 1, -1, -1 };
	static int max;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			max = -1;
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < input.length; j++) {
					board[i][j] = Integer.parseInt(input[j]);
				}
			}
			visited = new int[N][N];
			result = new int[101];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					check(i, j, i, j, 0, 0, result, visited);
				}
			}
			System.out.println("#" + testcase + " " + max);

		}
	}

	private static void check(int startx, int starty, int x, int y, int direction, int count, int[] result,
			int[][] visited) {

		if (count == 4)
			return;

		if (visited[x][y] == 1) {
			if (startx == x && starty == y && count == 3) {
				sum(result);
				return;
			}
		}

		if (result[board[x][y]] == 1)
			return;

		int[][] vcopy = copy(visited);
		vcopy[x][y] = 1;

		int[] lcopy = listcopy(result);
		lcopy[board[x][y]] = 1;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (direction == i) {
					check(startx, starty, nx, ny, i, count, lcopy, vcopy);
				}
				if (direction < i) {
					check(startx, starty, nx, ny, i, count + 1, lcopy, vcopy);
				}

			}
		}

	}

	private static int[] listcopy(int[] result) {
		int[] temp = Arrays.copyOf(result, result.length);
		return temp;
	}

	private static int[][] copy(int[][] arr) {
		int[][] temp = new int[arr.length][];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		return temp;
	}

	private static void sum(int[] result) {
		int sum = 0;
		for (int i : result) {
			if (i == 1)
				sum++;
		}
		max = Math.max(sum, max);
	}
}
