package algostudy.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_17281_야구공 { // 77688kb 488ms
	static int N, max = 0;
	static int[][] score;
	static int[] order, input, loc;
	static boolean[] isSelected;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N][9];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		input = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		do {
			total(input);
		}while(np(input));
		System.out.println(max);
	}
	
	public static boolean np(int[] input) {
		int N = input.length;
		int i = N-1;
		while(i>0 && input[i-1]>=input[i]) --i;
		if(i==0) return false;
		
		int j = N-1;
		while(input[i-1]>=input[j]) --j;
		swap(input, i-1, j);
		
		int k = N-1;
		while(i<k) swap(input, i++, k--);
		return true;
	}
	
	public static void swap(int[] input, int i, int j) {
		int tmp = input[i];
		input[i] = input[j];
		input[j] = tmp;
	}
	
	private static void total(int[] input) {
		int[] order = new int[input.length+1];
		order[0] = input[0];
		order[1] = input[1];
		order[2] = input[2];
		order[3] = 0;
		for(int i=4; i<9; i++) {
			order[i] = input[i-1];
		}
		int i = 0;
		int sum = 0;
		for(int n=0; n<N; n++) {
			loc = new int[4];
			int out = 0;
			while(out < 3){
				if(score[n][order[i]]==0) out++;
				else {
					move(score[n][order[i]]);
				}
				i = (i+1) % 9;
			}
			sum += loc[3];
		}
		max = Math.max(max, sum);
		return;
	}
	
	private static void move(int num) {
		for(int i=2; i>=0; i--) { // 3루타 ~ 1루타
			if(loc[i]!=0) { // 이동할 선수가 있을 때
				if(i+num < 3) { // 2, 3 루타에 남는 경우
					loc[i+num] = 1;
					loc[i] = 0;
				}
				else{ // 홈에 들어가는 경우
					loc[3]++;
					loc[i] = 0;
				}	
			}
		}
		if(num==4) loc[3]++; // 홈런인 경우 바로 홈에 들어가는 주자
		else loc[num-1] = 1; // 안타, 2루타, 3루타 치고 베이스에 남는 주자
	}
}
