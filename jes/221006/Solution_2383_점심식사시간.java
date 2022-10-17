package algostudy.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 *	풀이 시작 시간: 22.10.09 20:00
 * 	풀이 종료 시간: 22.10.09 21:43
 *  풀이 시간 : 설계 10분 + 코딩 30분 + 디버깅 40분 = 80분
 *  
 * 	목표: 모든 사람이 계단을 다 내려가는데 걸린 최소 시간
 * 
 *	조건
 * 	1. N*N 크기의 정사각형 모양의 방
 *  2. P: 사람 / S: 계단 입구
 *  3. 계단 입구까지 이동 시간 = |PR - SR| + |PC - SC|
 *  	PR, PC: 사람 세로, 가로 위치 / SR, SC: 계단 입구 세로, 가로 위치
 *  4. 계단 내려가는 시간
 *     4-1. 계단 입구 도착하고 1분 후(이동 시간+1) 아래칸으로 내려갈 수 있음
 *     4-2. 계단을 동시에 내려가는 사람은 최대 3명
 *     4-3. 이미 계단을 3명이 내려가고 있으면 그 중 한명이 이동 완료할때까지 대기
 *     4-4. 계단 길이 K일 때 계단을 완전히 내려가는 시간은 K분 => 최종적으로 계단 입구 도착 후 K+1분
 * 
 *  풀이 계획
 *  - 부분 집합
 *  
 * 	풀이 방법
 *  1. 각 사람이 어떤 계단을 이용할지 부분집합으로 나누기(subset())
 *  2. 계단 입구까지 이동 시간 계산하여 오름차순 정렬(Collections.sort() + Comparable) 
 *  3. 먼저 입구에 도착한 사람부터 계단 내려가기(arriveTime())
 * 	   3-1. 처음 3명은 무조건 계단 내려갈 수 있으므로 이동 완료시간 계산
 *     3-2. 앞 사람이 이동완료한 시간보다 계단 입구에서 내려가려는 시간이 더 늦을 때(나중일때) 이동 완료시간 계산
 *     3-3. 이미 계단에 내려가고 있는 사람이 있을 때 그 사람이 이동완료한 시간 + 계단 높이
 *  4. 두 계단에서 마지막 사람이 이동완료한 시간의 최대를 구함
 *  5. 4에서 구한 시간을 통해 모든 사람이 계단을 다 내려가는데 걸린 최소 시간 구함
 *  
 */

public class Solution_2383_점심식사시간 { // 32784kb 128ms
	static class Person implements Comparable<Person>{
		int x, y, time;
		public Person(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	static class Stair{
		int x, y, len;
		public Stair(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	
	static int size, ans;
	static Stair[] stairs;
	static List<Person> person;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			stairs = new Stair[2];
			person = new ArrayList<>();
			int idx = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num==1) { // 사람(위치, 시간)
						person.add(new Person(i, j, 0));
					}
					else if(num>1) { // 계단(위치, 높이)
						stairs[idx++] = new Stair(i, j, num);
					}
				}
			}
			size = person.size();
			ans = Integer.MAX_VALUE;
			isSelected = new boolean[size];
			subset(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void subset(int index) {
		// 계단 나누기(계단1 or 계단2)
		if(index==size) { 
			ArrayList<Person> stair_1 = new ArrayList<>();
			ArrayList<Person> stair_2 = new ArrayList<>();
			for(int i=0; i<size; i++) {
				int x = person.get(i).x;
				int y = person.get(i).y;
				// 계단 1인 사람 리스트 생성
				if(isSelected[i]) stair_1.add(new Person(x, y, count(x, y, 0)));
				// 계단 2인 사람 리스트 생성
				else stair_2.add(new Person(x, y, count(x, y, 1)));
			}
			
			// 오름차순 정렬
			Collections.sort(stair_1);
			Collections.sort(stair_2);
			
			// 계단 1 마지막 사람, 계단 2 마지막 사람 도착시간 비교
			int arrive = Math.max(arriveTime(stair_1, 0), arriveTime(stair_2, 1));
			
			// 최솟값 갱신
			ans = Math.min(ans, arrive);
			return;
		}
		
		isSelected[index] = false;
		subset(index+1);
		isSelected[index] = true;
		subset(index+1);
	}

	private static int arriveTime(ArrayList<Person> stair_person, int index) {
		int num = stair_person.size();
		if(num <= 3) { // 대기 인원이 생기지 않는 경우
			for(int i=0; i<num; i++) {
				stair_person.get(i).time += stairs[index].len+1; 
			}
		}
		else { // 대기 인원이 생기는 경우
			for(int i=0; i<3; i++) {
				stair_person.get(i).time += stairs[index].len+1;
			}
			for(int i=3; i<num; i++) {
				if(stair_person.get(i).time+1 >= stair_person.get(i-3).time) { // 새로운 사람이 내려가려는 시간에 이미 앞 사람이 계단 아래에 도착한 경우
					stair_person.get(i).time += stairs[index].len+1;
				}
				else { // 새로운 사람이 도착한 시간에 앞 사람이 아직 내려가는 중인 경우
					stair_person.get(i).time = stair_person.get(i-3).time + stairs[index].len;
				}
			}
		}
		return (stair_person.size()==0) ? 0 : stair_person.get(stair_person.size()-1).time;
	}

	// 사람이 계단까지 가는 거리
	private static int count(int x, int y, int i) {
		return (i==0) ? Math.abs(x-stairs[0].x)+Math.abs(y-stairs[0].y) : Math.abs(x-stairs[1].x)+Math.abs(y-stairs[1].y);
	}
}
