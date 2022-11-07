package algostudy.day1020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 	목표: 주어진 조건을 통해 S를 T로 만들수 있는지 여부 구하기
 * 
 *	조건
 * 	1. 두 문자열 S와 T가 주어졌을 때 S -> T 로 바꿈
 *  2. 두 가지 연산만 가능
 *    2-1. 문자열의 뒤에 A 추가
 *    2-2. 문자열의 뒤에 B를 추가하고 문자열을 뒤집음
 *  3. 바꿀 수 있으면 1, 없으면 0 출력
 *  4. 1 ≤ S의 길이 ≤ 49, 2 ≤ T의 길이 ≤ 50, S의 길이 < T의 길이
 *  
 *  풀이 계획
 *  - BFS
 *  
 * 	풀이 방법
 *  1. 주어진 조건을 반대로 적용하여 T -> S 가 가능한지 판단
 *  2. 두가지 연산을 모두 적용하면서 큐에 담고 큐가 빌때까지 반복
 *  3. T가 S가 되면 1 출력하고 종료
 *  4. 큐가 빌때까지 T가 S와 같아지지 않으면 0 출력
 *  
 */
public class Main_G5_12919_A와B2 { // 11592kb 84ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String S = br.readLine();
		String T = br.readLine();
		
		Queue<String> q = new LinkedList<>();
		q.offer(T);
		while(!q.isEmpty()) {
			String st = q.poll();
			if(st.equals(S)) {
				System.out.println(1);
				return;
			}
			if(st.length()>1 && st.charAt(0)=='B')
				q.offer(new StringBuilder(st.substring(1)).reverse().toString()); // 맨 앞에 B가 있을 때 B 제거하고 뒤집기
			if(st.length()>1 && st.charAt(st.length()-1)=='A')
				q.offer(st.substring(0, st.length()-1)); // 맨 뒤에 A가 있을 때 A 제거
		}
		
		System.out.println(0);
	}
}