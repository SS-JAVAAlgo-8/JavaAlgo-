/*
단순구현
접수 대기 큐 - 우선순위큐
정비 대기 큐
창구 객체 - 창구번호, 처리시간, 현재 고객
시간 별로
1. 정비 창구 시간 1씩 줄이기
2. 접수 창구 시간 1씩 줄이기
3. 접수완료된 사람들 정비 대기 큐에 넣기
4. 비어있는 정비창구에 고객 배치 (오래기다린 순 -> 앞에서부터 꺼내기)
5. 시간마다 도착하는 고객 접수 대기 큐에 넣기 (우선순위 큐 정수는 자동 오름차순 정렬)
6. 비어있는 접수창구에 고객 배치
7. 정비가 완료되지 않은 고객이 있다면 다시 반복
8. 정비가 모두 완료되었다면 A, B 창구를 거친 고객 번호 합 출력
*/
package study.day0929;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2477_차량정비소 {	// 메모리 : 63952 kb	실행시간 : 622 ms	
	public static class Desk {
		int no;
		int time;
		int kNum;
		public Desk(int no, int time, int kNum) {
			this.no = no;
			this.time = time;
			this.kNum = kNum;
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("data/2477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 데이터 입력받기
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] n = new int[N + 1];		// 각 접수창구의 처리시간
			Desk[] nq = new Desk[N + 1];	// 접수창구 담은 배열
			int[] m = new int[M + 1];		// 각 정비창구의 처리시간
			Desk[] mq = new Desk[M + 1];	// 정비창구 담은 배열

			int K = Integer.parseInt(st.nextToken());

			int[] k = new int[K + 1];				// 각 고객 별 도착시간
			int[][] visited = new int[K + 1][2];	// 고객별 방문한 창구 저장 배열

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				n[i] = Integer.parseInt(st.nextToken());
				nq[i] = new Desk(i, 0, 0);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				m[i] = Integer.parseInt(st.nextToken());
				mq[i] = new Desk(i, 0, 0);
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				k[i] = Integer.parseInt(st.nextToken());
			}

			PriorityQueue<Integer> q1 = new PriorityQueue<>();	// 접수 대기 큐
			Queue<Integer> q2 = new ArrayDeque<>();	// 정비 대기 큐
			int time = 0;
			// 시간마다 이루어지는 활동 
			// (도착하는 고객 접수 대기 큐에 넣기 -> 비어있는 접수 창구에 고객 배치 -> 접수완료된 고객 정비 대기 큐에 넣기 -> 정비 완료된 고객 제거)
			// 역순으로 시행하도록 하자 ~!~!~!
			outline : while(true) {
				for (int i = 1; i <= M; i++) {	// 정비 시간 줄이기
					if(mq[i].time == 0) continue;
					else mq[i].time--;
				}

				for (int i = 1; i <= N; i++) {	// 접수 시간 줄이기
					if(nq[i].time == 0) continue;
					else nq[i].time--;
				}

				for (int i = 1; i <= N; i++) {	// 접수완료된 사람들 정비 대기 큐에 넣기
					if(nq[i].time == 0 && nq[i].kNum != 0) {
						q2.offer(nq[i].kNum);
						nq[i].kNum = 0;
					}
				}

				if(!q2.isEmpty()) {	// 비어있는 정비창구에 고객 배치 (오래기다린 순 --> 앞에서부터 꺼내기)
					for (int i = 1; i <= M; i++) {
						if(q2.isEmpty()) break;
						if(mq[i].time == 0) {
							int num = q2.poll();
							mq[i].time = m[i];
							mq[i].kNum = num;
							visited[num][1] = i;
						}
					}
				}

				// 시간마다 도착하는 고객 접수대기 큐에 넣기
				for (int i = 1; i <= K; i++) {
					if(k[i] == time) q1.offer(i);
				}

				// 우선순위 큐로 작은 인덱스부터 비어있는 접수창구에 배치하기
				if(!q1.isEmpty()) {
					for (int i = 1; i <= N; i++) {
						if(q1.isEmpty()) break;
						if(nq[i].time == 0) {
							int num = q1.poll();
							nq[i].time = n[i];
							nq[i].kNum = num;
							visited[num][0] = i;
						}
					}
				}
				time ++;

				for (int i = 1; i <= K; i++) {
					if(visited[i][1] == 0) continue outline;
				}
				break;
			}

			int ans = 0;
			for (int i = 1; i <= K; i++) {
				if(visited[i][0] == A && visited[i][1] == B) {
					ans += i;
				}
			}

			sb.append("#" + t + " " + (ans == 0 ? -1 : ans) + "\n");
		}
		System.out.println(sb);
	}
}