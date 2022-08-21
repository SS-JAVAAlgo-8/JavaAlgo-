package BOJ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class BOJ_17281_야구공 {
	static int inn;
	static int base1 , base2 ,base3  ;
	static int[][] score;
	static int outCnt;
	static int result = Integer.MIN_VALUE;
	static int[] order = new int[10];
	static int[] visited = new int[10];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		inn = sc.nextInt();
		score = new int[inn][];

		for (int i = 0; i < inn; i++) {
			int[] temp = new int[10];
			for (int j = 1; j <= 9; j++) {
				temp[j] = sc.nextInt();
			}
			score[i] = temp;
		}
		
		//1번선수를 4번에 지정하고 순열뽑기
		visited[4] = 1;
		order[4] = 1;
		per(2);
		System.out.println(result);
	}

	private static void per(int depth) {
		if (depth == 10) {
			//다 구성했으면 경기 시작
			play();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				order[i] = depth;
				per(depth+1);
				visited[i] = 0;
			}
		}
	}

	private static void play() {
		base1 = 0;
		base2 = 0;
		base3 = 0;


		int tempResult = 0;
		int index = 0;
		int now = 1;
		while (true) {
			if(outCnt == 3) { //아웃카운트 3
				index +=1;
				base1 = 0;
				base2 = 0;
				base3 = 0;
				outCnt = 0;
				continue;
			}
			if(index >= inn) break;
			if (score[index][order[now]] == 1) { //1루타
				tempResult += base3;
				base3 = base2;
				base2 = base1;
				base1 = 1;
			}
			else if(score[index][order[now]] == 2) { //2루타
				tempResult += base2 + base3;
				base3 = base1;
				base2 = 1;
				base1 = 0;
			}
			else if(score[index][order[now]] == 3) { //3루타
				tempResult += base1 + base2 + base3;
				base3 = 1;
				base2 = 0;
				base1 = 0;
			}
			else if(score[index][order[now]] == 4) { //4루타
				tempResult +=base3 + base2 + base1 + 1;
				base1 = 0;
				base2 = 0;
				base3 = 0;
			}
			else if(score[index][order[now]] == 0) { // 아웃!
				outCnt +=1;
			}
			now = now +1;
			if(now == 10) now = 1; // 인덱스 1번으로 되돌려주기
		}
		result = Math.max(result, tempResult);

	}
}
