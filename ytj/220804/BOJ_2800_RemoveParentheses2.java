package study.day220804.problem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class BOJ_2800_RemoveParentheses2 {

	/*
	 *  완료 시간 :  ~ 20 : 27
	 *  
	 *  재설계  : 19:10 ~ 19 : 54  
	 *  재 구현  : 19 : 54 ~ 20 : 27  ( 1h 17m )
	 *  
	 *  1. 여는 괄호와 닫는 괄호의 위치를 쌍으로 같이 저장해야한다.
	 *  2. 괄호 쌍의 갯수 파악
	 *  3. isSelected 로 해당 위치 괄호(쌍)이 현재 고려됬는지 판단
	 *  4. 
	 *  답으로 출력될때는 처음 주어진 식 1개를 빼고 출력
	 * 
	*/
	static boolean[] isSelected;  // 괄호 지워짐 체크 
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();      // 입력받은 수식
		char[] arr = input.toCharArray();  // 괄호 위치의 인덱스 판별을 위한 char[]
		Stack<Integer> st = new Stack<>(); // 괄호를 넣고 뺄 스택
		
		//int idx[][] = new int[input.length()][2];	// 괄호 쌍의 idx를 저장할 2차원 배열
		
		ArrayList<int []> list =  new ArrayList<>();
		
		isSelected = new boolean [input.length()];   // 괄호가 선택 됬는지 됬는지 판단
		
		for (int i = 0; i < input.length(); i++) {
			if( input.charAt(i) == '(' ) {
				st.push(i);
			}
			else if( input.charAt(i) == ')' ) {
				//idx[i][0] = st.pop();
				//idx[i][1] = i;
				list.add(new int[] {st.pop(), i});				
			}
			
		}
		combi(list.size(), arr, list);
		
	}

	private static void combi(int count, char[] arr, ArrayList<int []> list) {
		if(count == 0) {
			boolean seletph = false;
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < arr.length; i++) {
				if(isSelected[i] == false) { 
					// 선택되지 않은 괄호 및 모든 숫자,수식을  append 한다. 즉, count만큼 재귀 하면서 선택된 괄호 빼고 저장
					sb.append(arr[i]);
				} 
				else { // 선택된 괄호가 하나이상 있을때
					seletph = true;
				}
			}
			if(seletph) { //모든 괄호가 있을때( 즉, input과 같을때 )를 제외하고 출력
				System.out.println(sb.toString());
			}
			return;
		}
		
		int[] idx = list.get(count-1);
		
		combi(count-1, arr, list);
		isSelected[idx[0]] = true;
		isSelected[idx[1]] = true;
//		isSelected[idx[count][0]] = true;
//		isSelected[idx[count][1]] = true;
		
		combi(count-1, arr, list);
		isSelected[idx[0]] = false;
		isSelected[idx[1]] = false;
//		isSelected[idx[count][0]] = true;
//		isSelected[idx[count][1]] = true;
		
		// (1+(2*(3+4)-(5+6)))
	}


}
