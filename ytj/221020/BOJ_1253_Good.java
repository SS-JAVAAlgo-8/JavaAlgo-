package study.day221020.problem;

import java.util.*;
import java.io.*;

public class BOJ_1253_Good {

	static int N, ans;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException { // 14592KB, 236ms
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		ans = 0;
		st = new StringTokenizer(br.readLine());
		list = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) list.add(Integer.parseInt(st.nextToken()));
		
		Collections.sort(list); // 오름차순 정렬
		
		for (int idx = 0; idx < N; idx++) { // 현재 위치의 값이  '좋음' 인지 판단
			int left = 0;
			int right = N-1;
			
			while(true) {
				// 현재 위치와 동일할 때, 범위 맞춰주기
				if(left == idx) left++;
				else if(right == idx) right--;
				
				if(left >= right) break; // 불가능
				
				if(list.get(right) + list.get(left) > list.get(idx)) right--; // 현재 위치의 값보다 두 수의 합이 크다면 큰값중 더 작은 값을 더해줘야 하므로, right 줄이기
				else if(list.get(right) + list.get(left) < list.get(idx)) left++; // 더 크면, 작은값 중 더 큰 값을 더해줘야하므로 , left 늘리기
				else {
					ans++;
					break;
				}
			}
		}
		System.out.println(ans);
	}

}
/*
10
1 2 2 2 2 3 4 6 8 10
 */
