package algostudy.day1006;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.10 22:30
 * 	풀이 종료 시간: 22.10.10 23:50
 *  풀이 시간 : 설계 10분 + 코딩 30분 + 디버깅 30분 = 70분
 *  
 * 	목표: 출발 위치, 진행 방향 임의로 선정했을 때 게임에서 얻을 수 있는 점수의 최댓값 구하기
 * 
 *	조건
 * 	1. 1~5: 블록 (부딪혀서 방향이 바뀌면 점수+1)
 * 	2. 6~10: 웜홀 -> 웜홀은 짝이 있고 동일한 숫자의 반대편 웜홀로 방향 그대로 빠져나옴
 *  3. -1: 블랙홀 -> 종료 조건
 *  4. 벽 : 진행 방향의 반대로 방향이 바뀌며 점수+1
 *  5. 블랙홀에 빠지거나 제자리로 돌아오면 게임 종료
 *  6. 블록, 월홀, 블랙홀이 있는 자리에서 시작 불가능 -> 0(빈칸)에서만 시작 가능
 *  
 *  풀이 계획
 *  - 요구사항대로 구현 -> 웜홀에서 break문 안줘서 무한루프 도는거 디버깅하느라 오래걸렸다
 *  
 * 	풀이 방법
 *  1. 웜홀 6~10을 짝지어서 리스트로 입력받기
 *  2. 게임판 숫자가 0인 곳에서 4방향으로 각각 게임 시작
 *  3. 블록이 있는 경우 방향 바꾸기
 *  4. 벽이 있는 경우 방향 바꾸기
 *  5. 블랙홀이 있거나 제자리로 돌아온 경우 게임 종료
 *  6. 웜홀이 있는 경우 반대편 웜홀로 빠져나오기
 *  7. 종료조건 만날때까지 반복
 *  
 */

public class Solution_5650_핀볼게임 { // 46472kb 227ms
	static int N, ans;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Point>[] wormhole;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			wormhole = new ArrayList[5];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6) { // 웜홀인 경우
						if(wormhole[map[i][j]-6] == null) wormhole[map[i][j]-6] = new ArrayList<>();
						wormhole[map[i][j]-6].add(new Point(i, j));
					}
				}
			}
			
			ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 빈칸에서 시작
					if(map[i][j]==0) {
						for(int d=0; d<4; d++) {
							game(i, j, d);
						}
					}						
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	// x: x좌표 , y: y좌표, d: 방향(0~3)
	private static void game(int x, int y, int d) {
		int score = 0;
		int r = x;
		int c = y;
		while(true) {
			int dx = 0;
			int dy = 0;
			
			// 블록인 경우
			if(map[r][c]>=1 && map[r][c]<=5	) {
				d = changeDir(map[r][c], d);
				score++;
			}

			dx = r+dr[d];
			dy = c+dc[d];
			
			// 벽인 경우
			if(dx<0 || dy<0 || dx>=N || dy>=N) {
				d = changeDir(5, d);
				score++;
				dx = r;
				dy = c;
			}
			
			// 블랙홀이거나 제자리인 경우(종료 조건)
			if(map[dx][dy]==-1 || (dx==x && dy==y)) {
				break;
			}

			// 웜홀인 경우
			if(map[dx][dy]>=6) {
				int num = map[dx][dy]-6;
				// 2개의 웜홀 중 어떤 웜홀인지 찾고 반대편 웜홀로 이동
				for(int i=0; i<2; i++) {
					if(wormhole[num].get(i).x==dx && wormhole[num].get(i).y==dy) {
						if(i==1) {
							dx = wormhole[num].get(0).x;
							dy = wormhole[num].get(0).y;
						}
						else {
							dx = wormhole[num].get(1).x;
							dy = wormhole[num].get(1).y;
						}
						break;
					}
				}
			}
			r = dx;
			c = dy;
		}
		ans = Math.max(ans, score);
	}

	// 방향 바꾸기
	private static int changeDir(int num, int dir) {
		switch(num) {
		case 1:
			dir = (dir==0) ? 2 : (dir==1) ? 3 : (dir==2) ? 1 : 0;
			break;
		case 2:
			dir = (dir==0) ? 1 : (dir==1) ? 3 : (dir==2) ? 0 : 2;
			break;
		case 3:
			dir = (dir==0) ? 3 : (dir==1) ? 2 : (dir==2) ? 0 : 1;
			break;
		case 4:
			dir = (dir==0) ? 2 : (dir==1) ? 0 : (dir==2) ? 3 : 1;
			break;
		case 5:
			dir = (dir==0) ? 2 : (dir==1) ? 3 : (dir==2) ? 0 : 1;
			break;
		}
		return dir;
	}
}
