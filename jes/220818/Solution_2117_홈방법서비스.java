package algostudy.day0818;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2117_홈방법서비스 { // 23844kb 218ms
	static int M, N;
	static List<Point> homelist;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			homelist = new ArrayList<>();
			int max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num==1) homelist.add(new Point(i, j));
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++)
					max = Math.max(max, service(i, j));
			}
		
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static int service(int x, int y) {
		int cost = 1, sum = 0, max = 0;
		// 서비스 지역이 맵 전체를 다 덮는 크기까지
		for(int k = 0; k <= N; k++) {
			// 현재 비용 = 이전 비용 + 4*K
			cost += k*4;
			for(int i=0; i<homelist.size(); i++) {
				// 범위 내에 있을 때
				if(Math.abs(x-homelist.get(i).x) + Math.abs(y - homelist.get(i).y) == k) {
					sum++;
				}
			}
			// 손해를 보지 않는 경우
			if(sum!=0 && sum*M >= cost) {
				max = Math.max(max, sum);
			}
		}
		return max;
	}
}
