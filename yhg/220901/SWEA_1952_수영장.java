package swtest;

import java.io.*;
import java.util.*;

/*
 * 52,324 kb 170 ms
 * 
 * */
public class SWEA_1952_수영장 {
	static int day, month, thmonth, year; // 각 이용권별 가격
	static int[] plan = new int[12]; // 각 월별 일정
	static boolean[] visited; //해당 월을 계산했는지 안했는지
	static int result; // 결과
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			// 각 월별 이용권 입력 받기
			String[] input = br.readLine().split(" ");
			day = Integer.parseInt(input[0]);
			month = Integer.parseInt(input[1]);
			thmonth = Integer.parseInt(input[2]);
			year = Integer.parseInt(input[3]);
			
			//월별 이용 계획 입력 받기
			input = br.readLine().split(" ");
			for (int i = 0; i < input.length; i++) {
				plan[i] = Integer.parseInt(input[i]);
			}
			
			// 필요한 값 초기화
			visited = new boolean[12];
			result = Integer.MAX_VALUE;
			calculate(0 , visited , 0); // 계산을 위한 메서드 (현재 어떤 달을 보고 있는지 , 방분처리한 배열 , 지금까지 계산한 결과)
			
			result = Math.min(result, year); // 1년 계산은 마지막에 한번만 해주면된다. 
			System.out.println("#" + testcase + " " + result); // 결과 출력
		}
		
		
		
	}
	private static void calculate(int monIndex, boolean[] v , int sum) {
		boolean[] visited = copy(v); // 방문배열 복사
		
		// 만약 인덱스가 넘어갔으면 모든 계산을 해본것이므로 결과 비교하고 리턴
		if(monIndex >= 12) {
			result = Math.min(result, sum);
			return;
		}
		
		//아직 계산한 결과가 없으면?
		if(!visited[monIndex]) {
			visited[monIndex] = true; //방문처리
			
			calculate(monIndex+1 , visited , sum + plan[monIndex] * day); // 현재 달을 전부 1일권으로 계산하고 다음달로 넘어가보기
			calculate(monIndex+1 , visited , sum + month); // 현재달을 1달권으로 계산하고 다음달로 넘어가보기
			if(threePossible(monIndex)) {  // 만약 3달 이용권을 사용할 수 있다면
				if(monIndex + 1 < 12)visited[monIndex + 1] = true; // 다음달 방문처리할 수 있으면 방문처리
				if(monIndex + 2 < 12)visited[monIndex + 2] = true; // 다다음달 방문처리할 수 있으면 방문처리
				calculate(monIndex+3 , visited , sum + thmonth); // 다다다음달로 넘어가서 마저 계산
			}
		}
	}
	
	
	private static boolean threePossible(int index) { // 3달 이용권 가능한지 확인하는 메서드
		//만약 계획에 없으면 false 계획을 짤 수 있으면 true;
		for (int i = index; i < index+3; i++) { 
			if(i >= 12)continue;
			if(plan[i] == 0) return false;
		}
		return true;
	}
	
	
	private static boolean[] copy(boolean[] v) { // 방문처리 배열 복사 메서드
		boolean[] visited = new boolean[12];
		for (int i = 0; i < v.length; i++) {
			visited[i] = v[i];
		}
		return visited;
	}
}
