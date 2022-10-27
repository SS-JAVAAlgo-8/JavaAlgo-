package study.day221020.problem;

import java.util.*;
import java.io.*;

public class BOJ_12919_AandB2 { // 11536 KB, 80 ms

	static StringBuffer S = new StringBuffer();
	static StringBuffer T = new StringBuffer();
	static int ans;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S.append(br.readLine());
		T.append(br.readLine());
		ans = 0;
		
		DFS(T.toString());
		
		System.out.println(ans);
	}
	

	private static void DFS(String s) { // 매개변수로 들어온 것을 직접 건들지 않기!
		
		if(s.length() == S.length()) {
			if(s.equals(S.toString())) ans = 1;
			return;
		}
		
		if(s.charAt(s.length()-1) == 'A') {
			DFS(s.substring(0, s.length()-1));
		}
		
		if(s.charAt(0) == 'B') {
			StringBuffer re = new StringBuffer();
			re.append(s.substring(1, s.length()));
			DFS(re.reverse().toString());
		}
		
		return;
	}

}
/*
문자열의 뒤에 A를 추가한다.
문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
--> 재귀를 위해 거꾸로
뒤에 A가 있다면 A 를 제거
뒤집고 B가 있다면  B 제거
*/