package study.day220804.problem;

import java.util.Arrays;
import java.util.Scanner;


public class BOJ_2800_RemoveParentheses {

	/*
	 *  시작 시간 : 16:30 ~ 
	 *  
	 *  풀이 설계 : 16:30 ~ 17:01  
	 *  구현 시작 : 17:01 
	 *   포   기  : 19:00   ( 2h 30m )
	 *  
	 *  n개의 괄호를 지우는 모든 방법  2^n 개 (부분 집합)
	 *   = n개의 괄호 중 1~n개 의 괄호를 지우는 모든 경우의 수
	 *   = 순열의 합
	 *  
	 *  답으로 출력될때는 처음 주어진 식 1개를 빼고 출력
	 *  
	 * 
	*/
	static boolean[] isSelected;  // 괄호 지워짐 체크 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();  // 입력받은 수식
		StringBuilder ans = new StringBuilder(input); // 괄호 제거 작업을 할 스트링빌더
		String str = input.replaceAll("[0-9,+,*,-,/,]","");  // 숫자와 수식을 지워서 괄호만 남기기
		
		
		int parentNum = str.length() / 2 ;   // 괄호 (쌍) 갯수
	
		isSelected = new boolean[parentNum];
		remove(ans, parentNum);
		
		System.out.println( ans.toString());
		
		
	}

	private static void remove(StringBuilder ans, int parentNum) {
		
		if(parentNum == 0) return;
		
		System.out.println(ans.toString());
		
		for(int i = 0; i < parentNum; i++) {
			find(ans, i);
			
			find(ans, parentNum); // num 번째 괄호 쌍 삭제 
			remove(ans, parentNum - 1);
		}

		
	}

	private static void find(StringBuilder ans, int parentNum) {
		if(parentNum==0) return;
		int openNum = 0;
		int closeNum = 0;
		int openidx = 0;
		int closeidx = 0;
		
		for(int i = 0; i < ans.length(); i++) {
			if( ans.charAt(i) == '(' )
				openNum++;
			else if( ans.charAt(i) == ')' )
				closeNum++;
			
			if(openidx == 0 && openNum == parentNum ) {
				openidx = i;
			}
			
			if(closeidx == 0 && closeNum == parentNum) {
				closeidx = i;
				ans.deleteCharAt(closeidx);
				ans.deleteCharAt(openidx);
				return;
			}
				
		}
		
	}

}
