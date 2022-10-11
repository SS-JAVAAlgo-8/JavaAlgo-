/*
1. 시작 칸을 h로 지정, cnt 1 부터 시작
2 - 1 h와 같으면
- cnt ++
- cnt2가 0보다 크고 x보다 작을 경우 break;
2 - 2 h보다 1 높으면
- 저장해놓은 cnt 가 x 미만일  경우 break;
- x 이상일 경우 cnt = 1로 초기화, h도 초기화
- cnt2가 0보다 크고 x보다 작을 경우 break;
2 - 3 h보다 1 낮으면
- cnt = 0으로 초기화, cnt2 ++
- 만약 cnt2 가 x 이상일 경우 cnt2 = 0, h도 초기화 
2 - 4 그 외에 break;
3. cnt2가 0일 경우 결과 카운트 ans++
*/
package study.day0929;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로 {	// 메모리 : 28,892 kb, 실행시간 : 131 ms	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {	
			int ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 모든 행에 대한 활주로 건설 가능성 체크
			outline : for (int i = 0; i < n; i++) {
				int h = map[i][0];	// 처음 높이 저장
				int cnt = 1;	// 같은 높이 개수 저장
				int cnt2 = 0;	// 1칸 낮은 높이 개수 저장
				for (int j = 1; j < n; j++) {
					if(map[i][j] == h) {	// h와 높이가 같은 경우
						if(cnt2 > 0) continue outline;	// 이전에 1칸 낮은 높이가 x보다 적게 있었을 경우 활주로 건설 불가능
						cnt++;	// 같은 높이 개수 ++
					} else if(map[i][j] == h + 1) {	// h보다 1칸 높을 경우
						if(cnt < x || cnt2 > 0) continue outline;	// 이전에 2칸, 1칸 낮은 높이가 x보다 적게 있었을 경우 불가능
						if(cnt >= x) {	// 이전에 1칸 낮은 높이가 x이상 있었을 경우 경사로 설치 가능
							cnt = 1;	// 같은 높이 개수 1개로 초기화
							h = map[i][j];	// 높이 본인으로 초기화
						}
					} else if(map[i][j] == h - 1) {	// h보다 1칸 낮을 경우
						cnt = 0;	// 같은 높이 개수 0개로 초기화
						cnt2++;		// 1칸 낮은 높이 개수 ++
						if(cnt2 == x) {	// 여태까지 1칸 낮은 높이가 x개 연속 있었을 경우 경사로 설치
							cnt2 = 0;	// 1칸 낮은 개수 초기화
							h = map[i][j];	// 높이 본인으로 초기화
						}
					} else continue outline;	// 높이 차이가 1이상인 경우 무시				
				}
				if(cnt2 == 0 )ans++;	// 마지막에 1칸 낮은 높이가 있었는지 체크
			}
			// 모든 열에 대한 활주로 건설 가능성 체크
			outline : for (int j = 0; j < n; j++) {
				int h = map[0][j];	
				int cnt = 1;	
				int cnt2 = 0;
				for (int i = 1; i < n; i++) {
					if(map[i][j] == h) {	
						if(cnt2 > 0) continue outline;	
						cnt++;	
					} else if(map[i][j] == h + 1) {	
						if(cnt < x || cnt2 > 0) continue outline;
						if(cnt >= x) {
							cnt = 1;
							h = map[i][j];
						}
					} else if(map[i][j] == h - 1) {
						cnt = 0;
						cnt2++;
						if(cnt2 == x) {
							cnt2 = 0;
							h = map[i][j];
						}
					} else continue outline;					
				}
				if(cnt2 == 0) ans++;
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
}
