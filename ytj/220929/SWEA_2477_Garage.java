package study.day220929.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2477_Garage {
	
	// 3:13 ~
	
	static int N; 	// 접수 창구의 갯수 
	static int M; 	// 정비 창구의 갯수
	static int K; 	// 방문한 고객의 수
	static int A; 	// 비교할 창구 번호
	static int B; 	// 비교할 정비 번호
	
	static int Time;  // 현재 시간
	static int Num;   // 고객 번호
	
	static int ans;
	
	static Customer[] list; // 고객 리스트
	static Deque<Customer> waitR;
	
	static int [] Nt; // 각 번호에 해당하는 접수 창구의 소요 시간
	static int [] Mt; // " 차량 정비 소요 시간
	static int [] Kt; // " 고객 방문 시간
	
	static boolean [] reception; // 현재 접수 창구 상황
	static boolean [] service;	 // 현재 정비 창구 상황
	
	static class Customer {
		int no;		// 고객번호
		int time; 	// 도착 시간
		int rNum;	// 본인 접수 창구 번호
		int rst;	// 접수 창구에 배정 받은 시간		
		int sNum;	// 본인 정비 창구 번호		
		int sst;	// 정비 창구에 배정 받은 시간
		
		public Customer(int no, int time) {
			this.no = no;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			Num = 0;
			ans = 0;
			waitR = new ArrayDeque<>();
			list = new Customer[K+1];
			
			st = new StringTokenizer(br.readLine());
			
			Nt = new int[N+1];
			for (int i = 1; i <= N; i++) {
				Nt[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			Mt = new int[M+1];
			for (int i = 1; i <= M; i++) {
				Mt[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			Kt = new int[K+1];
			for (int i = 1; i <= K; i++) {
				Kt[i] = Integer.parseInt(st.nextToken());
				list[i] = new Customer(i, Kt[i]);
				waitR.add(list[i]);
			}
			
			reception = new boolean[N+1];
			service = new boolean[M+1];
			
			Time = Kt[0];
			
			run();
			
			for (int i = 1; i <= K; i++) {
				if(list[i].rNum == A && list[i].sNum == B)
					ans += list[i].no;				
			}
			
			if(ans == 0 ) ans = -1;
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	private static void run() {
		/*
		 * 1. 현재 시간에 방문한 고객이 있는지 판단, 있으면 일단 창구대기로 보냄 
		 * 2. 접수 창구가 비어있으면 대기에서 접수 창구로 보냄, 접수창고에 여유가 없으면 창구대기로 보냄 
		 * 3. 현재 시간이 본인 도착시간 + 창구 소요시간 과 같다면, 비어있는 정비 창구로 보냄  , 비어있지 않으면 정비대기로 보냄
		 * 
		*/
		
		Customer[] Reception = new Customer[N+1];
		Deque<Customer> waitS = new ArrayDeque<>();
		Customer[] Service = new Customer[N+1];		
		int cnt = 0;
		
		while(cnt != K) {
			
			for (int i = 1; i <= N; i++) {
				// 접수 완료된 사람을 찾아 정비 대기소에 보낸다.
				if(reception[i] && Time == Nt[i] + Reception[i].rst ) {
					waitS.add(Reception[i]);
					Reception[i] = null;
					reception[i] = false;
				}
				
				// 접수 대기소에서 사람을 뽑아 비어있는 접수대로 보냄
				if(!waitR.isEmpty() && !reception[i] && waitR.peek().time <= Time) {
					Customer c = waitR.poll();
					c.rNum = i;
					c.rst = Time;
					Reception[i] = c;
					reception[i] = true;
				}
			}
			
			for (int i = 1; i <= M; i++) {
				
				// 정비 접수가 완료된 사람은 창구에서 비워준다.
				if(service[i] && Time == Mt[i] + Service[i].sst ) {
					Service[i] = null;
					service[i] = false;
					cnt++;
				}
				
				// 정비 대기소에서 사람을 뽑아 비어있는 정비 창구로 보냄
				if(!waitS.isEmpty() && !service[i]) {
					Customer c = waitS.poll();
					c.sNum = i;
					c.sst = Time;
					Service[i] = c;
					service[i] = true;
				}
			}
			
			Time++;
		}
	}
}
/*

1
1 1 2 1 1
5
7
7 10

1
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4

1
2 2 2 1 1
10 1
1 1
1 2

*/	
