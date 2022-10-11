package com.ssafy.recur.BOJ.sol14890;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 가로 세로의 길을 지나갈 수 있음 - > 양쪽으로 두번 검사
	// 낮은칸이 두 칸이 연속될 경우 다리를 놓을 수 있음
    // 검사하면서 위치가 다르지 않을경우 이어지는 숫자의 갯수 카운팅
	// 검사하다가 위치가 다를경우 더 높은지 낮은지 확인 훅
	// 더 높을경우 해당 위치에 사다리 놓기 더 낮을경우에는 카운팅 초기화
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 맵의 가로,세로 길이
			int L = Integer.parseInt(st.nextToken()); // 받침대의 길이
			map = new int[N][N];
			boolean[] result = new boolean[N]; // 가로 체크를 할 때 해당 길이 지나갈 수 있는 길인지 체크할 배열
			boolean[] result2 = new boolean[N]; // 세로 체크를 할 때 해당 길이 지나갈 수 있는 길인지 체크할 배열

            // 초기값 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
				}
			}
            // 가로 체크
			for (int i = 0; i < N; i++) {
				int check = 0; // 같은 높이가 L칸만큼 있는지 확인할 변수
				int temp = 0; // 이전 높이를 저장하고있을 변수
				boolean[] runway = new boolean[N]; // 받침대가 놓였다면 놓아진 위치를 체크 할 배열
				outer: for (int j = 0; j < N; j++) {
					if (j == 0) { // 인덱스가 0 이면 그 값을 저장 후, 카운트를 올려주고 아닐경우 이전값을 저장
						temp = map[i][j];
						check++;
						continue;
					} else {
						temp = map[i][j - 1];
					}
					if (result[i] == true) {
						continue;
					}
					if (map[i][j] > temp + 1 || map[i][j] < temp - 1) { // 만약 현재 칸이 이전칸 +1 보다 크거나 이전칸-1 보다 작을 경우 받침대를 못만들기떄문에 해당번쨰를 true 체크 후 Continue
						result[i] = true;
						continue;
					}
					if (map[i][j] == temp + 1 && check >= L) { // 만약 현재 칸이 temp보다 한 칸 높은데 같은높이의 칸이 L칸 이상 연속되었을 경우
						int k = j - 1;
						int c = 0;
						while (c != L) { // 현재 자리부터 L길이만큼 뒤쪽으로 검사
							if (runway[k] == true) {  // 만약 이미 받침대가 놓여져 있으면
								result[i] = true; // 불가능 표시 하고
								continue outer; // 다음칸 검색
							}
							k--;
							c++;
						}
						c = 0;
						k = j - 1;
						while (c != L) { // 만약 위의 while문에서 걸리지 않았다면 문제없이 받침대를 놓을 수 있다는 것이니 runway를 true체크해주면서 받침대를 놓은 표시를 해줌
							runway[k] = true;
							k--;
							c++;
						}
						check = 1; // 받침대를 놓았으면 다시 검사하기 위해 check를 1로 초기화
					} else if (temp == map[i][j]) { // 이전 높이와 현재 높이가 같을경우 check 를 +1 해줌
						check++;
					} else if (map[i][j] == temp - 1 && j + L - 1 < N) { // 만약 현재 칸이 이전칸보다 한칸 작고, 현재위치에서 받침대를 놓을 수 있는 길이가 남아 있다면
						for (int k = j; k < j + L; k++) { // 해당 공간이 전부 같은 높이인지 체크 후 아닐경우 다음칸 검사
							if (map[i][k] != temp - 1) {
								result[i] = true;
								continue outer;
							}
						}
						for (int k = j; k < j + L; k++) { // 해당 공간에 받침대를 놓기에 적합한 공간이면 runway를 true 체크 해주면서 받침대를 놓은 표시
							runway[k] = true;
						}
					} else { // 위의 모든 경우의 수에 해당되지 않을 경우 해당 칸 true체크
						result[i] = true;
					}
				}
			}


            // 세로체크
            // 가로와 같은 로직
			for (int j = 0; j < N; j++) {
				int check = 0;
				int temp = 0;
				boolean[] runway = new boolean[N];
				outer2: for (int i = 0; i < N; i++) {
					if (i == 0) {
						temp = map[i][j];
						check++;
						continue;
					} else {
						temp = map[i - 1][j];
					}
					if (result2[j] == true) {
						continue;
					}
					if (map[i][j] > temp + 1 || map[i][j] < temp - 1) {
						result2[j] = true;
						continue;
					}
					if (map[i][j] == temp + 1 && check >= L) {
						int k = i - 1;
						int c = 0;
						while (c != L) {
							if (runway[k] == true) {
								result2[j] = true;
								continue outer2;
							}
							k--;
							c++;
						}
						k = i - 1;
						c = 0;
						while (c != L) {
							runway[k] = true;
							k--;
							c++;
						}
						check = 1;
					} else if (temp == map[i][j]) {
						check++;
					} else if (map[i][j] == temp - 1 && i + L - 1 < N) {
						for (int k = i; k < i + L; k++) {
							if (map[k][j] != temp - 1) {
								result2[j] = true;
								continue outer2;
							}
						}
						for (int k = i; k < i + L; k++) {
							runway[k] = true;
						}
					} else {
						result2[j] = true;
					}
				}
			}
            // 결과값 카운트 후 출력
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (!result[i])
					cnt++;
				if (!result2[i])
					cnt++;
			}
			System.out.println("#" + tc +" " +cnt);
		}
	}
}
