package algostudy.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_G5_12904_A와B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String S = br.readLine();
		String T = br.readLine();
		
		// 문자열 거꾸로 뒤집기, 삭제하려고 사용
		StringBuilder s = new StringBuilder();
		StringBuilder t = new StringBuilder();
		s.append(S);
		t.append(T);

		while(true) {
			// s == t이거나 t길이가 0보다 작아지면 반복문 빠져나감
			if(s.toString().equals(t.toString()) || t.length() < 0) break;
			if(t.charAt(t.length()-1) == 'A')
				t.deleteCharAt(t.length()-1); // 가장 뒤에 A일 때 A삭제
			else {
				t.deleteCharAt(t.length()-1); // 가장 뒤에 B일 때 B삭제
				t.reverse(); // 뒤집기
			}
		}
		
		// s==t이면 1, 아니면 0 출력
		if(s.toString().equals(t.toString()))
			System.out.println(1);
		else
			System.out.println(0);
	}
}
