/*
dfs

- T를 S로 만들기
- 맨 첫 글자가 B : 글자 뒤집고 맨 뒷 글자 remove
- 맨 뒷 글자가 A : 맨 뒷 글자 remove

 */
package study.day1020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_12919_A와B2 {	// 메모리 : 13,240 kb		실행시간 : 88 ms
	static boolean flag;
	static String S;
	public static void main(String[] args) throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		String[] T = br.readLine().split("");
		List<String> list = new ArrayList<>();
			
		for (String str : T) {
			list.add(str);
		}
			
		dfs(list);
			
		System.out.println(0);
	}
	private static void dfs(List<String> list) {
		if(list.size() <= S.length()) {
			String temp = "";
			for (String str : list) {
				temp += str;
			}
			if(temp.equals(S)) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		
		if(list.get(0).equals("B")) {
			Collections.reverse(list);
			String cup = list.get(list.size() - 1);
			list.remove(list.size() - 1);
			dfs(list);
			list.add(cup);
			Collections.reverse(list);
		}
		if(list.get(list.size() - 1).equals("A")) {
			String cup = list.get(list.size() - 1);
			list.remove(list.size() - 1);
			dfs(list);
			list.add(cup);
		}
	}
}
