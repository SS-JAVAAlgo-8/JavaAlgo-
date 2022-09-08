/*
 * ### 한 줄 소감
`한 줄 소감을 쓰시오`


## 1번 문제 풀이
### 문제 분석
이 문제는...

### 걸린 시간
- 몇 분?

### 풀이 방법
1. 이 문제는...

### 포인트
이 문제의 포인트는...



*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_SwimmingPool {

	static int[] cost;
	static int[] plan;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			cost = new int[4];
			plan = new int[12];
			ans = Integer.MAX_VALUE;
						
			st = new StringTokenizer(br.readLine());
			// 1일, 1달, 3달, 1년 요금
			for (int i = 0; i < cost.length; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}

			// 월별 이용 횟수
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < plan.length; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			findmincost(0, 0);
			
			// 1년 비용과 비교
			if(ans > cost[3]) ans = cost[3];
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void findmincost(int month, int sum) {
		
		if(month >= 12) {
			if(ans > sum) ans = sum;
			System.out.println("=========== \t================");
			return;
		}
		
		System.out.println("방문한 달 : " + (month+1) + "\t현재 까지 비용 : " + sum );

		if(plan[month] == 0) findmincost(month+1, sum);
		
		else {
			findmincost(month+1, sum + (plan[month] * cost[0])); // 1일 이용
			findmincost(month+1, sum + cost[1]);				 // 1달 이용
			findmincost(month+3, sum + cost[2]);				 // 3달 이용
		}
	}

}
/*

1
10 40 100 300   
0 0 2 9 1 5 0 0 0 0 0 0

 */