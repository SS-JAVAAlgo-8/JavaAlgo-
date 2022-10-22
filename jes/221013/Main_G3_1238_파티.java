package algostudy.day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.17 10:30
 * 	풀이 종료 시간: 22.10.17 11:17
 *  풀이 시간 : 설계 10분 + 코딩 30분 = 40분
 *  
 * 	목표: N명의 학생이 X번 마을 왕복하는 데 모두 최단 시간이 걸릴 때 가장 오래 걸리는 학생 찾기
 * 
 *	조건
 * 	1. N명의 학생(1<=N<=1000)
 *  2. M개의 단방향 도로 (1<=M<=10000)
 *  3. X번 마을에 모여 파티
 *  4. i번째 길을 지나는 데 걸리는 시간 = Ti
 *  5. 학생들은 모두 최단시간에 왕복해야 함
 *  
 *  풀이 계획
 *  - 한 정점에서의 최단 경로 => 다익스트라 알고리즘
 *  
 * 	풀이 방법
 *  1. 각 학생이 위치한 마을 -> X번 마을 최단 경로(최단 시간) 구하기
 *  2. X번 마을 -> 각 학생이 위치한 마을 최단 경로(최단 시간) 구하기
 *  3. 앞에서 구한 두가지 최단 경로를 각각 하나씩 합하여 왕복 시간 계산
 *  4. 왕복 시간이 가장 큰 것 출력
 *  
 */

public class Main_G3_1238_파티 { // 17260kb 176ms
	static class Vertex implements Comparable<Vertex>{
		int vertex, weight;

		public Vertex(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 단방향 도로 수
		int X = Integer.parseInt(st.nextToken())-1; // 모일 마을 번호
		
		List<Vertex>[] adjList = new ArrayList[N];
		List<Vertex>[] reverse = new ArrayList[N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			if(adjList[from] == null) adjList[from] = new ArrayList<>();
			if(reverse[to] == null) reverse[to] = new ArrayList<>();
			adjList[from].add(new Vertex(to, weight));
			reverse[to].add(new Vertex(from, weight));
			
		}
		
		int[] D = new int[N]; // 각 마을에서 목표 마을까지 최단 거리 저장할 배열
		int[] rD = new int[N]; // 목표 마을에서 각 마을까지 최단 거리 저장할 배열
		Arrays.fill(D, Integer.MAX_VALUE);
		Arrays.fill(rD, Integer.MAX_VALUE);

		dijkstra(X, D, adjList); // 각 마을에서 목표 마을까지 최단 거리 
		dijkstra(X, rD, reverse); // 목표 마을에서 각 마을까지 최단 거리 
		
		int max = 0;
		for(int i=0; i<M; i++) { // 왕복으로 걸리는 시간 구하고 그 중에서 최대인 값 갱신
			max = Math.max(max, D[i]+rD[i]);
		}
		System.out.println(max);
	}

	// 인접 리스트를 사용한 다익스트라
	private static void dijkstra(int x, int[] d, List<Vertex>[] list) {
		d[x] = 0; // 시작점을 x라고 했을 때 => 여기에서는 목표 마을(X)이 항상 시작점으로 들어옴
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(x, d[x]));
		
		while (!pq.isEmpty()) {
			Vertex minV = pq.poll();
			int vertex = minV.vertex;
			int weight = minV.weight;
			
			// 연결된 마을이 없는 경우 continue
			if(list[vertex]==null) continue;
			// 연결된 마을이 있는 경우 최단 거리 탐색
			for(int i=0; i<list[vertex].size(); i++) {
				int v = list[vertex].get(i).vertex;
				int w = weight + list[vertex].get(i).weight;
				if(d[v]> w) { // 기존의 최단 거리보다 짧은 경우 갱신
					d[v] = w;
					pq.add(new Vertex(v, w));
				}
			}
		}
	}
}
