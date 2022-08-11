/*
1 자리 : 1 ~ 9 		(1 - 9개) + 9개
2 자리 : 10 ~ 99 		(10 - 189개) + 180개
3 자리 : 100 ~ 999		(190 - 2889개) + 2700개
4 자리 : 1000 ~ 9999	(2900 - 38889개) + 3600개
*/
package study.day0811;

import java.io.IOException;
import java.util.Scanner;

public class BOJ_1790_수이어쓰기2 {	// 메모리 : 12868kb	실행시간 : 112ms
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextInt();
		long k = sc.nextInt();
		long i = 2;
		long endPrev = 9;
		
		if(k < 10) {	// 만약 k가 1자리 수라면
			if(k <= N) System.out.println(k);	// k가 범위내에 있을 경우 k출력
			else System.out.println(-1);	// 범위 밖이면 -1 출력
			return;
		}
		while(true) {	// N이 몇 자리 수인지 파악하기
			endPrev += 9 * i * Math.pow(10, i - 1);
			if(k <= endPrev) {				
				endPrev -= 9 * i * Math.pow(10, i - 1);
				break;
			}
			i++;
		}
		
		k -= endPrev;
		long nowNum = (int) (((k - 1)/ i) + Math.pow(10, (i - 1)));	// k가 포함된 수 구하기
		k--;	// 자리수 계산을 위해서는 i자리 수 첫번 째 부터의 거리를 알아야 하기 때문에 -1 해준다
		
		if(nowNum > N) System.out.println(-1);	// k가 포함된 수가 N보다 크면 -1 출력
		else {
			System.out.println(String.valueOf(nowNum).charAt((int) (k % i)));	// k % i 번째 인덱스 출력
		}
		
	}
}
