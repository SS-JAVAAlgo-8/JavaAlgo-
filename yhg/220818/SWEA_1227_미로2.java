package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SWEA_1227_미로2 {
	static Deque<int[]> dq = new ArrayDeque<>();
	static String[][] board = new String[100][];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		outer : for (int i = 1; i <= 10; i++) {
			String num = br.readLine();
			String[] des;
			visited = new int[100][100];
			for (int j = 0; j < 100; j++) {
				String[] row = br.readLine().split("");
				for (int k = 0; k < row.length; k++) {
					if (row[k].equals("2")) {
						dq.addLast(new int[] { j, k });
					}		

				}
				board[j] = row;
			}
			//4방탐색 시작
			while (dq.size() > 0) {
				int[] info = dq.removeFirst();
				int x = info[0];
				int y = info[1];
				visited[x][y] = 1;
                     
				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];

					if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100 && visited[nx][ny] == 0) { //방문한적이 없으면?
						if (board[nx][ny].equals("0")) { // 지나갈 수 있는 길이면?
							dq.addLast(new int[] { nx, ny }); //추가
							}
						else if (board[nx][ny].equals("3")) { // 만약 도착지라면
							System.out.println("#" + i + " " + 1); //출력
							dq.clear();
							continue outer;
						}
					}
				}
			}
			
			System.out.println("#" + i + " " + 0);

		}
	}
}
