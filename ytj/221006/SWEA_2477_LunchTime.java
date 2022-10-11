package study.day221006.problem;

import java.io.*;
import java.util.*;

public class SWEA_2477_LunchTime { // 7:35 ~ 9:43 | 33,928 KB | 143 ms
	
	static int N, ans;
	static int [][] map;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
//	static int [][] stair;
	static List<Person> list;
	static List<Stair> stairs;
	static boolean [] selected;
	
	static class Person implements Comparable<Person> {
		int r, c;		// 초기 위치
		int arrive;		// 할당된 계단 까지 도착한 시간 ( 맨허튼 거리 )
		int move;		// 할단된 계단의 아래층 까지 이동 시간
		int finish;		// 아래층 도착 시간
		
		public Person(int r, int c) {
			this.r = r; 
			this.c = c;
		}

		@Override
		public int compareTo(Person o) {
			return this.arrive - o.arrive;
		}
		
	}
	
	static class Stair {
		int r, c;		// 초기 위치
		int time;		// 계단의 이동시간

		public Stair(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			map = new int[N][N];
			
//			stair = new int[2][10];
			list = new ArrayList<>();
			stairs = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) list.add(new Person(i, j));
					if(map[i][j] > 1) stairs.add(new Stair(i, j, map[i][j]));
				}
			}
			
			selected = new boolean [list.size()];
			
			setStair(0);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void setStair(int cnt) {
		if(cnt == list.size()) {
			
			int maxtime = setTime();
			ans = Math.min(ans, maxtime);
			
			return;
		}
		
		selected[cnt] = true;
		setStair(cnt+1);
		
		selected[cnt] = false;
		setStair(cnt+1);
	}

	private static int setTime() {
		List<Person> stair0 = new ArrayList<>();
		List<Person> stair1 = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			if(selected[i]) { // 계단 1 배정, 해당 계단까지 도착시간 및 계단이동시간 할당
				list.get(i).arrive = Math.abs(list.get(i).r - stairs.get(0).r) + Math.abs(list.get(i).c - stairs.get(0).c);
				list.get(i).move = stairs.get(0).time;
				stair0.add(list.get(i));
			}
			else { // 계단 2 배정
				list.get(i).arrive = Math.abs(list.get(i).r - stairs.get(1).r) + Math.abs(list.get(i).c - stairs.get(1).c);
				list.get(i).move = stairs.get(1).time;
				stair1.add(list.get(i));
			}
		}
		
		Collections.sort(stair0);
		Collections.sort(stair1);

		// 0, 1, 2 
		// 3, 4, 5 
		// 6, 7, 8 
		// 9
		for (int i = 0; i < stair0.size(); i++) {
			if(i == 0) stair0.get(i).finish = stair0.get(i).arrive + stair0.get(i).move + 1;
			if(i == 1) stair0.get(i).finish = stair0.get(i).arrive + stair0.get(i).move + 1;
			if(i == 2) stair0.get(i).finish = stair0.get(i).arrive + stair0.get(i).move + 1;
			
			if(i >= 3) {
				if(stair0.get(i-3).finish <= stair0.get(i).arrive) 
					stair0.get(i).finish = stair0.get(i).arrive + stair0.get(i).move + 1; // 도착시간이 크므로 비어있는 상태
				else 
					stair0.get(i).finish = stair0.get(i-3).finish + stair0.get(i).move; // 기다렸기 때문에 대기시간 완료한 상태
			}
		}
		
		for (int i = 0; i < stair1.size(); i++) {
			if(i == 0) stair1.get(i).finish = stair1.get(i).arrive + stair1.get(i).move + 1;
			if(i == 1) stair1.get(i).finish = stair1.get(i).arrive + stair1.get(i).move + 1;
			if(i == 2) stair1.get(i).finish = stair1.get(i).arrive + stair1.get(i).move + 1;
			
			if(i >= 3) {
				if(stair1.get(i-3).finish <= stair1.get(i).arrive) 
					stair1.get(i).finish = stair1.get(i).arrive + stair1.get(i).move + 1; // 도착시간이 크므로 비어있는 상태
				else 
					stair1.get(i).finish = stair1.get(i-3).finish + stair1.get(i).move; // 기다렸기 때문에 대기시간 완료한 상태
			}
		}
		
		int a, b;
		
		if (stair1.size() == 0)	a = 0;
		else a = stair1.get(stair1.size()-1).finish;
		
		if (stair0.size() == 0)	b = 0;
		else b = stair0.get(stair0.size()-1).finish;
		
		return Math.max(a, b); // 가장 늦은 인원의 도착시간 반환
	}

}
/*

1
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0

1
5
0 0 1 1 0
0 0 1 0 2
0 0 0 1 0
0 1 0 0 0
1 0 5 0 0

*/