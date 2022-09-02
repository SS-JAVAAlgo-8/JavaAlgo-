package algostudy.day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5653_줄기세포배양 {

	static class Cell implements Comparable<Cell>{
		int r, c; // 위치
		int start; // 시작 시간(X)
		int end; // 끝나는 시간(X*2)
		public Cell(int r, int c, int start, int end) {
			this.r = r;
			this.c = c;
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Cell o) {
			return o.start - this.start; // 내림차순 -> 동시에 번식할 때 큰 값을 넣어주기 위해!
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Cell> q = new PriorityQueue<>(); // 시간 별 세포 번식
			Queue<Cell> tmp = new LinkedList<>(); // q를 통해 얻은 세포 및 q에서 활성화되지 않은 세포 번식
			
			int[][] map = new int[400][400];
			
			for(int i=200-N; i<200; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=200-M; j<200; j++) {
					int x = Integer.parseInt(st.nextToken());
					// 생명력이 있는 경우 큐에 담기
					if(x!=0) {
						map[i][j] = x;
						q.add(new Cell(i, j, x, x*2));
					}
				}
			}
			
			int[] dr = {-1, 0, 1, 0};
			int[] dc = {0, 1, 0, -1};
			// K시간만큼 반복
			for(int k=1; k<=K; k++) {
				while(!q.isEmpty()) {
					Cell C = q.poll();
					// X시간이 지났을 때(C.end-C.start = X)세포 번식
					if(C.start >= C.end) {
						for(int i=0; i<4; i++) {
							if(map[C.r+dr[i]][C.c+dc[i]] == 0) {
								map[C.r+dr[i]][C.c+dc[i]] = C.start;
								tmp.add(new Cell(C.r+dr[i], C.c+dc[i], C.start, C.start*2));
							}
						}
					}
					// X시간만큼 지나지 않은 경우 번식하지 않고 세포 C가 살아 있을수 있는 시간을 1 감소
					C.end--;
					// 세포가 아직 죽지 않았으면 다시 큐에 추가
					if(C.end!=0) tmp.add(C);
				}
				
				while(!tmp.isEmpty()) {
					q.add(tmp.poll());
				}
			}
			sb.append("#").append(t).append(" ").append(q.size()).append("\n");
		}
		System.out.println(sb);
	}

}
