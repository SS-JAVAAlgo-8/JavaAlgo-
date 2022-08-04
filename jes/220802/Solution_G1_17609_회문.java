package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G1_17609_회문 {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			String st = br.readLine();
			int start = 0;
			int end = st.length()-1;
			int cnt = 0;
			
			// 앞과 뒤 인덱스가 교차하기 전까지 반복
			while(start<end) {
				// 양쪽 문자가 같으면 안쪽으로 인덱스 이동
				if(st.charAt(start)==st.charAt(end)) {
					start++;
					end--;
				}
				// 양쪽 문자가 다를때 유사 회문 검사
				else {
					// 앞쪽 문자를 제외한 문자열
					String left = st.substring(start+1, end+1);
					// 뒤쪽 문자를 제외한 문자열
					String right = st.substring(start, end);
					// 유사 회문 검사가 하나라도 true일 때 유사회문이므로 1 
					if(pseudo(left, 0, left.length()-1) || pseudo(right, 0, right.length()-1)) {
						cnt = 1;
						break;
					}
					// 둘다 false일 때 회문도 유사회문도 아니므로 2
					else {
						cnt = 2;
						break;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	// 유사 회문 검사 메소드
	public static boolean pseudo(String st, int start, int end) {
		while(start<end) {
			if(st.charAt(start)==st.charAt(end)) {
				start++;
				end--;
			}
			else {
				return false;
			}
		}
		return true;
	}
}