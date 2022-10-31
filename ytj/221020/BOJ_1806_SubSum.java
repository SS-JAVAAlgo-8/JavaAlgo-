package study.day221020.problem;

import java.util.*;
import java.io.*;

public class BOJ_1806_SubSum {

	static int N, S, ans;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		ans = 100001;
		
		list = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)	{
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		list.add(0);   // 반복문의 탈출 조건을 주기 위해 추가 
		int start = 0; // 시작 위치
		int end = 0;   // 끝    위치
		int sum = list.get(0);  // 시작 ~ 끝 위치 까지의 수열의 합
		
		while(true) {
			
			if(end == N) break; // end가 처음 주어진 수열의 끝을 넘어갔다면 더 이상 조건을 만족하는 수열을 만들 수 없으므로, 반복문 탈출 
			
			if(sum < S) { // 현재 수열의 합이 S 보다 작다면, 수열의 끝 위치를 +1 갱신해 주고 그 위치의 값을 더 해준다.
				end++;
				sum += list.get(end);
			}
			
			else { // 현재 수열의 합이 S 보다 크거나 같다면, 현재 수열의 합에서  수열의 시작 위치의 값을 빼주고 시작 위치를 +1 갱신한다. 
//				System.out.println("start : " + start + " end : " + end + " || sum : " + sum);
				ans = Math.min(ans, end-start+1);
				sum -= list.get(start);
				start++;
			}
			
		}
		
		if(ans == 100001) System.out.println(0);
		else 			  System.out.println(ans);
		
	}

}

/*
10 15
5 1 3 5 10 7 4 9 2 8
*/
