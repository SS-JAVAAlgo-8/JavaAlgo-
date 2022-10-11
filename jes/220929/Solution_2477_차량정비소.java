package algostudy.day0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.04 13:00
 * 	풀이 종료 시간: 22.10.04 19:13
 *  풀이 시간 : 설계 20분 + 코딩 30분 + 디버깅 30분=> 약 80분
 * 
 * 	목표: 지갑 분실한 고객과 같은 접수창구, 같은 정비 창구를 이용한 고객번호의 합 출력하기
 * 
 *	조건
 * 	1. 차량 정비소를 방문한 고객은 K명 -> 도착하는 순서대로 고객번호 부여(1부터)
 *  2. 고객번호 k 고객이 차량 정비소에 도착하는 시간은 tk
 *  3. 접수 창구 우선순위
 *    3-1. 여러 고객 대기 -> 고객번호 낮은 순으로 우선 접수
 *    3-2. 빈 창구 여러 곳 -> 접수 창구번호가 작은 곳
 *  4. 정비 창구 우선순위
 *    4-1. 먼저 기다리는 고객이 우선
 *    4-2. 두 명 이상의 고객이 동시에 도착한 경우 접수 창구번호 작은 순
 *    4-3. 빈 창구 여러 곳 -> 정비 창구번호가 작은 곳
 *  5. 지갑 분실한 고객과 같은 접수, 정비 창구 이용한 고객 없으면 -1, 있으면 그 고객번호들의 합 출력
 * 
 *  풀이 계획
 *  1. 시간별 처리 -> 생각보다 헷갈려서 포기..
 *  2. 과정별 처리 -> 2개의 큐를 사용하여 각 과정별로 처리
 * 
 * 	풀이 방법
 * 	1. 접수 -> 정비 과정으로 진행
 *  2. Client 클래스를 통해 지갑 분실 고객과 같은 창구를 이용한 고객 판별
 *  3. 정비는 정렬이 필요 -> 우선순위 큐를 사용하여 정비할 고객을 정렬
 *  4. 정비까지 마치면 해당 고객이 이용한 창구와 지갑 분실 고객이 이용한 창구 비교하여 ans에 +
 *  
 */

public class Solution_2477_차량정비소 { // 32960kb 144ms

	static class Client implements Comparable<Client> {
		int num, time, rec;
		boolean same;

		public Client(int num, int time, int rec, boolean same) {
			this.num = num; // 고객 번호
			this.time = time; // 정비소에 도착한 시간
			this.rec = rec; // 이용한 접수 창구번호
			this.same = same;
		}

		@Override
		public int compareTo(Client o) {
			if (this.time == o.time) {
				return Integer.compare(this.rec, o.rec);
			}
			return Integer.compare(this.time, o.time);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
			int M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
			int K = Integer.parseInt(st.nextToken()); // 정비소 방문 고객 수
			int A = Integer.parseInt(st.nextToken()); // 지갑 두고간 고객이 이용한 접수 창구번호
			int B = Integer.parseInt(st.nextToken()); // 지갑 두고간 고객이 이용한 정비 창구번호

			int[] reception = new int[N + 1]; // 접수 창구별 걸리는 시간
			int[] repair = new int[M + 1]; // 정비 창구별 걸리는 시간
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				reception[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				repair[i] = Integer.parseInt(st.nextToken());
			}

			Queue<Client> q_rec = new LinkedList<>(); // 접수받을 고객
			PriorityQueue<Client> q_rep = new PriorityQueue<>(); // 정비받을 고객
			int[] wait_rec = new int[N + 1]; // 데스크별 접수 대기 끝나는 시간
			int[] wait_rep = new int[M + 1]; // 데스크별 정비 대기 끝나는 시간
			int ans = 0; // 출력할 값
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				q_rec.add(new Client(i, Integer.parseInt(st.nextToken()), 0, false));
			}

			// 접수
			int time = 0;
			outer: while (!q_rec.isEmpty()) {
				if(q_rec.peek().time > time) {
					time++;
					continue;
				}
				// 접수 창구 개수만큼 반복
				for (int i = 1; i <= N; i++) {
					if (wait_rec[i] <= time) { // 대기가 끝나는 시간이 현재 시간과 같거나 이전인 경우(배치 가능할 때)
						wait_rec[i] = time + reception[i]; // 현재 데스크가 다음 고객을 받을 수 있는 시간 저장
						Client c = q_rec.poll();
						// 지갑을 잃어버린 고객과 같은 접수 창구인 경우 true, 아닌 경우 false
						if (A == i) q_rep.add(new Client(c.num, wait_rec[i], i, true));
						else q_rep.add(new Client(c.num, wait_rec[i], i, false));
						continue outer;
					}
				}
				time++;
			}

			// 정비
			time = 0;
			outer: while (!q_rep.isEmpty()) {
				if(q_rep.peek().time > time) {
					time++;
					continue;
				}
				// 접수 창구 개수만큼 반복
				for (int i = 1; i <= M; i++) {
					if (wait_rep[i] <= time) { // 대기가 끝나는 시간이 현재 시간과 같거나 이전인 경우(배치 가능할 때)
						wait_rep[i] = time + repair[i]; // 현재 데스크가 다음 고객을 받을 수 있는 시간 저장
						Client c = q_rep.poll();
						// 지갑을 잃어버린 고객과 같은 접수 창구, 같은 정비 창구인 경우 고객번호 합하기
						if (c.same && B == i) ans+=c.num;
						continue outer;
					}
				}
				time++;
			}
			
			if(ans==0) ans = -1;
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
