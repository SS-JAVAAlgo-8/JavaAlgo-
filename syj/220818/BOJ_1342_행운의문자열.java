/*
 * 순열 (비트마스킹)
 * 
 * 1. 인접한 문자와 같은 문자일 경우 x
 * 2. 중복 제거 >>> i번 째 자리에 같은 알파벳이 등장하면 패쓰 ----> 비트마스킹
 * 3. 알파벳에 따라 위치를 저장해야하니까 char 사용!!!
 */

package study.day0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_1342_행운의문자열 {	// 메모리 : 11920 kb	실행시간 : 304 ms
	static char[] c;
	static boolean[] isSelected;
	static int ans = 0;
	static HashSet<String> hSet = new HashSet<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		c = br.readLine().toCharArray();
		isSelected = new boolean[c.length];
		perm(' ', 0);	// 처음에 빈 문자와 길이 0 전달	
		System.out.println(ans);
	}
	private static void perm(char ch, int cnt) {	// char는 아무것도 쓸 수 없다 ... 전부 매개변수로 넘겨줘야 함 (ch : 현재 알파벳 저장, cnt : char 길이 저장)
		if(cnt == c.length) {
			ans++;
			return;
		}
		int flag = 0;
		for (int i = 0; i < c.length; i++) {
			if(isSelected[i] || ch == c[i]) continue;	// 이미 사용한 원소 혹은 이전 문자와 같은 경우 컨티뉴
			if((flag & 1 << (c[i] - 'a')) != 0) continue;	// i번째 위치에 이미 같은 알파벳을 사용한 경우 중복이므로 컨티뉴
			flag |= 1 << (c[i] - 'a');	// 만약 i번째 해당 알파벳을 처음 사용하는 거라면 해당 위치 1로 바꿔주기
			isSelected[i] = true;
			perm(c[i], cnt + 1);
			isSelected[i] = false;
		}
	}
}
