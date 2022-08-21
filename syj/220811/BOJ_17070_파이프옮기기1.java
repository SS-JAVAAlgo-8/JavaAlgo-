/*
 * dfs 활용
 * 
 * ^ 0
 * 1 2
 * 
 * ^ : 현재 파이프의 한쪽 끝
 * 가로 : 0번 위치만 확인
 * 세로 : 1번 위치만 확인
 * 대각선 : 0, 1, 2번 위치 확인
 */
package study.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17070_파이프옮기기1 {	// 메모리 : 12872 kb	실행시간 : 152 ms
	static int[][] map;
	static int cnt = 0;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(temp[j - 1]);
			}
		}
		if(map[N][N] == 1) System.out.println(0);   // 도착지점에 벽이 있을 경우 옮기기 불가능
		else {
			movePipe(1, 2, 0);  // 현재 파이프 한쪽 끝 위치와 파이프 형태(처음엔 가로) 전달
			System.out.println(cnt);
		}
	}
	static void movePipe(int r, int c, int i) {	// r, c : 현재 위치, i : 파이프 모양(0 : 가로, 1 : 세로, 2 : 대각선)
		
        // 기저조건
        if(r == N && c == N) {	// 파이프를 끝까지 옮겼을 경우 cnt++
			cnt++;
			return;
		}
        // 미리 조건을 확인한 뒤 재귀호출 (쓸데없는 재귀 방지)
        // "가로 : 가로, 대각선" 	"세로 : 세로, 대각선"	"대각선 : 가로, 세로, 대각선"	
		switch (i) {	
		case 0:
            // 가로	
			if(c + 1 <= N && map[r][c + 1] == 0) {		
				movePipe(r, c + 1, 0);
			}
            // 대각선
			if(r + 1 <= N && c + 1 <= N && 
					map[r + 1][c] == 0 && map[r][c + 1] == 0 && map[r + 1][c + 1] == 0) {				
				movePipe(r + 1, c + 1, 2);
			}
			break;
		case 1:
            // 세로
			if(r + 1 <= N && map[r + 1][c] == 0) {				
				movePipe(r + 1, c, 1);
			}
            // 대각선
			if(r + 1 <= N && c + 1 <= N && 
					map[r + 1][c] == 0 && map[r][c + 1] == 0 && map[r + 1][c + 1] == 0) {			
				movePipe(r + 1, c + 1, 2);
			}
			break;
		case 2:
            // 가로
			if(c + 1 <= N && map[r][c + 1] == 0) {			
				movePipe(r, c + 1, 0);
			}
            // 세로
			if(r + 1 <= N && map[r + 1][c] == 0) {			
				movePipe(r + 1, c, 1);
			}
            // 대각선
			if(r + 1 <= N && c + 1 <= N && 
					map[r + 1][c] == 0 && map[r][c + 1] == 0 && map[r + 1][c + 1] == 0) {		
				movePipe(r + 1, c + 1, 2);
			}
			break;
		}
	}
}