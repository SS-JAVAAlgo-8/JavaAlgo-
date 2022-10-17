package com.ssafy.recur.swea.sol2382;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 걸린시간 : 1시간 15분
public class Solution {
	
	// 미생물 군집들은 한시간마다 다음 셀로 이동
	// 미생물들이 각 사이드에 도착하면 절반으로 줄고 이동방향이 반대로 바뀜
	// 만약 미생물이 홀수일 경우 2로 나누고 소수점 이하를 버린 값 -> 1마리일경우 살아남은 미생물 수가 0
	// 만약 이동 후에 같은 셀에 군집이 두개 이상 있다면 가장 많은 쪽의 방향으로 정해진다
	// M 시간 후 남아있는 미생물 수의 총합 구하기
	// 1 상, 2 하, 3 좌 , 4 우
	static class Microbe {
		int x, y, num, direc;

		public Microbe(int x, int y, int num, int direc) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.direc = direc;
		}
	}

	static int N, M, K; // N -> 한변의 셀의 개수 , M -> 격리 시간, K -> 미생물 군집의 개수
	static ArrayList<Microbe> micro; // 미생물정보를 저장할 리스트
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			micro = new ArrayList<>();
			result = 0;
            // 초기값 입력받기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				Microbe m = new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1);
				micro.add(m);
			}
			move(0);
			System.out.println("#" + tc + " " + result);
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void move(int time) {
        // 격리시간이 끝나면 결과값 갱신하고 탈출
		if (time >= M) {
			for (int i = 0; i < micro.size() ; i++) {
				result+=micro.get(i).num;
			}
			return;
		}
		int[][] map3 = new int[N][N]; // 같은 위치에 3개 이상의 미생물이 겹치게 될 경우 최대값을 구하기 위한 2차원 배열
		Microbe[][] map2 = new Microbe[N][N]; // 미생물의 이동 정보와 합쳐질 경우 그 정보들을 저장하기 위한 2차원 배열
		for (int i = 0; i < micro.size(); i++) {
			micro.get(i).x += dx[micro.get(i).direc];
			micro.get(i).y += dy[micro.get(i).direc];
			// 움직인 곳이 가장자리일 경우 num을 반으로 깎고 direc을 +2%4연산 해줌
            // 해당 위치에 다른 미생물이 있을 경우
			if (map2[micro.get(i).x][micro.get(i).y] != null) {
                // 현재 검사하는 미생물과 해당 위치에 있는 미생물의 수 를 비교해서 합친값, 같은 셀 중의 최대값과 방향을 갱신
				if (map3[micro.get(i).x][micro.get(i).y]>micro.get(i).num) {
					map2[micro.get(i).x][micro.get(i).y].num += micro.get(i).num;
					micro.remove(micro.get(i));
					i--;
				}else {
					map2[micro.get(i).x][micro.get(i).y].num += micro.get(i).num;
					map2[micro.get(i).x][micro.get(i).y].direc=micro.get(i).direc;
					map3[micro.get(i).x][micro.get(i).y]=micro.get(i).num;
					micro.remove(micro.get(i));
					i--;
				}
            // 해당위치에 다른 미생물이 없을 경우 현재 최대값과 미생물 정보를 저장
			} else {
				map2[micro.get(i).x][micro.get(i).y] = micro.get(i);
				map3[micro.get(i).x][micro.get(i).y]= micro.get(i).num;
			}
		}
		for (int i =0 ; i<micro.size();i++) {
            // 약품이 칠해져 있을 경우
			if(isIn(micro.get(i).x,micro.get(i).y)) {
                // 미생물의 수를 반으로 줄이고
				micro.get(i).num/=2;
                // 자신이 보고있는 방향의 반대방향을 보게끔 값 갱신
				if(micro.get(i).direc == 0 || micro.get(i).direc == 1) {
					micro.get(i).direc=micro.get(i).direc == 1 ? 0 : 1;
				}
				else {					
					micro.get(i).direc=micro.get(i).direc == 2 ? 3 : 2; 
				}
			}
		}
        // 시간을 1 늘리고 재귀
		move(time + 1);
	}

	private static boolean isIn(int x, int y) {
		if (x<= 0 || x>=N-1 || y<=0 || y>=N-1) return true;
		return false;
	}
}
