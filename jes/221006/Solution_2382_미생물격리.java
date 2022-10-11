package algostudy.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.09 23:40
 * 	풀이 종료 시간: 22.10.10 01:13
 *  풀이 시간 : 설계 20분 + 코딩 20분 + 디버깅 30분 = 70분
 *  
 * 	목표: M시간 후 남은 미생물의 수
 * 
 *	조건
 * 	1. N*N 정사각형 구역 안에 K개의 미생물 군집 주어짐
 *  2. 가장자리에는 약품이 칠해져 있음
 *  3. 각 군집은 1시간마다 이동방향에 따라 다음 셀로 이동
 *  4. 가장자리로 이동하면 미생물 수/2
 *     4-1. 미생물 수가 0이 되면 군집 사라짐
 *  5. 두 개 이상의 군집이 한 셀에 모이는 경우 군집 합쳐짐
 *     5-1. 미생물 수는 합쳐진 군집들의 미생물 수의 합
 *     5-2. 이동 방향은 합쳐진 군집 중 미생물 수가 가장 많은 군집
 *  6. M 시간동안 반복
 * 
 *  풀이 계획
 *  - 구현
 *  
 * 	풀이 방법
 *  1. 입력받은 군집들을 미생물 수에 따라 오름차순 정렬
 *  2. 군집 이동 -> 오름차순 정렬해두었기 때문에 무조건 다음에 나오는 군집이 미생물 수가 더 많음
 *  3. M번 반복
 *  
 */

public class Solution_2382_미생물격리 { // 109308kb 327ms
	static class Micro implements Comparable<Micro>{
		int x, y, num, dir;

		public Micro(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Micro o) {
			return Integer.compare(this.num, o.num);
		}
		
	}
	static int N, M, K;
	static int[][] map;
	static Micro[] micro;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 한 변에 있는 셀의 개수(정사각형 가로, 세로)
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수
			micro = new Micro[K+1]; // 미생물 군집 배열
			map = new int[N][N];
			for(int i=1; i<=K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				micro[i] = new Micro(x, y, num, dir);
			}
			micro[0] = new Micro(0, 0, 0, 0);
			move();
			sb.append("#").append(t).append(" ").append(count()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int count() {
		int sum = 0;
		for(int i=1; i<=K; i++) {
			sum += micro[i].num;
		}
		return sum;
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static void move() {
		for(int time=0; time<M; time++) {
			Arrays.sort(micro);
			map = new int[N][N];
			for(int i=1; i<=K; i++) {
				// 군집이 존재하는 경우
				if(micro[i].num!=0) {
					micro[i].x += dr[micro[i].dir];
					micro[i].y += dc[micro[i].dir];
					
					// 가장자리로 이동한 경우
					if(micro[i].x<1 || micro[i].y<1 || micro[i].x>N-2 || micro[i].y>N-2) {
						// 미생물 수 절반 감소
						micro[i].num /= 2;
						// 미생물 수가 0이 되면 군집 X
						if(micro[i].num==0) continue;
						// 방향 반대로 전환
						micro[i].dir = (micro[i].dir==0) ? 1 : (micro[i].dir==2) ? 3: (micro[i].dir==3) ? 2 : 0;
					}
					
					// 이동한 곳에 이미 다른 군집이 있는 경우(정렬해두었기 때문에 현재 군집 미생물이 더 많음)
					if(map[micro[i].x][micro[i].y]!=0) {
						micro[i].num += micro[map[micro[i].x][micro[i].y]].num;
						micro[map[micro[i].x][micro[i].y]].num = 0;		
					}
					
					// 새로운 자리로 이동
					map[micro[i].x][micro[i].y] = i;
				}
			}
		}
	}
}
