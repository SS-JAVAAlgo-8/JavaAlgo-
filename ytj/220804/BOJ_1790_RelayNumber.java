package study.day220804.problem;

import java.util.Scanner;

public class BOJ_1790_RelayNumber {
	/*	N = 100,000,000   1억
	 *  k = 1,000,000,000 10억
	 * 
	 * 
	 *  N 
	 *  1 ~ 9 : 1 자리씩 ... 					1*9 = 9 자리
	 *  10 ~ 99 : 2 자리씩  ... 				2*90 = 180 자리 
	 *  100 ~ 999 : 3자리씩 ...				3*900 = 2,700 자리
	 *  1,000 ~ 9,999 : 4자리씩  ... 			4*9,000 = 36,000 자리 
	 *  10,000 ~ 99,999 : 5자리씩 ... 			5*90,000 = 450,000 자리
	 *  100,000 ~ 999,999 : 6자리씩 ... 		6*900,000 = 5,400,000 자리
	 *  1,000,000 ~ 9,999,999 : 7자리씩 ... 	7*9,000,000 = 63,000,000 자리
	 *  ==================================================================
	 *  10,000,000 ~ 99,999,999 : 8자리씩 ...	8*90,000,000 = 720,000,000 자리
	 *  100,000,000             : 9자리		MAX_K = 788,888,898
	 * 
	 *  1. N 까지 수를 이었을 때의 길이가, K보다 큰지 판단
	 *  2. 
	*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int Nlen = 0;
		int ans = 0;
		
		if(N <= 9) {
			for(int i = 1; i <= N; i++) {
				Nlen++;	
			}
			if(Nlen >= K)
				ans = Nlen;
			else
				ans = -1;
		}
		
		else if(N > 9 && N <= 99) {
			Nlen = 9;
			for(int i = 10; i <= N; i++) {
				Nlen += 2;	
			}
		}
		else if(N > 99 && N <= 999) {
			
		}
		else if(N > 999 && N <= 9_999) {
			
		}
		else if(N > 9_999 && N <= 99_999) {
			
		}
		else if(N > 99_999 && N <= 999_999) {
			
		}
		else if(N > 999_999 && N <= 9_999_999) {
			
		}
		else if(N > 9_999_999 && N <= 99_999_999) {
			
		}
		else {
			
		}
		
		
	}

}