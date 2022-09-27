/*
	< 단순 구현 문제 >
	
	걸린 시간 : 30분
	
	0회전
	1회전
	2회전
	...
	N / 4 회전
	
	1. 문자열 입력받기
	2. 배열을 이용해 회전시키기
	3. 문자열 / 4 만큼 배열에서 뽑으면서 해시셋에 16진수 저장 (중복 제거)
	4. 16진수 10진수로 변환해 배열에 저장
	5. 배열 정렬 (내림차순 정렬 매우 복잡 ... 따라서 오름차순 정렬 후 뒤에서 k번째 뽑을 거임)
	6. 뒤에서 k번째 수 뽑아내기
*/

package study.day0908;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_5658_보물상자비밀번호 {	// 메모리 : 22384 kb		실행시간 : 126 ms
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/5658.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			HashSet<String> hSet = new HashSet<>();
			
			// 배열에 16진수 숫자 넣기
			String[] str = br.readLine().split("");
			
			// 단위 만들어놓기
			int cut = N / 4;
			
			// N/4 회전 시키면서 해시셋에 16진수 넣기
			for (int i = 0; i < cut; i++) {
				// 회전
				String temp = str[str.length - 1];
				for (int j = str.length - 1; j > 0; j--) {
					str[j] = str[j - 1];
				}
				str[0] = temp;
				
				// 미리 정해놓은 단위로 16진수 만들어서 해시셋에 저장 (중복 거르기)
				for (int j = 0; j <= str.length - cut; j += cut) {
					String hex = "";
					for (int k = j; k < j + cut; k++) {
						hex += str[k];
					}
					hSet.add(hex);
				}
			}
			// 해시셋에서 값 꺼내면서 10진수로 변환해 배열에 저장
			int[] arr = new int[N];
			int idx = 0;
			for (String hexString : hSet) {
				arr[idx++] = Integer.parseInt(hexString, 16);
			}
			
			// 10진수 저장해놓은 배열 정렬 후 뒤에서 k번째 수 뽑아내기
			Arrays.sort(arr);
			sb.append("#" + t + " " + arr[arr.length - K] + "\n");
		}
		System.out.println(sb);
	}
}
