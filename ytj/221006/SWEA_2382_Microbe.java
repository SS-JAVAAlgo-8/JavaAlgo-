package study.day221006.problem;

import java.io.*;
import java.util.*;

public class SWEA_2382_Microbe {// 10:25 ~

	static int N, M, K, ans;		// 셀의 갯수, 격리 시간, 미생물 군집의 갯수
	
	static int[] dr = {0, -1, 1, 0, 0}; // (상: 1, 하: 2, 좌: 3, 우: 4)
	static int[] dc = {0, 0, 0, -1, 1};
	
	static List<Microbe> list;
	static Microbe[][] map;
	
	static class Microbe implements Comparable<Microbe>{
		int r, c;		// 위치
		int num;		// 미생물 수
		int d;			// 이동 방향

		public Microbe(int r, int c, int num, int d) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.d = d;
		}

		@Override
		public int compareTo(Microbe o) {
			return this.num - o.num;
		}

		@Override
		public String toString() {
			return "Microbe [r=" + r + ", c=" + c + ", num=" + num + ", d=" + d + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());	// 셀의 갯수
			M = Integer.parseInt(st.nextToken());	// 격리 시간
			K = Integer.parseInt(st.nextToken());	// 미생물 군집의 갯수
			ans = 0;
			
			list = new ArrayList<>();
			map = new Microbe[N][N];
			
			for (int i = 0; i < K; i++) {
				
				st = new StringTokenizer(br.readLine());
				
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				list.add(map[r][c] = new Microbe(r, c, num, d));
			}
			
			for (int i = 0; i < M; i++) {
				
				Move();
				
				Plus();
				
//				Show();
			}
			
			for(Microbe m : list) ans += m.num;
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void Show() {
		for(Microbe[] arr : map) {
			for(Microbe m : arr) {
				if(m != null) System.out.print(m.num+"\t");
				else System.out.print(0+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void Plus() {
		
		map = new Microbe[N][N];
		
		Collections.sort(list);
		
		int size = list.size()-1;
		for (int i = size; i >= 0; i--) {
			
			Microbe m = list.get(i);
			
			if(map[m.r][m.c] == null) map[m.r][m.c] = m;
			else {
				map[m.r][m.c].num += m.num;
				list.remove(m);
			}
		}
	}

	private static void Move() {
		
		for (int j = 0, size = list.size(); j < size; j++) {
			
			int nr = list.get(j).r + dr[list.get(j).d];
			int nc = list.get(j).c + dc[list.get(j).d];
			
			if(isIn(nr, nc)) {
				list.get(j).r = nr;
				list.get(j).c = nc;
			}
			
			if(isEdge(nr, nc)) {
				list.get(j).num /= 2;
				Changedirection(list.get(j));
			}
		}
	}

	private static void Changedirection(Microbe microbe) {
		switch (microbe.d) {
		case 1:
			microbe.d = 2;
			break;
		case 2:
			microbe.d = 1;
			break;
		case 3:
			microbe.d = 4;
			break;
		case 4:
			microbe.d = 3;
			break;

		default:
			break;
		}
	}

	private static boolean isEdge(int nr, int nc) {
		return nr == 0 || nr == N-1 || nc == 0 || nc == N-1;
	}

	private static boolean isIn(int nr, int nc) {
		return nr < N && nc < N && nr >= 0 && nc >= 0;
	}


}
/*

1
7 2 9   
1 1 7 1 
2 1 7 1
5 1 5 4
3 2 8 4 
4 3 14 1
3 4 3 3 
1 5 8 2 
3 5 100 1
5 5 1 1

*/