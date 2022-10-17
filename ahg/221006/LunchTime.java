package com.ssafy.recur.swea.sol2383;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 걸린 시간 1시간
public class Solution {
    static class Human {
        int x, y;
 
        public Human(int x, int y) {
            this.x = x;
            this.y = y;
 
        }
    }
 
    static class Stairs {
        int x, y, depth;
 
        public Stairs(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
 
    // 사람의 위치를 파악하고 리스트에 저장, 계단의 위치를 파악하고 리스트에 저장
    // 조합을 통해 한 계단에 내려갈 사람들을 체크,체크안된쪽은 다른쪽 계단을 내려갈 사람들
    static int N;
    static Stairs[] stairs; // 계단 정보를 담을 배열
    static int[][] map; // 전체 맵을 입력받을 2차원 배열
    static ArrayList<Human> human; // 사람 위치를 담을 리스트
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            human = new ArrayList<>();
            stairs = new Stairs[2];
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            stairs = new Stairs[2];
            int idx = 0;
            // 초기값 입력받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if (num == 1) {
                        human.add(new Human(i, j));
                    }
                    if (num > 1) {
                        stairs[idx++] = new Stairs(i, j, num);
                    }
                }
            }
            min_value=Integer.MAX_VALUE;
            subset(0, 0);
            System.out.println("#" + tc + " " + min_value);
        }
    }
 
    private static void subset(int idx, int flag) {
        // 부분조합으로 첫번째 계단을 갈 사람과 두번째 계단을 갈 사람을 나눔
        if (idx == human.size()) {
            ArrayList<Human> firstStair = new ArrayList<>();
            ArrayList<Human> secondStair = new ArrayList<>();
            for (int i = 0; i < human.size(); i++) {
                if ((flag & 1 << i) != 0) {
                    firstStair.add(human.get(i));
                } else {
                    secondStair.add(human.get(i));
                }
            }
            // 각 계단에 갈 사람들을 나누었으면 이동 시작
            lunchtime(firstStair, secondStair, 0 , new ArrayList<Integer>(),new ArrayList<Integer>());
            return;
        }
        subset(idx + 1, flag);
        subset(idx + 1, flag | 1 << idx);
    }
 
    // 계단과 사람의 거리보다 현재 지난 시간이 더 클경우 리스트에 추가 후 계단에 대기중인 사람 삭제
    // 그냥 리스트에 추가 하고 아무나 카운팅 해도 되는거 아님? 어차피 결국 다 건너가는 시간은 똑같으니
    static int min_value;
    private static void lunchtime(ArrayList<Human> firstStair, ArrayList<Human> secondStair, int time,ArrayList<Integer> wait,ArrayList<Integer> wait2) {
        // 각 계단에 대기중인사람,갈 사람이 없으면 최소값 갱신하고 종료
        if (firstStair.size()==0 && secondStair.size()==0 && wait.size()==0 && wait2.size()==0) {
            min_value = Math.min(min_value, time);
            return;
        }
        // 맨허튼 거리 값<시간일 경우 계단 대기열에 해당 계단 깊이만큼의 요소 추가
        for (int i = 0; i < firstStair.size(); i++) {
            if (move(firstStair.get(i).x, firstStair.get(i).y, stairs[0].x, stairs[0].y) < time) {
                wait.add(stairs[0].depth);
                firstStair.remove(i);
                i--;
            }
        }
        // 맨허튼 거리 값<시간일 경우 계단 대기열에 해당 계단 깊이만큼의 요소 추가
        for (int i = 0; i < secondStair.size(); i++) {
            if (move(secondStair.get(i).x, secondStair.get(i).y, stairs[1].x, stairs[1].y) < time) {
                wait2.add(stairs[1].depth);
                secondStair.remove(i);
                i--;
            }
        }
        // 계단에 있는 최대 3명의 사람들의 이동 층수를 하나씩 내려주고 0이면 삭제
        int idx2 =3;
        for (int i =0 ; i < idx2;i++) {
            if (wait.size()>i) {
                wait.set(i, wait.get(i)-1);
                if(wait.get(i)==0) {
                    wait.remove(i);
                    i--;
                    idx2--;
                }
            }
        }
        // 계단에 있는 최대 3명의 사람들의 이동 층수를 하나씩 내려주고 0이면 삭제
        idx2=3;
        for (int i =0 ; i <idx2;i++) {
            if (wait2.size()>i) {
                wait2.set(i, wait2.get(i)-1);
                if(wait2.get(i)==0) {
                    wait2.remove(i);
                    i--;
                    idx2--;
                }
            }
        }
        // 시간 +1 해주면서 재귀
        lunchtime(firstStair,secondStair,time+1,wait,wait2);
    }
 
     
    private static int move(int nx, int ny, int dx, int dy) {
        return Math.abs(nx - dx) + Math.abs(ny - dy);
    }
}