package algostudy.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_G5_14891_톱니바퀴 { // 11824kb 80ms
	static List<Integer>[] wheel;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		
		wheel = new ArrayList[4];
		for(int i=0; i<4; i++) {
			wheel[i] = new ArrayList<>();
			String[] string = br.readLine().split("");
			for(int j=0; j<8; j++) 
				wheel[i].add(Integer.parseInt(string[j]));
		}
		
		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken())-1; // 번호
			int dir = Integer.parseInt(st.nextToken()); // 방향
			rotate(num, dir);
		}
		
		int res = 0;
		if(wheel[0].get(0) == 1) res+=1;
		if(wheel[1].get(0) == 1) res+=2;
		if(wheel[2].get(0) == 1) res+=4;
		if(wheel[3].get(0) == 1) res+=8;
		System.out.println(res);
	}
	
	private static void rotate(int num, int dir) {
		// 회전 방향 구하기(리스트 수정하기 전 값으로 회전 방향 구하기 위해)
		int[] index = new int[4];
		index[num] = dir;
		// num의 오른쪽 톱니바퀴
		for(int i=num; i<3; i++) {
			if(wheel[i].get(2)!=wheel[i+1].get(6)) {
				index[i+1] = index[i]*(-1);
			}
			else {
				index[i+1] = 0;
				break;
			}
		}
		// num의 왼쪽 톱니바퀴
		for(int i=num; i>0; i--) {
			if(wheel[i-1].get(2)!=wheel[i].get(6)) {
				index[i-1] = index[i]*(-1);
			}
			else {
				index[i-1] = 0;
				break;
			}
		}
		
		// 회전
		for(int i=num-1; i>=0; i--) {
			if(index[i]==0) break;
			else if(index[i]==1) wheel[i].add(0, wheel[i].remove(7));
			else if(index[i]==-1) wheel[i].add(wheel[i].remove(0)); 
		}
		for(int i=num; i<4; i++) {
			if(index[i]==0) break;
			else if(index[i]==1) wheel[i].add(0, wheel[i].remove(7));
			else if(index[i]==-1) wheel[i].add(wheel[i].remove(0)); 
		}
	}
}
