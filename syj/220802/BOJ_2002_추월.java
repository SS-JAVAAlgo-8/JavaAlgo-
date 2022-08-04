// 메모리 : 13396kb
// 실행시간 : 144ms
/*
풀이 방법 :

- ArrayList 활용
- 앞에서부터 탐색하면서 들어온 순서와 나가는 순서가 다른 차를 remove
- remove 한 위치부터 다시 탐색
*/
package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2002_추월 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> in = new ArrayList<>();
		ArrayList<String> out = new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {	// 들어가는 차 목록
			in.add(br.readLine());
		}
		for (int i = 0; i < n; i++) {	// 나오는 차 목록
			out.add(br.readLine());
		}
		int i = 0;	// 맨 앞부터 비교
		int cnt = 0;	// 역전한 차 대수
		while (i < out.size()) {
			if(in.get(i).equals(out.get(i))) {	// 같은 차면 다음 비교
				i++; 
				continue;	
			}
			// 다른 차일 경우 out의 차와 똑같은 차를 in에서 찾아서 둘다 제거한뒤 비교
			for (int j = i; j < in.size(); j++) {
				if(in.get(j).equals(out.get(i))) {
					in.remove(j);
					out.remove(i);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		
	}
}
