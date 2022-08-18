package algostudy.day0811;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_G3_17135_캐슬디펜스 {
	static int N, M, D, max;
	static int[][] map;
	static int[] loc;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
	
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		loc = new int[3];
		max = 0;
		comb(0,0);
		System.out.println(max);
	}

	// 궁수 위치 지정
	private static void comb(int start, int cnt) {
		if(cnt == 3) {
			// 궁수 배치 완료. 최대 물리칠 수 있는 적 수 카운트
			defence(new int[N][M]);
			return;
		}
		
		for(int i=start; i<M; i++) {
			loc[cnt] = i;
			comb(start+1, cnt+1);
		}
	}

	// 가장 가까운 적 찾아 동시에 공격하고 물리친 적 카운트
	private static void defence(int[][] map) {
		map = mapCopy();
		
		int cnt = 0;
		int turn = 0;
		while(turn < N) {
			// 죽인 적 겹치지 않게 카운트하기 위해 사용
			boolean[][] isKilled = new boolean[N][M];
			List<Point> killed = new ArrayList<>();
			
			for(int i = 0; i<3; i++) { // 궁수 3명 공격
				// 가장 가까운 적 찾기
				int dr = N-turn;
				int dc = loc[i];
				int min = Integer.MAX_VALUE;
				int r = -1;
				int c = -1;
				
				// 세로줄로 검사해서 적과 궁수의 거리 계산
				for(int m = M-1; m >= 0; m--) {
					for(int n = 0; n < N; n++) {
						if(map[n][m] == 1) {
							int d = Math.abs(dr-n) + Math.abs(dc-m);
							if(d > 0 && d <= D) {
								if(min >= d) {
									min = d;
									r = n;
									c = m;
								}
							}
						}
					}
				}
				
				// 가까운 적이 있을 때 이미 처리한 적은 카운트 하지 않음
				if(r!=-1 || c!=-1) {
					if(!isKilled[r][c]) {
						killed.add(new Point(r, c));
						cnt++;
					}
					isKilled[r][c] = true;
				}
			}
			
			for(int i=0; i<killed.size(); i++) {
				map[killed.get(i).x][killed.get(i).y] = 0;
			}
			// 성으로 들어온 적 맵에서 제거
			for(int i=0; i<M; i++)
				map[N-turn-1][i] = 0;
			
			turn++;
		}
		
		max = Math.max(max, cnt);
	}
	
	static int[][] mapCopy() {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}
}