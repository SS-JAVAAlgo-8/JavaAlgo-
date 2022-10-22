package study.day221013.problem;

import java.io.*;
import java.util.*;

public class BOJ_13549_HideAndSeek {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		findCnt(N, K);
	}

	private static void findCnt(int N, int K) {
		
		Queue<Integer> qu = new ArrayDeque<>();
		Queue<Integer> cnt = new ArrayDeque<>();
		boolean isVisited[] = new boolean[100000 + 1];
		isVisited[N] = true;
		qu.offer(N);
		cnt.offer(0);
		
		while( !qu.isEmpty() ) {
			
			int cur = qu.poll();
			int time = cnt.poll();
			
			if( cur == K ) {
				System.out.println(time);
				return;
			}
			
			if (2*cur <= 100000 && !isVisited[2*cur]) {
				qu.offer(2*cur);
				cnt.offer(time);
				isVisited[2*cur] = true;
			}
			
			if (cur-1 >= 0 && !isVisited[cur-1]) {
				qu.offer(cur-1);
				cnt.offer(time+1);
				isVisited[cur-1] = true;
			}
			
			if (cur+1 <= 100000 && !isVisited[cur+1]) {
				qu.offer(cur+1);
				cnt.offer(time+1);
				isVisited[cur+1] = true;
			}
			
		}
		/*
		ex) 4 에서 12로
		2 -1 +1 순으로 집어넣는경우. 3->6 으로 진행 되는 동작이 5->6 으로 진행 되는 동작보다 먼저 일어나므로 4->3->6->12 1초.
		2 +1 -1 순으로 집어넣는경우. 5->6으로 진행되는 동작이 3->6보다 먼저 진행되어 3->6 으로 queue에 들어가지지 않음. 4->5->6->12 2초.
		*/
	}

}
