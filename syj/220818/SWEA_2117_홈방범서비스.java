/*
 * 완 전 탐 색 ...
 * 
 * 1. 도시 정보 입력받기
 * 2. 입력 받으면서 집 개수 저장
 * 3. k범위 설정 ----> 1 ~ 2N-1
 * 4. 모든 위치에서 k범위 안에 있는 집 개수 구하기 (최대 집 개수 저장) (|r - x| + |c - y| < k)
 * 5. 최대 집 개수에 대해 손익 계산
 * 6. 이익이 났을 때 최대 집 개수 저장
 * 7. 최대 집 개수가 총 집 개수와 같으면 종료 후 출력
 * 
 */
package study.day0825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_홈방범서비스 {	// 메모리 : 26692 kb	실행시간 : 456ms
	static int[][] map;
	static int N, M;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int homeCnt = 0;
			for (int i = 0; i < N; i++) {	// 도시 정보 입력받기
				String[] temp = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(temp[j]);
					if(map[i][j] == 1) homeCnt++;	// 입력받으면서 집 개수 저장
				}
			}
			int maxHome;
			int ans = 0;
			for (int k = 1; k <= (2 * N) - 1; k++) {	// k가 2N - 1 까지 커지면 모든 맵을 덮을 수 있음 
				maxHome = 0;
				for (int i = 0; i < N; i++) {	// (0, 0) 부터 (N - 1, N - 1)까지 모든 위치에서 K범위 내에 있는 집 최대값 구하기
					for (int j = 0; j < N; j++) {
						maxHome = Math.max(maxHome, security(i, j, k));	// 방범 범위내에 있는 집 개수 구하는 함수
					}
				}
				if((maxHome * M) - ((k * k) + ((k - 1) * (k - 1))) < 0) continue;	// 만약 손해일 경우 저장하지 않고 진행
				ans = Math.max(ans, maxHome);	// 이익일 경우 최대값 갱신
				if(ans == homeCnt) break;	// 이익을 본 집의 개수가 모든 집의 개수와 같으면 더이상 탐색할 필요 없으므로 반복문 탈출
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	private static int security(int r, int c, int k) {	// 방범서비스 범위가 k ---> |r - x| + |c - y| 가 k 이하여야 한다 
		int home = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(Math.abs(r - i) + Math.abs(c - j) < k && map[i][j] == 1) home++;	// 손익계산 때문에 k를 1부터 시작해서 <= 가 아니라 < 를 사용
			}
		}
		return home;
	}
}