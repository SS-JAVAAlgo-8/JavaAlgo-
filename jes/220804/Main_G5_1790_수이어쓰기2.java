package algostudy.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_1790_수이어쓰기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long tmp = K;
		long count = 9;	// 자릿수 별 숫자 개수
		long num = 1; 
		long length = 1; // 자릿수 별 숫자 길이
		int index = 0; 
		
		// tmp(K) > 각 자릿수의 숫자 개수(9, 90, 900...) * 자릿수별 숫자 길이(1, 2, 3...)
		while(tmp > count*length) {
			num += count; // 현재 자릿수 (10, 100, ...)
			tmp -= count * length; // 현재 자릿수까지 제거한 남은 숫자 길이
			length++;                                                                         
			count *= 10;
		}
		
		// K번째 수가 속한 수: 자릿수 + 이어진 숫자 길이 / 자릿수별 숫자 길이
		// ex) 20 23 일때 10 + (23-10*1)/2 = 16
		num += (tmp-1) / length;
		if(num > N) System.out.println(-1); // N보다 크면 -1
		else {
			index = (int)((tmp-1) % length); // K번째 수 찾기 위한 인덱스
			System.out.println(Long.toString(num).charAt(index));
		}
	}
}
