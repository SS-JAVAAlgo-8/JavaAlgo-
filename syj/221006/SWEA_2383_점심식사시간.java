/*
중복순열 + 빡구현

걸린시간 : 2일

1. 사람 객체 (계단까지 거리, 계단 번호, 계단 길이, 기다림 여부)
2. 계단 위치 2개 저장
3. 사람마다 계단 정하기 (중복순열 0 or 1)
4. 계단 내려보내기
- 먼저 내려가는 중인 사람 계단 길이 1씩 줄이기
- 계단 길이가 0인 사람(다 내려온 사람) 카운트에서 빼주기
- 이제 막 도착한 사람 1분 대기
- 이미 기다리고 있던 사람은 바로 내려보내기
- 계단 꽉차서 기다려야 되는 사람 기다림 여부 체크해주기

*/
package study.day1006;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2383_점심식사시간4 {	// 메모리 : 31,732 kb		실행시간 : 130 ms
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int x1, x2, y1, y2, cnt, N, ans;
	static int[][] map;
	static Person[] person;
	
	public static class Person implements Comparable<Person> {
		int r;
		int c;
		int time;	// 계단까지 걸린 시간
		int num;	// 계단 번호
		int len;	// 계단 길이
		boolean wait;
		public Person(int r, int c, int time, int num, int len) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.num = num;
			this.len = len;
			this.wait = false;
		}
		
		public int getTime() {
			return time;
		}
		
		public void setTime(int x, int y) {
			this.time = Math.abs(this.r - x) + Math.abs(this.c - y);
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.time, o.getTime());
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/2383.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			x1 = -1;
			cnt = 0;
			// 맵 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) cnt++;
					if(map[i][j] > 1) {	// 계단 내려가는 시간 저장
						if(x1 == -1) {
							x1 = i; y1 = j;
						} else {
							x2 = i; y2 = j;
						}
					}
				}
			}
			// 계단 도착여부, pq 초기화
			person = new Person[cnt];
			
			int num = 0;	// 사람 번호
			for (int i = 0; i < N; i++) {	// 사람이 등장하면 두 계단 대기 큐로 이동해서 대기시키기
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						person[num++] = new Person(i, j, 0, 0, 0);
					}
				}
			}
			select(0);	// 계단 선택
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	private static void select(int r) {
		if(r == cnt) {
			goDown();
			return;
		}
		for (int i = 0; i <= 1; i++) {
			person[r].num = i;
			select(r + 1);
		}
	}

	private static void goDown() {	// 계단 내려가기
		int p1 = 0;
		int p2 = 0;
		
		for (int i = 0; i < cnt; i++) {
			if(person[i].num == 0) p1++;
			else p2++;
		}
		Person[] ps1 = new Person[p1];
		Person[] ps2 = new Person[p2];
		for (int i = 0, idx1 = 0, idx2 = 0; i < cnt; i++) {
			if(person[i].num == 0) {
				ps1[idx1] = new Person(person[i].r, person[i].c, 0, person[i].num, map[x1][y1] + 1);
				ps1[idx1++].setTime(x1, y1);
			}
			else {
				ps2[idx2] = new Person(person[i].r, person[i].c, 0, person[i].num, map[x2][y2] + 1);
				ps2[idx2++].setTime(x2, y2);
			}
		}
		Arrays.sort(ps1);
		Arrays.sort(ps2);
		int sum = 0;
		int time = 0;
		if(p1 == 0) {			
			time = ps2[0].time;	// 시작 시간
		} else if(p2 == 0) {
			time = ps1[0].time;	// 시작 시간
		} else {
			time = Math.min(ps1[0].time, ps2[0].time);	// 시작 시간
		}
		int cnt1 = 0;	// 1번 계단에 있는 사람 수
		int cnt2 = 0;	// 2번 계단에 있는 사람 수
		while(sum < cnt) {
			// 내려가던 중인 사람 내려보내기
			for (int i = 0; i < p1; i++) {
				if(ps1[i].len <= map[x1][y1]) ps1[i].len--;
			}
			for (int i = 0; i < p2; i++) {
				if(ps2[i].len <= map[x2][y2]) ps2[i].len--;
			}
			// 밑에 도착한 사람 카운트에서 뺴주기
			for (int i = 0; i < p1; i++) {
				if(ps1[i].len == 0) {
					ps1[i].num = -1;
					sum++;
					cnt1--;
				}
			}
			for (int i = 0; i < p2; i++) {
				if(ps2[i].len == 0) {
					ps2[i].num = -1;
					sum++;
					cnt2--;
				}
			}
			// 계단 1에서의 상황
			for (int i = 0; i < p1; i++) {
				if(ps1[i].time > time) break;	// 도착 안한 사람 무시
				if(ps1[i].num < 0) continue;	// 이미 내려간 사람 무시
				if(ps1[i].len == map[x1][y1] + 1 && cnt1 < 3 && !ps1[i].wait) {		// 도착해서 1분 대기
					ps1[i].wait = true; 
				}
				if(ps1[i].len == map[x1][y1] + 1 && cnt1 >= 3 && !ps1[i].wait) {	// 도착했는데 계단 꽉차서 기다리는 사람 기다림 여부 체크
					ps1[i].wait = true; 
				}
				if(ps1[i].len == map[x1][y1] + 1 && cnt1 < 3 && ps1[i].wait) {	// 계단에 사람이 들어갈 수 있고, 이미 기다리고 있던 사람이면 바로 내려보내기
					ps1[i].len--;
					cnt1++;
				}
			}
			// 계단 2에서의 상황
			for (int i = 0; i < p2; i++) {
				if(ps2[i].time > time) break;
				if(ps2[i].num < 0) continue;
				if(ps2[i].len == map[x2][y2] + 1 && cnt2 < 3 && !ps2[i].wait) {
					ps2[i].wait = true; 
				}
				if(ps2[i].len == map[x2][y2] + 1 && cnt2 >= 3 && !ps2[i].wait) {
					ps2[i].wait = true; 
				}
				if(ps2[i].len == map[x2][y2] + 1 && cnt2 < 3 && ps2[i].wait) {
					ps2[i].len--;
					cnt2++;
				}
			}
			time++;
		}
		ans = Math.min(ans, time);
	}
}
