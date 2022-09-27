package algostudy.day0908;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 풀이 시작 시간: 22.09.14 22:30
 * 풀이 종료 시간: 22.09.14 23:10
 * 풀이 시간 : 40분 
 *
 * 목표: 주어진 수로 만들 수 있는 수 중 K번째로 큰 수를 10진수로 표현
 * 
 * 조건
 * 1. 각 변에 16진수 숫자(0~F)가 있고 한 번 돌릴때마다 시계방향으로 한칸씩 회전함
 * 2. 각 변에는 동일한 개수의 숫자가 있고 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타냄
 * 3. 회전하면서 동일한 수가 중복 생성되는 것 카운트 제외
 * 
 * 풀이 계획
 * 1. 주어진 숫자 개수만큼 회전하여 숫자 생성(중복 제외)
 * 2. 내림차순 정렬
 * 3. K번째로 큰 수 출력
 * 
 */


public class Solution_5658_보물상자비밀번호 { // 19816kb 122ms

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 보물상자 뚜껑 회전할 때 사용
			ArrayList<Character> treasure = new ArrayList<>();
			char[] ch = br.readLine().toCharArray();
			for(char c : ch) {
				treasure.add(c);
			}
			
			// 중복 제거하기 위해 사용
			Set<String> s = new TreeSet<>();
			
			// 보물상자 뚜껑을 회전하여 얻을 수 있는 모든 경우
			for(int i=0; i<N; i++) {
				int cnt = 0;
				// 4변
				for(int k=0; k<4; k++) {
					String tmp = "";
					// N/4씩 자른 16진수 구하기
					for(int j=cnt; j<cnt+N/4; j++) {
						tmp += treasure.get(j);
					}
					// treeset에 추가하여 중복 제거 및 오름차순 정렬
					s.add(tmp);
					cnt += N/4;
				}
				// 시계 방향으로 회전
				treasure.add(0, treasure.remove(treasure.size()-1));
			}
			
			// 오름차순으로 정렬된 배열을 ArrayList에 옮겨 내림차순 만들기
			List<Integer> res = new ArrayList<>();
			String[] arr = s.toArray(new String[0]);
			for(int i=0; i<arr.length; i++) {
				int num = Integer.parseInt(arr[i], 16);
				res.add(num);				
			}
			
			// 이미 오름차순으로 정렬되어 있으므로 자리를 거꾸로 바꾸면 내림차순
			Collections.reverse(res);
			sb.append("#").append(t).append(" ").append(res.get(K-1)).append("\n");
		}
		System.out.println(sb);
	}
}
