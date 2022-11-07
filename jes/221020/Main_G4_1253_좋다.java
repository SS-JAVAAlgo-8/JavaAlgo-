package algostudy.day1020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 	목표: 좋은 수의 개수 찾기
 * 
 *	조건
 * 	1. N개의 수 중 어떤 수가 다른 두 수의 합으로 나타낼 수 있다 -> 좋다
 *  2. 수의 위치가 다르면 값이 같아도 다른 수
 *  
 *  풀이 계획
 *  - 투포인터
 *  
 * 	풀이 방법
 *  1. 초기 포인터는 각각 가장 왼쪽과 오른쪽에 위치
 *  2. 위치를 한칸씩 이동하면서 좋은 수인지 판단
 *  
 */
public class Main_G4_1253_좋다 { // 12840kb 152ms

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());	
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		int cnt = 0;
		for(int i=0; i<N; i++) {
			int l = 0;
			int r = N-1;
			
			while(true) {
				// 포인터가 현재 위치를 가리키는 경우 이동
				if(l == i) l++;
				else if(r == i) r--;
				
				// left와 right가 같은 곳을 가리키거나 자리가 스위치된 경우
				if(l >= r) break;
				
				// left + right 값이 num보다 크면 right-- 작으면 left++ 같으면 cnt++
				if(num[l]+num[r] == num[i]) {
					cnt++;
					break;
				}
				else if(num[l]+num[r] > num[i]) r--;
				else if(num[l]+num[r] < num[i]) l++;
			}
		}
		
		System.out.println(cnt);
	}
}
