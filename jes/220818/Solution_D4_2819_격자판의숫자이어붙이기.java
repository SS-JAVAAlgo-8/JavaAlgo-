package algostudy.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution_D4_2819_격자판의숫자이어붙이기 {	// 70388kb 208ms
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Set<String> ans;
	static String[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new String[4][4];
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			ans = new HashSet<>();  
			for(int i=0; i<4; i++) {
				map[i] = br.readLine().split(" ");
			}
			
			// 한 칸마다 dfs
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					setNum(0, i, j, map[i][j]);
				}
			}
			sb.append("#").append(t).append(" ").append(ans.size()).append("\n");
		}
		System.out.println(sb);
	}

	private static void setNum(int cnt, int x, int y, String res) {
		// 6번 이동 했을 때 HashSet에 담고 리턴
		if(cnt==6) {
			ans.add(res);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(x+dr[i] >= 0 && y+dc[i] >= 0 && x+dr[i] < 4 && y+dc[i] < 4) {
				setNum(cnt + 1, x+dr[i], y+dc[i], res + map[x+dr[i]][y+dc[i]]);
			}
		}
	}
}
