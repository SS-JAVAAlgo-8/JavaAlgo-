import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2115_HoneyHarvesting {

	static int N, M, C, ans;
	static int worker;
	static int[][] honey;
	static boolean [][] isSelected;
	static boolean[] isVisited;
	static int [][] harvestWK;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			ans = 0;
			worker = 0;
			honey = new int [N][N];
			isSelected = new boolean [N][N];
			isVisited = new boolean[M];
			harvestWK = new int [2][M];
			
			// 입력 data 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0, 0);
									
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void dfs(int idx, int r, int c) {
		if(idx == 2) {
			worker = 0;    // 일꾼이 채취한 벌꿀의 이익
			Arrays.fill(isVisited, false); // 부분 집합으로 합을 도출할 때 이용되는 벌꿀통 방문 체크 
			
			profit(harvestWK[0], 0);
			int sum1 = worker;
			
			worker = 0;
			Arrays.fill(isVisited, false);
			
			profit(harvestWK[1], 0);
			int sum2 = worker;
			
			ans = Math.max(ans, sum1 + sum2);
			
//			System.out.println(Arrays.toString(harvestWK[0]));
//			System.out.println(Arrays.toString(harvestWK[1])+"\n");
			return;
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = c; j < N; j++) {
								
				if(j+M > N || isSelected[i][j]) continue;
				
				for (int n = 0; n < M; n++) {
					harvestWK[idx][n] = honey[i][j+n];
					isSelected[i][j+n] = true;
				}
				// 벌통 갯수만큼 가로로 담기 성공
//				System.out.printf("%d번째 통 [%d , %d]\t", idx+1, i, j);
				// 두번째 벌통 담기
				dfs(idx + 1, i, j);
				
				for (int n = 0; n < M; n++) {
					isSelected[i][j+n] = false;
				}
				// 첫번쨰 벌통의 다음 경우의 수를 위해 방문 체크 반환
				
			}
			System.out.println();
		}
	}

	private static void profit(int[] harvest, int idx) {
		
		if(idx == M) {
			int max = 0;
			int profit = 0;
			for (int i = 0; i < M; i++) {
				if(isVisited[i]) {
					max += harvest[i];
					profit += harvest[i] * harvest[i];
				}
			}
			
			if(max <= C)
				worker = Math.max(profit, worker);
			
			return;
		}
		
		isVisited[idx] = true;
		profit(harvest, idx + 1);
		
		isVisited[idx] = false;
		profit(harvest, idx + 1);

	}


}
/*
1
8 3 12
9 1 6 7 5 4 6 7
9 5 1 8 8 3 5 8
5 2 6 8 6 9 2 1
9 2 1 8 7 5 2 3
6 5 5 1 4 5 7 2
1 7 1 8 1 9 5 5
6 2 2 9 2 5 1 4
7 1 1 2 5 9 5 7
1
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
1
3 3 10
7 2 9
6 6 6
5 5 7
*/