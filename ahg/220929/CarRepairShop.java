import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
    // 고객이 이용했던 접수 창구번호와 장비 창구번호가 존재
    // N개의 접수창구와 M개의 장비창구가 있다
    // 접수창고에서 고장 접수 -> 장비창구에서 차량 정비
    // i에서 고객 한명의 고장을 접수하는데 걸리는 시간은 ai
    // j에서 고객 한명의 고장을 접수하는데 걸리는 시간은 bj
    // 지금까지 방문한 고객은 K명, 도착하는대로 번호 받음
    // 고객번호 k 고객이 차량 정비소에 도착하는 시간은 tk
    // 정비소에 도착하면 빈접수창고에 가서 접수
    // 빈 접수창고가 없으면 대기
    // 접수창구의 우선순위 1. 여러명 기다리면 고객번호가 낮은 순서,빈 창구가 여러곳이면 창구번호가 낮은 순
    // 정비창구의 우선순위 1, 먼저 기다리는 고객 우선,동시에 접수하고 정비창구에 이동하면 창구번호가 작은 고객이 우선,빈 창구가 여러 곳이면
    // 창구번호가 작은곳부터
    // 없을경우 -1
    // 1.접수창구 대기열,정비창구 대기열 큐를 생성
    // 2.반복 or 재귀를 돌리면서 고객이 왔을 경우 접수창구 배열에 넣기
    // 3.시간이 될때마다 오는 고객들을 큐에 넣어줌
    // 4.고객이 들어간 순간부터 시간초를 재서 ai초에 도달하면 탈출후 정비창구 큐에 넣기
    // 5.정비창구도 접수창고와 비슷한 로직을 사용하는데 순차적 탐색을 해서 접수창구가 작은 순서 먼저 큐에 넣어주는 식으로 해결
    // N -> 접수창구 갯수, M -> 정비창구 갯수, K -> 차량정비소를 방문한 고객의 수
    // A -> 지갑 두고간 손님이 방문한 접수창고,B -> 지갑 두고간 손님이 방문한 정비창고
    // a1 -> 접수창고의 처리시간 , b1 -> 정비창구의 처리시간 tk -> k번쨰 고객이 차량 정비소를 방문하는 시간
    static class Customer {
        int i, t;
        public Customer(int i, int t) {
            this.i = i;
            this.t = t;
        }
    }

    static int result;
    static int N, M, K, A, B;
    static int[] ai, bi;
    static boolean[] check;
    static int[][] recep,repair;
    static ArrayList<Customer> ti;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            // 초기값 입력받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            recep = new int[N][2];
            repair = new int[M][2];
            ai = new int[N];
            bi = new int[M];
            ti = new ArrayList<>();
            result=0;
            check = new boolean[K+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                ai[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                bi[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                ti.add(new Customer(i + 1, Integer.parseInt(st.nextToken())));
            }
 
            Queue<Integer> wait = new LinkedList<>(); // 접수창고 웨이팅 큐
            Queue<Integer> wait2 = new LinkedList<>(); // 정비창고 웨이팅 큐
            reception(0, wait, wait2);

            // 지갑 잃은사람의 루트를 한사람도 지나치지 않았다면 -1 아니면 정상출력
            System.out.println("#" + tc +  " " +  (result == 0 ? -1 : result));
        }
    }
 
    public static void reception(int time, Queue<Integer> recepwait,Queue<Integer> repairwait) { 
        if (ti.isEmpty() && recepwait.isEmpty() && repairwait.isEmpty()) { //각 창고와 대기 손님이 없고 창구안에도 손님이 하나도 없으면 리턴
            boolean c = false;
            for (int i=0 ; i <recep.length; i ++) { 
                if (recep[i][0]!=0) c= true;
            }
            for (int i=0 ; i <repair.length; i ++) {
                if (repair[i][0]!=0) c= true;
            }
            if (!c) return;
        }
         
        for (int i = 0; i <repair.length;i++) { // 정비창고 처리시간을 1씩 더해주고 처리가 다 되었으면 해당 창구를 비워줌
            if (repair[i][0]!=0) {
                repair[i][1]++;
                if(bi[i]==repair[i][1]) {
                    repair[i][0]=0;
                    repair[i][1]=0;
                }
            }
        }
        // 접수창고에 사람이 있을 경우 해당 시간 +1 해주고 만약 ai랑 같을경우 정비창고대기열로 이동
        for (int i =0; i <recep.length; i++) {
            if (recep[i][0]!=0) {
                recep[i][1]++;
                if(ai[i]==recep[i][1]) {
                    // 정비창고대기열로 이동
                    repairwait.offer(recep[i][0]);
                    recep[i][0]=0;
                    recep[i][1]=0;
                }
            }
        }
         
        for (int i = 0; i < ti.size(); i++) { // 고객이 도착하면 접수창구에 넣어줌
            if (ti.get(i).t == time) {
                recepwait.offer(ti.get(i).i);
                ti.remove(i);
                i--;
            }
        }
        for (int i = 0; i < repair.length; i++) { // 정비창구 대기열에 있는 사람들을 비어있는 각 정비창구에 넣어주고, 지갑잃은사람이 간 접수창구,정비창구 와 같으면 result에 고객번호만큼 더해줌
            if (repairwait.isEmpty())
                break;
            if (repair[i][0] == 0) {
                repair[i][0] = repairwait.poll();
                if (i==B-1 && check[repair[i][0]]) result+=(repair[i][0]);
            }
        }
        // 대기열에서 빼면서 접수창고에 하나씩 넣어주기
        for (int i = 0; i < recep.length; i++) {
            if (recepwait.isEmpty())
                break;
            if (recep[i][0] == 0) {
                recep[i][0] = recepwait.poll();
                if (i==A-1) check[recep[i][0]]=true;
            }
        }
        reception(time + 1, recepwait,repairwait); // 시간을 1초씩 증가시키면서 재귀
    }
}