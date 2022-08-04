package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 220803
public class Solution_S1_2002_추월 { // 11876kb 96ms

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int car = Integer.parseInt(br.readLine());
		List<String> start = new ArrayList<>();
		List<String> end = new ArrayList<>();
		for(int i=0; i<car; i++) start.add(br.readLine());
		for(int i=0; i<car; i++) end.add(br.readLine());

		int tmp = start.size();
		boolean check = true;
		while(check) {
			int i=0;
			// 들어간 차와 나온 차의 순서가 같지 않을 때 나온 차를 추월했다고 판단
			// 나온 차를 리스트에서 삭제하고 들어간 차 리스트에서도 해당 차를 삭제
			for(i=0; i<end.size(); i++) {
				if(start.get(i).equals(end.get(i)))
					continue;
				start.remove(start.indexOf(end.remove(i)));
				break;
			}
			if(i==end.size()) break;
		}
		// 삭제된 차의 개수를 구해 추월한 차가 몇대인지 출력
		System.out.println(tmp-end.size());
	}
}
