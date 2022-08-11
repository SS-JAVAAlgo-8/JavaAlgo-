package study.day220804.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1790_RelayNumber2 {
	/*	 그냥 String 담아서 k번째 읽기
	 * 
	 *  1. N 까지 수를 이었을 때의 길이가, K보다 큰지 판단
	 *  2. 
	*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		StringBuilder relay = new StringBuilder();
		
		for(int i = 1; i <= K; i++) {
			relay.append(i);
		}
		
		if(relay.length() < K )
			System.out.println(-1);
		else
			System.out.println(relay.charAt(K-1));
			
	}

}
