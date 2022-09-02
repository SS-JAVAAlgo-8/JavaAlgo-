package algostudy.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// a: 97 ~ z: 122
public class Main_S1_1342_행운의문자열 { // 13624kb 208ms

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] ch = br.readLine().toCharArray();
		int[] alp = new int[ch.length];
		for(int i=0; i<ch.length; i++) {
			alp[i] = (int)ch[i]-'a';
		}
		
		int res = 0;
		// 배열을 오름차순 정렬 -> 가장 작은 순열 한번 처리
		Arrays.sort(alp);
		do {
			boolean is = false;
			for(int i=0; i<alp.length-1; i++) {
				// 인접한 문자가 하나라도 같은 경우 카운트 하지 않음
				if(alp[i]==alp[i+1]) {
					is = true; 
					break;
				}
			}
			if(!is) { // 인접한 문자가 모두 다른 경우 카운트
				res++;
			}
		}while(np(alp));
		
		System.out.println(res);
	}
	
	// Next Permutation
	// 다음 순열 존재하면 true, 아니면 false
	public static boolean np(int[] numbers) {
		int N = numbers.length;
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
	
		// 다음 순열 만들 수 없을 때 = 이미 가장 큰 순열
		if(i==0) return false;
		
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) --j;
		swap(numbers, i-1, j);
		
		int k = N-1;
		while(i<k) swap(numbers, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
}
