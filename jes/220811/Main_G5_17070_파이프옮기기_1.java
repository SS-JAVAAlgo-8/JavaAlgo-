package algostudy.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_17070_파이프옮기기_1 { // 12684kb 184ms
	// 오른쪽(0), 아래(1), 오른쪽 대각선 아래(2)
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 0, 1};
	static int ans, N;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		// 처음 시작하는 파이프의 오른쪽 끝점에서 시작
		setPipe(0, 1, 0);
		System.out.println(ans);
	}
	
	public static void setPipe(int r, int c, int dir) {
		// 도착했을 때 리턴
		if(r == N-1 && c == N-1) {
            ans += 1;
            return;
        }

		// 각 방향에 따라 처리
        if(dir == 0) {  // 가로일 때 가로, 대각선
            if(c+1 < N && map[r][c+1] == 0) {
            	setPipe(r, c+1, 0);
            }
            if(r+1 < N && c+1 < N && map[r+1][c] == 0 && map[r][c+1] == 0 && map[r+1][c+1] == 0) {
            	setPipe(r+1, c+1, 2);
            }
        }
        else if(dir == 1) {  // 세로일 때 세로, 대각선
            if(r+1 < N && map[r+1][c] == 0) {
            	setPipe(r+1, c, 1);
            }
            if(r+1 < N && c+1 < N && map[r+1][c] == 0 && map[r][c+1] == 0 && map[r+1][c+1] == 0) {
            	setPipe(r+1, c+1, 2);
            }
        }
        else {  // 대각선일 때 가로, 세로, 대각선
            if(c+1 < N && map[r][c+1] == 0) {
            	setPipe(r, c+1, 0);
            }
            if(r+1 < N && map[r+1][c] == 0) {
            	setPipe(r+1, c, 1);
            }
            if(r+1 < N && c+1 < N && map[r+1][c] == 0 && map[r][c+1] == 0 && map[r+1][c+1] == 0) {
            	setPipe(r+1, c+1, 2);
            }
        }
	}
}