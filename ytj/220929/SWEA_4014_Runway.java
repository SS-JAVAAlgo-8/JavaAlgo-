package study.day220929.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
// java.util.* , java.io.* , throws Exception
public class SWEA_4014_Runway {
	
	static int N, X; 		// 한변의 길이, 경사로의 길이
	static int ans;		 
	static int [][] map;	// 지형 정보
	static int [][] reverse_map;	// 지형 정보
	
	static boolean [] filled; // 각 행,열 마다 활주로 건설 확인
	static int cnt;			// 객 행,열 마다 높이 변화가 1인 지점 갯수
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			reverse_map = new int[N][N];
			ans = 0;
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					reverse_map[c][r] = map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
						
			for (int i = 0; i < N; i++) {
				runwayCheck(map[i]);
				runwayCheck(reverse_map[i]);
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void runwayCheck(int[] arr) {
		
		List<Integer> list = new ArrayList<>();
		filled = new boolean[N];
		cnt = 0;
		
		for (int i = 0; i < N-1; i++) {
			if(Math.abs(arr[i] - arr[i+1]) == 1) 
				list.add(arr[i] > arr[i+1] ? i+1 : i);
			else if(Math.abs(arr[i] - arr[i+1]) >= 1) return;
		}
				
		for (int i = 0; i < list.size(); i++) {
			int idx = list.get(i);
			
			if(idx - (X-1) >= 0 && idx+1 < N && arr[idx] < arr[idx+1]) { // 높은지대가 오른쪽에 있으므로 왼쪽으로 X만큼 확인
				
				int std = arr[idx];
				boolean check = true;
				for (int n = idx; n >= idx - (X-1); n--) {
					
					if(std != arr[n] || filled[n]) check = false;
					else filled[n] = true;
				}
				
				if(check == true) cnt++;
			}
			
			else if(idx + (X-1) < N && idx-1 >= 0 && arr[idx] < arr[idx-1]) { // 높은지대가 왼쪽에 오른쪽으로 X만큼 확인
				
				int std = arr[idx];
				boolean check = true;
				for (int n = idx; n < idx + X; n++) {
					
					if(std != arr[n] || filled[n]) check = false; 
					else filled[n] = true;
				}
				
				if(check == true) cnt++;
			}
		}
		if( list.size() == cnt) ans++;
	}
}
/*

1
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2

1
8 3
2 2 2 3 3 4 2 2 
2 2 2 3 3 4 2 2 
2 2 2 2 2 2 2 2 
2 2 2 2 2 2 2 2 
2 2 2 2 1 1 2 2 
2 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 
2 1 1 1 1 1 1 1 

*/
