package algostudy.day0818;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D4_1227_미로_2 { // 39696kb 190ms
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] map = new int[100][100];
		
		// 상우하좌
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for(int t = 0; t < 10; t++) {
			int T = Integer.parseInt(br.readLine());
			Point end = null;
			for(int i=0; i<100; i++) {
				String[] st = br.readLine().split("");
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st[j]);
					if(map[i][j]==3) end = new Point(i, j);
				}
			}
			
			boolean[][] isChecked = new boolean[100][100]; 
			Queue<Point> q = new LinkedList<>();
			boolean isArrived = false;
			q.offer(end);
			outer: while(!q.isEmpty()) {
				Point temp = q.poll();
				int x = temp.x;
				int y = temp.y;
				for(int i=0; i<4; i++) {
					if(x+dr[i]<=0 || x+dr[i]>=99 || y+dc[i]<=0 || y+dc[i]>=99 || map[x+dr[i]][y+dc[i]] != 0 || map[x+dr[i]][y+dc[i]] == 3) {
						if(map[x+dr[i]][y+dc[i]] == 2) {
							isArrived = true;
							break outer;
						}
						continue;
					}
					if(!isChecked[x+dr[i]][y+dc[i]]) q.offer(new Point(x+dr[i], y+dc[i]));
					isChecked[x+dr[i]][y+dc[i]] = true;
				}
			}
			
			sb.append("#").append(T).append(" ");
			if(isArrived) sb.append(1).append("\n");
			else sb.append(0).append("\n");
		}
		
		System.out.println(sb);
	}
}
