package study.day221013.problem;

import java.io.*;
import java.util.*;

public class BOJ_1238_Party {
	
	static int N, M, X, ans;
	static Queue<Village> qu;
	static boolean [] visited;
	
	static List<Village> list;
	static class Village {
		int no;
		int cnt;
		List<Connect> cons = new ArrayList<>();

		public Village(int no, int cnt) {
			this.no = no;
			this.cnt = cnt;
		}
	}

	static class Connect {
		int no;
		int con;
		int time;

		public Connect(int no, int con, int time) {
			this.no = no;
			this.con = con;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		list = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) list.add(new Village(i, 0)); // 마을 갯수만큼 village 객체 생성 및 list에 담기
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			list.get(start-1).cons.add(new Connect(start, end, time));
//			System.out.println("no : " + list.get(start-1).no + "con : " + start + " -> " + end + " : " + time);
		}
		
		int []total = new int[N];
		for (int i = 0; i < N; i++) {
		    ans = Integer.MAX_VALUE;
			visited = new boolean[N];
			if(i+1 == X) continue;
			DFStoX(i, 0);
			total[i] = ans;
		}
		
		for (int i = 0; i < N; i++) {
		    ans = Integer.MAX_VALUE;
			visited = new boolean[N];
			if(i+1 == X) continue;
			DFStoHome(X-1, i, 0);
			total[i] += ans;
		}
		
		int max = 0;
		// 1~N 마을에서 X 까지 가는데 걸리 시간들을 비교해서 가장 큰값을 ans로
		for (int i = 0; i < total.length; i++) {
			max = Math.max(max, total[i]);
		}
		System.out.println(max);
	}

	
	private static void DFStoHome(int party, int home, int sum) {
		if(party == home) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = 0; i < list.get(party).cons.size(); i++) {
			if(visited[party]) continue;
			
			visited[party] = true;
			DFStoHome(list.get(party).cons.get(i).con - 1, home ,sum + list.get(party).cons.get(i).time);
			visited[party] = false;
		}
	}


	private static void DFStoX(int home, int sum) {
		if(home+1 == X) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = 0; i < list.get(home).cons.size(); i++) {
			if(visited[home]) continue;
			
			visited[home] = true;
			DFStoX(list.get(home).cons.get(i).con - 1, sum + list.get(home).cons.get(i).time);
			visited[home] = false;
		}
	}

}
