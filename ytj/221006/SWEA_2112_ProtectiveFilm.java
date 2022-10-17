package study.day221006.problem;

import java.io.*;
import java.util.*;

public class SWEA_2112_ProtectiveFilm { // 1:30 ~ 4:01 | 32,980 kb  | 399 ms  처음 부분집합과 순열을 통해 A,B,선택x 를 뽑으려고해서 시간만 소요함
	
	static int D, W, K, ans;
	static int [][] map;
	static int [][] copy;
	
//	static boolean [] selected; // 조합 => 막 선택
//	static boolean [] checked;  // 부분 집합 => 막 특성 A or B 부여
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			
			map = new int[D][W];
			copy = new int[D][W];
			
//			checked = new boolean[D];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					copy[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			System.out.println(Evaluate());
			Simulate();
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}


	private static void Simulate() {
		if(Evaluate()) {
			ans = 0;
			return;
		}
		
		Pickfilm(0, 0);
	}


	private static void Pickfilm(int cnt, int idx) {
		
		if(idx == D) {
			if(Evaluate()) ans = Math.min(ans, cnt);
			return;
		}
		
		// 주입하는 막 갯수가 현재까지 최솟값 보다 크면 판단 불필요
		if(cnt >= ans) return;
		
		
		// 해당 idx 막에  0 : A 를 주입한 경우 
		for(int i = 0 ; i < W ; ++i) 
			copy[idx][i] = 0;
		Pickfilm(cnt + 1, idx + 1);

		// 해당 idx 막에  1 : B 를 주입한 경우 
		for(int i = 0 ; i < W ; ++i) 
			copy[idx][i] = 1;
		Pickfilm(cnt + 1, idx + 1);
		
		// 주입하지 않는 경우
		Pickfilm(cnt, idx + 1);
		
		// 해당 idx 막 되돌리기 
		for(int i = 0 ; i < W ; ++i) 
			copy[idx][i] = map[idx][i];
	}


	private static boolean Evaluate() { // 성능 평가
		
		for (int i = 0; i < W; i++) {
			boolean check = false;
			for (int j = 0; j < D - (K-1); j++) {
				int cnt = 1;
				for (int k = 1; k < K; k++) {	// 열마다 K 만큼 연속된 값이 있는지 cnt 증가 
					if(copy[j][i] == copy[j+k][i]) cnt++;
					else cnt = 1;				// 값이 달라지면 cnt 초기화
				}
				if(cnt == K) check = true;		// 연속된 갯수가 K와 같으면 해당 열은 통과
			}
			if(!check) return false;			// 하나의 열이라도 통과 되지 않으면 false 반환
		}
		return true;
	}

}
/*
1
6 8 3
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1

1
6 8 3
1 1 1 1 0 0 1 0
0 0 1 1 0 1 0 1
1 1 1 1 0 0 1 0
1 1 1 0 0 1 1 0
1 1 0 1 1 1 1 0
1 1 1 0 0 1 1 0
*/