/*
풀이 방법 : 

- 투 포인터를 활용
- 뒤에서 한칸 앞이랑 같을 때 / 앞에서 한칸 뒤랑 같을 때
- 두 가지로 나눠서 회문 or 유사회문 찾기
*/
package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_17609_회문 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			char[] c = br.readLine().toCharArray();
			// 맨 앞과 뒤를 비교하면서 list.size/2 지점에서 만남
			int start = 0;	// 맨 앞에서 시작
			int end = c.length - 1; 	//맨 뒤에서 시작
			int cnt1 = 0;	//삭제한 문자 개수
			int cnt2 = 0;	//삭제한 문자 개수
			
			for (int i = 0; i < c.length/2; i++) {	// 회문 찾기
				if(c[start+i] == (c[end-i])) continue;	// 지금 문자가 같으면 다음 문자 비교
				if(c[start+i] != c[end-i-1]) {	// 지금 문자가 다를 때 뒤에서 그 앞에 문자와 같으면 (ex) ummus)
					cnt1 = 2;
					break;
				}
				end--;
				cnt1++;
			}
			
			end = c.length - 1; 	//맨 뒤에서 시작
			
			for (int i = 0; i < c.length/2; i++) {	// 회문 찾기
				if(c[start+i] == (c[end-i])) continue;	// 지금 문자가 같으면 다음 문자 비교
				if(c[start+i+1] != c[end-i]) {	// 지금 문자가 다를 때 뒤에서 그 앞에 문자와 같으면 (ex) ummus)
					cnt2 = 2;
					break;	
				}
				start++;
				cnt2++;
			}
			int cnt = Math.min(cnt1, cnt2);
			if(cnt == 0) sb.append(0 + "\n");
			else if(cnt == 1) sb.append(1 + "\n");
			else sb.append(2 + "\n");
		}
		System.out.println(sb);
	}
}
