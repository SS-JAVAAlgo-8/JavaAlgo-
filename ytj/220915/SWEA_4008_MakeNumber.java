package study.day220915.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4008_MakeNumber {

	static int N, max, min, ans;
	static int[] num;
	static int[] operator;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T ; tc++) {
			
			N = Integer.parseInt(br.readLine());
			max = -100_000_000 - 1;
			min = 100_000_000 + 1; 
			num = new int[N];
			
			st = new StringTokenizer(br.readLine());   		// 각 연산자의 갯수
			
			int plus = Integer.parseInt(st.nextToken());
			int subtract = Integer.parseInt(st.nextToken());
			int multiply = Integer.parseInt(st.nextToken());
			int divide = Integer.parseInt(st.nextToken());
			
			int[] operator = {plus, subtract, multiply, divide};
			
			st = new StringTokenizer(br.readLine());		// 수식에 사용 되는 숫자
			
			for(int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			// 입력
			
			DFS(1, num[0], operator[0], operator[1], operator[2], operator[3]);
			
			ans = Math.abs(max - min);
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void DFS(int cnt, int val, int plus, int subtract, int multiply, int divide) {
		
		if(cnt == N) {
			if(val > max) max = val;
			if(val < min) min = val;
		}
		// 연산자 선택하면서 다음 값을 하나씩 받아와 계산 하기때문에 연산자에 대한 순서를 상관할 필요가 없다
		if(plus > 0) 	 DFS(cnt+1, val + num[cnt], plus-1, subtract, multiply, divide);		
		if(subtract > 0) DFS(cnt+1, val - num[cnt], plus, subtract-1, multiply, divide);		
		if(multiply > 0) DFS(cnt+1, val * num[cnt], plus, subtract, multiply-1, divide);		
		if(divide > 0)	 DFS(cnt+1, val / num[cnt], plus, subtract, multiply, divide-1);
	}

}
/*

1
5
2 1 0 1
3 5 3 7 9

1
6
4 1 0 0
1 2 3 4 5 6

*/
