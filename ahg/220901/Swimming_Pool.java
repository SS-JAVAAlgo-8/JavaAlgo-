package com.ssafy.recur.swea.sol1952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swimming_Pool {
	// 최소값 구하는 문제
	// 즉,1일 최대 365개 ,1달 최대 12개 ,3달 최대 4개,1년 최대 1개
	// 1일을 삿을떄는 반드시 한달 단위로 사야함 (아니면 남는 돈을 커버할 방법이 없음)
	// 부분집합으로 해결 할 수 있을거 같음 (수영장에 나가는 일 수가 존재하는 매 달마다 일,달,3달,1년 순으로 살 수 있기에)
	// 1년을 제외한 3가지 경우의 수를 최대 12번을 돌리니 시간 복잡도는 3^12 = 531,441번
	static int[] price; // 인덱스 순서대로 1일,1달,3달,1년 가격
	static int[] month; // 각 월별 수영장을 가는 횟수를 기록할 배열
	static int min_value; // 최소값을 저장할 변수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			price = new int[4];
			month = new int[12];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 12; j++) {
				month[j] = Integer.parseInt(st.nextToken());
			}
			min_value = price[3]; // 최대로 나올 수 있는 가격은 1년이용권이기 때문에 최소값을 초기값을 1년이용권 가격으로 설정해줌
			subset(0, 0);
			sb.append("#" + tc + " " + min_value).append("\n");
		}
		System.out.println(sb);
	}

	

	public static void subset(int idx, int total) { // idx : 각 월을 순차적으로 탐색할 변수 , total : 탐색하면서 합산한 가격을 담을 변수
		if (idx >= 12) { // 12개월치의 계산이 끝나면
			min_value = Math.min(total, min_value); // 최소값 계산
			return;
		}
		if (total > min_value) { // 만약 현재 루트가 이미 최소값보다 커질경우 더 진행할 가치가 없으므로 리턴 
			return;
		}
		if (month[idx] == 0) { // 현재 달에 수영장에 가는 횟수가 적으면 다음 달로 넘어감
			subset(idx + 1, total);
			return;
		}
		subset(idx + 1, total + month[idx] * price[0]); // 1일 이용권을 살 경우 ( 살 경우는 반드시 한달 치를 사야한다, 아니면 다른 방법으로 남은 일자를 매꿀 수 없기떄문 )
		subset(idx + 1, total + price[1]); // 1달 이용권을 살 경우
		subset(idx + 3, total + price[2]); // 3달 이용권을 살 경우
	}
}
