package study.day220818.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//	메모리 : 40,356 kb  
//	실행 시간 : 353 ms
public class SWEA_2117_SWtest_HomeService { 

	static int[][] map;
	static int totalHomeCnt;
	static int N, M, ans;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			homes = new ArrayList<>();
			map = new int[N][N];
			totalHomeCnt = 0;
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1)
						totalHomeCnt++;
				}
			}
			// 탐색을 끝내도 되는 경우
			// 1. map 에 존재하는 모든 집이 영역 내에 있을 때
			// 2. 영액 내에 모든 곳에 집이 존재 할 때 -> 영역 범위 늘리기
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					bfs(r, c);
//					search(r, c);  :: 맨하탄 거리
				}
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}

	private static void bfs(int r, int c) {
		
		Queue<Integer> qu = new ArrayDeque<>();
		boolean [][] isVisited = new boolean[N][N];
		
		isVisited[r][c] = true;
		int homeCnt = map[r][c];
		int K = 1;
		
		qu.offer(r);
		qu.offer(c);
		
		while(!qu.isEmpty()) {
			
			int benefit = homeCnt * M;
			int cost = K*K + (K-1)*(K-1);
			
			if(benefit >= cost)  // 비용보다 영업 이익이 많을때만 집 갯수 파악하면 됨
				ans = Math.max(ans, homeCnt);
			
			if(homeCnt == totalHomeCnt) // 집 갯수가 총 갯수와 같으면 더 큰 이익이 없으므로 탐색 완료
				return;
			
			for (int n = 0, size = qu.size(); n < size; n += 2) {
				
				int row = qu.poll();
				int col = qu.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];
					if( isIn(nr, nc) && !isVisited[nr][nc]) {
						isVisited[nr][nc] = true;
						qu.offer(nr);
						qu.offer(nc);
						homeCnt += map[nr][nc];
					}
				}
			} // 한 depth 종료
			K++;
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}
	
    static List<Point> homes;
	
    static class Point{
    	int r, c, d;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public void updateDistance(int r, int c) {
			this.d = Math.abs(this.r - r) + Math.abs(this.c - c);
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + ", d=" + d + "]";
		}
    	
    }
    
    private static void search(int r, int c) {
		// r, c에서 각 지점(집)의 거리를 업데이트해서 집계해보자.
    	int [] dists = new int [2*N+1]; // index가 거리 , 값은 거리에 따라 떨어진 집의 갯수
    	for(Point p: homes) {
    		p.updateDistance(r, c);
    		dists[p.d]++;  // 거리만큼 떨어진 집의 갯수를 갱신
    	}
		//System.out.println(Arrays.toString(dists));
    	int cost = 0;
    	int benefit = 0;
    	int homes = 0; // 누적
    	for(int i=0; i<dists.length; i++) {
    		if(dists[i]!=0) { // 해당 거리에 집이 있으면,
    			cost = i*i + (i+1)*(i+1);// 거리가 0 -> K = 1, 거리가  1 -> K = 2
    			homes +=dists[i];
    			benefit = homes * M;
    			
    			if(benefit >=cost) {
				ans = Math.max(ans, homes);
    			}
    		}
    	}
	}

}
