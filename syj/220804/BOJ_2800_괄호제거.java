/*
	1. ArrayList에 수식 저장
	2. 스택을 이용해 괄호 쌍 인덱스 map에 저장  -> key값은 따로 배열에 저장
		map - { 0 : 6, 3 : 5, ... }
		arr - [0, 3, ...]
	3. 부분집합으로 삭제 - 1~n -> 중복체크 (겹괄호가 있을 경우 중복이 생김)
	4. 사전 순으로 정렬 후 출력

 */
package study.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class BOJ_2800_괄호제거 {
	static int[] num;
	static int[] arr;
	static ArrayList<String> list;
	static HashMap<Integer, Integer> map;
	static ArrayList<String> result = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		list = new ArrayList<>();
		map = new HashMap<>();
		String[] temp = br.readLine().split("");
		// 1. ArrayList에 수식 저장
		for (int i = 0; i < temp.length; i++) {
			list.add(temp[i]);
		}
		// 2. "("가 나오면 stack에 해당 인덱스 push & map에 key등록 , ")" 나오면 pop해서 나온 인덱스의 value를 해당 인덱스로 변경
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals("(")) {
				stack.push(i);
				map.put(i, 0);
			}
			if(list.get(i).equals(")")) {
				map.put(stack.pop(), i);
			}
		}
		// 3. key값만 arr에 따로 저장 (조합에 사용하기 위해)
		arr = new int[map.size()];
		int idx = 0;
		for (int i : map.keySet()) {
			arr[idx++] = i;
		}
		// 4. 부분집합으로 괄호 삭제
		for (int i = 1; i <= arr.length; i++) {
			num = new int[i];	// 삭제할 인덱스 담을 배열
			subset(0, arr.length - 1);
		}
		// 5. 사전 순으로 정렬 후 출력
		Collections.sort(result);
		for (int i = 0; i < result.size(); i++) {			
			sb.append(result.get(i) + "\n");
		}
		System.out.println(sb);
	}

	private static void subset(int r, int start) {
		if(r == num.length) {	// 뽑힌 인덱스 괄호 지우기
			String str = "";
			outline : for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < num.length; j++) {
					if(i == num[j] || i == map.get(num[j])) continue outline;
				}
				str += list.get(i);
			}
			// 중복 체크 -> 겹괄호가 있는 경우 결과가 겹칠 수 있음
			for (int i = 0; i < result.size(); i++) {
				if(result.get(i).equals(str)) return;
			}
			result.add(str);
			return;
		}
		if(start < 0) return;
		num[r] = arr[start];
		subset(r + 1, start - 1);
		subset(r, start - 1);
	}
}
