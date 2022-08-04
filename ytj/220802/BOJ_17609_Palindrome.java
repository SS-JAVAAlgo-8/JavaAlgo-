package study.day220804.problem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ_17609_Palindrome {
	/*
	 * 회문 조건 
	 * 1. 문자열 길이 짝수 
	 * 2. 문자열의 첫인덱스의 값과 마지막 인덱스값의 비교를 시작으로 
	 *    문자열 길이/2 만큼 가까워지면서 반복했을때 불일치가 없어야한다.
	 * 
	 * 유사 회문 조건 
	 * 1. 문자열 길이 홀수 
	 * 2. 문자열을 이루는 문자들의 중복 갯수를 체크 하였을때, 한 종류만 홀수이고 나머지는 전부 짝수	여야한다. 
	 * 3. 1개인 문자를 제외하고 회문 조건을 주었을 떄 만족해야한다.
	 * 
	 * 나머진 일반 문자열
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("data/pali.txt"));
		Scanner sc = new Scanner(System.in);
		
		int TC = Integer.parseInt(sc.nextLine());
		
		for (int t = 1; t <= TC; t++) {
			StringBuffer str = new StringBuffer();
			str.append(sc.nextLine());

			System.out.println("#" + t + " " + pali(str));
		}
		
	}

	private static int pali(StringBuffer st) {
		
		if(st.length()%2 == 0) { // 회문은 반드시 짝수여야 하므로 문자열 갯수가 홀수면 판단하지 않는다.
			
			StringBuffer sb = new StringBuffer(st);
			
			int count = 0;
			for(int i = 0; i < sb.length()/2; i++) {   // 문자열의 처음과 끝 부터 가까워지면서 비교
				
				if(sb.charAt(i) == sb.charAt(sb.length() - i - 1)) // 비교해서 값이 일치한다면 count를 늘려준다
					count++;
				else if(sb.charAt(i) != sb.charAt(sb.length() - i - 1)) // 회문은 모두 일치해야 하므로 불일치 시 회문 X
					break;
			}
			
			if(count == st.length()/2)  // count 갯수가 문자열길이의 반이면 회문 이라고 할 수 있으므로 0을 반환
				return 0;
		}
		
		else if(st.length()%2 == 1) { // 유사 회문 또한 문자열의 길이가 반드시 홀수여야 한다.
			
			StringBuffer sb = new StringBuffer(st);
			HashMap<Character, Integer> comp = new HashMap<Character, Integer>(); 
			// 문자열을 이루는 각 문자와 그 갯수를 한 쌍으로 담기 위해 Map을 사용 ( key값은 문자, value는 문자의 갯수 )
			comp.put(sb.charAt(0), 1);
			// 문자열의 첫 문자는 겹치지 않으므로 담고 시작
			int idx = 0;
			for(int i = 1; i < sb.length(); i++) {
				if(comp.containsKey(sb.charAt(i))) { 
					// 문자열의 첫번째 문자는 이미 담았으므로, 2번째 부터 문자열 길이까지 문자 종류가 겹치는지 비교
					comp.replace(sb.charAt(i), comp.get(sb.charAt(i)) + 1 );
					// 겹치는 문자라면 value 값을 1 늘려준다
				}
				else {
					comp.putIfAbsent(sb.charAt(i), 1);	
					// 겹치지 않은 문자라면 Map에 추가 해준다.
				}
			}
			
			Character keys = 0;
			for(Character key : comp.keySet()) {
				if(comp.get(key) % 2 == 1) { 
					// 문자열을 모두 판단하였을때, 유사 회문이라면 Map의 value 값들 중 홀수인 key가 하나일 것이므로 그 key값을 담는다 
					keys = key;
				}
				
			}
			
			for(int i = 0; i < sb.length()/2; i++) {
				
				if(sb.charAt(i) != sb.charAt(sb.length() - i - 1)) {
					// 회문 판단처럼 양끝에서 비교하면서 오다가 다른 지점이 생겼을때, 다른 두 인덱스 중 Map에서 추출한 key와 같은 문자라면
					// delete 하여 준다.
					if(sb.charAt(i)==keys) { 
						sb.deleteCharAt(i);
						break;
					}
					else if(sb.charAt(sb.length() - i - 1)==keys) {
						sb.deleteCharAt(sb.length() - i - 1);
						break;
					}
				}
				
			}
			
			if(pali(sb)==0) { // 지운 후 문자열을  회문판단 하였을때, 회문이 맞다면 유사회문으므로 1을 반환 
				return 1;
			}
			
		}
		
		return 2;
		// 주어진 Test case 말고 몇개를 더 추가해 보았을때, 답은 맞지만 백준에서 돌리면 시간초과 발생
        /*10
		* abba
		* summuus
		* xabba
		* xabbay
		* comcom
		* comwwmoc
		* comwwtmoc
		* axaaxaa
		* abcddadca
		* aabcbcaa
		*/
	}

}
