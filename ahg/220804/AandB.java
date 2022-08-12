package BOJ_12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AandB {
	// 완전탐색으로 시작부터 하나씩 붙여가며 접근 -> X (시간초과로 에러)
	// 왜 시간초과가 나는지 생각해봄 -> 최대 문자열의 길이는 1천이니 매번 1000길이의 문자열과 비교하다보니  시간초과
	// 처음부터 비교하기보단 완성된 문자열을 규칙적로 빼주면서 비교하는게 속도가 빠름
	// 이문제를 풀면서 깨달은것 -> 문제에 기재된 범위나 조건을 꼼꼼하게 읽으면 풀이방법의 힌트가 될 수 있다.
	static String str1;
	static boolean chk=false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1= br.readLine();
		StringBuilder sb = new StringBuilder();
		sb.append(br.readLine());
		check(sb);
		System.out.println(chk ? 1 : 0);
	}
	static void check(StringBuilder sb) {
		if (chk==true) return;
		if (sb.length() == str1.length()) { //길이가 같아질경우 비교 후 리턴
			if(sb.toString().equals(str1)) {
				chk =true;
				return;
			}else {
				return;
			}
		}
		// 맨 뒷글자가 A일 경우 -> 문자열에 + A를 한 케이스 -> A를 뺴주고 다시 체크
		// 맨 뒷글자가 B일 경우 -> 문자열을 뒤집고 + B를 한 케이스 -> B를 뺴주고 문자열을 뒤집어서 체크
		if (sb.charAt(sb.length()-1)=='A') {
			check(sb.delete(sb.length()-1, sb.length()));
		}else {
			check(sb.delete(sb.length()-1,sb.length()).reverse());
		}
	}
}
