package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_14891_톱니바퀴 {
	static List<Integer> numList;
	static Map<Integer, List<Integer>> map = new HashMap<>();
	
	//  회전
	private static void rotate(int listNum, int rotateNum) {
		// 1번 톱니를 돌리게 되면 다음과 같이 체크가 필요하다. 
		// 1번을 돌려서 2번이 돌려지는지 , 그 영향으로 3번이 돌아가는지 , 그 영향으로 4번이 돌아가는지
		if (listNum == 1) {
			if (map.get(1).get(2) != map.get(2).get(6)) {
				if (map.get(2).get(2) != map.get(3).get(6)) {
					if (map.get(3).get(2) != map.get(4).get(6)) {
						Collections.rotate(map.get(4), -rotateNum);
					}
					Collections.rotate(map.get(3), rotateNum);
				}
				Collections.rotate(map.get(2), -rotateNum);
			}
			Collections.rotate(map.get(1), rotateNum);
		}
		// 2번 톱니를 돌리게 되면 다음과 같이 체크가 필요하다. 
		// 2번을 돌려서 1번이 돌려지는지
		// 2번을 돌려서 3번이 돌아가는지 그 영향으로 4번이 돌아가는지
		if (listNum == 2) {
			if (map.get(2).get(6) != map.get(1).get(2)) {
				Collections.rotate(map.get(1), -rotateNum);

			}

			if (map.get(2).get(2) != map.get(3).get(6)) {
				if (map.get(3).get(2) != map.get(4).get(6)) {
					Collections.rotate(map.get(4), rotateNum);
				}
				Collections.rotate(map.get(3), -rotateNum);
			}
			Collections.rotate(map.get(2), rotateNum);
		}
		// 3번 톱니를 돌리게 되면 다음과 같이 체크가 필요하다. 
		// 3번을 돌려서 4번이 돌려지는지
		// 3번을 돌려서 2번이 돌아가는지 그 영향으로 1번이 돌아가는지
		if (listNum == 3) {
			if (map.get(3).get(2) != map.get(4).get(6)) {
				Collections.rotate(map.get(4), -rotateNum);
			}

			if (map.get(2).get(2) != map.get(3).get(6)) {
				if (map.get(1).get(2) != map.get(2).get(6)) {
					Collections.rotate(map.get(1), rotateNum);
				}
				Collections.rotate(map.get(2), -rotateNum);
			}
			Collections.rotate(map.get(3), rotateNum);
		}
		// 4번 톱니를 돌리게 되면 다음과 같이 체크가 필요하다. 
		// 4번을 돌려서 3번이 돌려지는지 3번을 돌려서 2번이 돌아가는지 그 영향으로 1번이 돌아가는지
		if (listNum == 4) {
			if (map.get(4).get(6) != map.get(3).get(2)) {
				if (map.get(3).get(6) != map.get(2).get(2)) {
					if (map.get(2).get(6) != map.get(1).get(2)) {
						Collections.rotate(map.get(1), -rotateNum);
					}
					Collections.rotate(map.get(2), rotateNum);
				}
				Collections.rotate(map.get(3), -rotateNum);
			}
			Collections.rotate(map.get(4), rotateNum);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		for (int i = 1; i < 5; i++) {
			input = br.readLine().split("");
			numList = new ArrayList<>();
			for (String string : input) {
				numList.add(Integer.parseInt(string));
			}
			map.put(i, numList);
		}

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			input = br.readLine().split(" ");
			rotate(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}

		int result = 0;
		int[] score = { 0, 1, 2, 4, 8 };
		for (int i = 1; i < 5; i++) {
			if (map.get(i).get(0) == 1) {
				result += score[i];
			}
		}
		System.out.println(result);
	}
}
