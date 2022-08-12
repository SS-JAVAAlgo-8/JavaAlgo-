package BOJ;

import java.util.*;
import java.util.stream.Stream;
import java.lang.*;
import java.io.*;

class BOJ_1790 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		long[] counted = new long[10];
		int c = 1;
		int ten = 1;
		for (int i = 1; i < counted.length - 1; i++) {
			counted[i] = (long) (9 * c * ten * i);
			ten *= 10;
		}
		counted[9] = 1000000000;

		int cindex = 0;
		for (int i = 0; i < counted.length; i++) {
			if (counted[i] > k) {
				cindex = i;
				break;
			}
		}
//		System.out.println(Arrays.toString(counted));
//		System.out.println(cindex);
		for (int i = 0; i < cindex; i++) {
			k-=counted[i];
		}
//		System.out.println(k);
		
		ten =1;
		for (int i = 1; i < cindex; i++) {
			ten*=10;
		}
		ten = ten + ((k-1)/cindex) ;
//		System.out.println(ten);
		int[] result = Stream.of(String.valueOf(ten).split("")).mapToInt(Integer::parseInt).toArray();
//		System.out.println(Arrays.toString(result));
		System.out.println(result[(k-1)%cindex]);
	}
}