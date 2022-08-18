/*
1. 숫자 개수 : N /2 + 1     최대 괄호 개수 : 숫자 개수 / 2
2. 괄호 0개 ~ 숫자개수 /2 ----> 부분집합으로 모든 괄호 개수에 대해 최대값 구하기
3. 먼저 계산할 인덱스를 조합으로 저장
4. 원본 수식을 복사한 list에서 해당 인덱스 부분을 계산해서 저장
5. 복사한 list 왼쪽부터 계산

3 ~ 5를 괄호 개수마다 반복하며 최대값 저장
*/
package study.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_16637_괄호추가하기2 {	// 메모리 : 12028 kb	실행시간 : 88 ms
	static List<String> list, list2;
	static int maxNum = Integer.MIN_VALUE;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int n = N / 2 + 1;	// 숫자 개수
		String[] temp = br.readLine().split("");		
		for (int i = 0; i < N; i++) {
			list.add(temp[i]);
		}
		for (int i = 0; i <= (n / 2); i++) {	// 괄호 개수만큼 부분집합
			num = new int[i];
			bracket(0, 0);
		}
		System.out.println(maxNum);
	}
	private static void bracket(int r, int start) {
		if(r == num.length) {
			list2 = new ArrayList<>(list);
			int n;
			for (int j = 0; j < num.length; j++) {
				switch (list2.get(num[j] + 1)) {
				case "*":
					n = Integer.parseInt(list2.get(num[j])) * Integer.parseInt(list2.get(num[j] + 2));
					for (int i = 0; i < 3; i++) {					
						list2.remove(num[j]);
					}
					list2.add(num[j], Integer.toString(n));
					break;
				case "+":
					n = Integer.parseInt(list2.get(num[j])) + Integer.parseInt(list2.get(num[j] + 2));
					for (int i = 0; i < 3; i++) {					
						list2.remove(num[j]);
					}
					list2.add(num[j], Integer.toString(n));
					break;
				case "-":
					n = Integer.parseInt(list2.get(num[j])) - Integer.parseInt(list2.get(num[j] + 2));
					for (int i = 0; i < 3; i++) {					
						list2.remove(num[j]);
					}
					list2.add(num[j], Integer.toString(n));
					break;
				}
			}
			Calculation();
			return;
		}
		if(start >= list.size() - (2 * num.length)) return;
		num[r] = start;
		bracket(r + 1, start + 2);
		bracket(r, start + 2);
	}
	private static void Calculation() {	// 최종 수식 계산 결과가 최대값일 때만 저장
		int n;
		while(list2.size() >= 3) {
			switch (list2.get(1)) {
			case "*":
				n = Integer.parseInt(list2.get(0)) * Integer.parseInt(list2.get(2));
				for (int i = 0; i < 3; i++) {					
					list2.remove(0);
				}
				list2.add(0, Integer.toString(n));
				break;
			case "+":
				n = Integer.parseInt(list2.get(0)) + Integer.parseInt(list2.get(2));
				for (int i = 0; i < 3; i++) {					
					list2.remove(0);
				}
				list2.add(0, Integer.toString(n));
				break;
			case "-":
				n = Integer.parseInt(list2.get(0)) - Integer.parseInt(list2.get(2));
				for (int i = 0; i < 3; i++) {					
					list2.remove(0);
				}
				list2.add(0, Integer.toString(n));
				break;
			}
		}
		maxNum = Math.max(maxNum, Integer.parseInt(list2.get(0)));
	}
	
}
