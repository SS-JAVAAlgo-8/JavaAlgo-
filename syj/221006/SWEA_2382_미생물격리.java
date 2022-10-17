/*

구현문제

걸린 시간 : 20-30분

1. 군집 객체 생성 후 배열에 담기 (군집 객체 내용 : r, c, 미생물 수, 방향)
2. M시간 동안 다음 구간 반복
- 미생물 전체 이동 (0 || N - 1 에 닿은 경우 방향 바꾸고 (미생물 수/2))
- 미생물 병합 (미리 좌표 -> 좌표가 같은 경우 미생물 수를 기준으로 내림차순 정렬해놓기)

3. M시간 후 미생물 합 출력

*/
package study.day1006;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2382_미생물격리 {	// 메모리 : 67,964 kb,		실행시간 : 444 ms
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	// 군집 객체
	public static class Microbe implements Comparable<Microbe> {
		int r;
		int c;
		int cnt;
		int d;
		public Microbe(int r, int c, int cnt, int d) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.d = d;
		}
		@Override
		public int compareTo(Microbe o) {
			if(this.r == o.r) {	// 좌표기준 우선으로 정렬
				if(this.c == o.c) {
					return Integer.compare(this.cnt, o.cnt) * -1;	// 좌표가 같은 경우 미생물 수로 내림차순 정렬
				}else return Integer.compare(this.c, o.c);
			}else return Integer.compare(this.r, o.r);
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/2382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Microbe[] arr = new Microbe[K];
			// 미생물 객체 생성 후 배열에 담아두기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				arr[i] = new Microbe(r, c, cnt, d);
			}
			// 격리시간동안 미생물 이동
			while(M > 0) {
				// 모든 군집 이동
				for (int i = 0; i < K; i++) {
					if(arr[i].cnt == 0) continue;
					int nr = arr[i].r + dx[arr[i].d];
					int nc = arr[i].c + dy[arr[i].d];
					arr[i].r = nr;
					arr[i].c = nc;
					// 약품에 닿은 경우 미생물 수 절반으로 줄이고 방향 전환
					switch (arr[i].d) {
					case 1:
						if(nr == 0) {
							arr[i].d = 2;
							arr[i].cnt /= 2;
						}
						break;
					case 2:
						if(nr == N - 1) {
							arr[i].d = 1;
							arr[i].cnt /= 2;
						}					
						break;
					case 3:
						if(nc == 0) {
							arr[i].d = 4;
							arr[i].cnt /= 2;
						}
						break;
					case 4:
						if(nc == N - 1) {
							arr[i].d = 3;
							arr[i].cnt /= 2;
						}
						break;
					}
				}
				// 좌표 기준 -> 미생물 수 기준으로 정렬
				Arrays.sort(arr);
				// 이동 후 겹치는 곳에 대해 병합처리
				for (int i = 0; i < K; i++) {
					if(arr[i].cnt == 0) continue;
					int sum = arr[i].cnt;
					for (int j = i + 1; j < K; j++) {
						if(arr[j].cnt == 0) continue;
						if(arr[i].r == arr[j].r && arr[i].c == arr[j].c) {
							sum += arr[j].cnt;
							arr[j].cnt = 0;
						}else break;
					}
					arr[i].cnt = sum;
				}
				M--;
			}
			
			// 격리 후 남은 미생물 합
			int ans = 0;
			for (int i = 0; i < K; i++) {
				ans += arr[i].cnt;
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
}
