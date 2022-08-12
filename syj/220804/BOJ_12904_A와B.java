/*
 * 메모리 : 13260kb
 * 실행시간 : 144ms
 * 풀이방법 :
 * - t를 s로 만든다
 * - t의 끝이 A일 경우 A만 삭제
 * - t의 끝이 B일 경우 B삭제 후 문자 뒤집기
 * 역으로 계산하며 마지막에 t와 s가 같으면 result = 1
 */

package study.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_12904_A와B {
	static int result = 0;	// 결과값 0으로 초기화
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// s 입력받기
		String[] temp = br.readLine().split("");
		ArrayList<String> s = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			s.add(temp[i]);
		}
		// t 입력받기
		temp = br.readLine().split("");
		ArrayList<String> t = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			t.add(temp[i]);
		}
		
		String str;	// 스왑하기 위한 저장 변수
		
		while(s.size() < t.size()) {	// s와 t의 길이가 같아질 때까지 반복
			if(t.get(t.size()-1).equals("A")) {	// t의 맨 끝이 A일 경우
				t.remove(t.size() - 1);	// A만 삭제
			}else {	// t의 맨 끝이 B일 경우
				t.remove(t.size() - 1);	// B 삭제 후
				for (int i = 0; i < t.size()/2; i++) {	// 문자 뒤집기
					str = t.get(i);
					t.set(i, t.get(t.size() - 1 - i));
					t.set(t.size() - 1 - i, str);
				}
			}
		}
		if(Arrays.equals(s.toArray(), t.toArray())) result = 1;	// s와 t가 같으면 result를 1로 변경
		System.out.println(result);
	}
}
