import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
    static class Brick { // 벽돌의 위치와 스플래쉬 숫자를 저장할 클래스
        int x, y, crash;
 
        public Brick(int x, int y, int crash) { 
            this.x = x;
            this.y = y;
            this.crash = crash;
        }
    }
 
    // 구슬위치 완탐 -> 최대 W^4 => 12^4 = 20,736 개의 경우의 수
    // 구슬이 부딪힌 위치에서 사방탐색 BFS
    // 1,536ms
    static int N, W, H; // N -> 구슬 떨어드리는 횟수 , W -> 넓이 , H -> 높이
    static int[][] map; // 초기 맵을 저장할 전역변수
    static int min_value; // 출력할 결과값을 저장할 변수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            min_value =Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            perm(0, new int[N]); // 중복 순열 구하기
             
            System.out.println("#" + tc + " " + min_value);
        }
 
    }
 
    private static void perm(int idx, int[] ball) { // 구슬을 떨어뜨릴 위치를 중복순열로 구하기
        if (idx == N) {
            int[][] map2 = new int[H][W];
            for (int i = 0; i < H; i++) { //경우의 수 마다 새로운 맵을 복사 후
                map2[i] = map[i].clone();
            }
            bfs(ball, 0, map2); // bfs 탐색
            return;
        }
 
        for (int i = 0; i < W; i++) {
            ball[idx] = i;
            perm(idx + 1, ball);
        }
 
    }
 
    static int[] dx = { 1, -1, 0, 0 }; // x축 사방탐색 변수 
    static int[] dy = { 0, 0, 1, -1 }; // y축 사방탐색 변수
 
    private static void bfs(int[] ball, int num, int[][] map2) {
        if (num==N) { // 탐색이 끝났으면
            int brickCount=0;
            for (int i = 0 ; i < H ; i++) {  // 맵을 탐색하면 돌 카운트
                for (int j = 0; j<W ; j++) {
                    if (map2[i][j]!=0) brickCount++;
                }
            }
            min_value=Math.min(min_value, brickCount); // 현재 최소값이랑 비교 후 값 갱신
            return;
        }
         
        Queue<Brick> queue = new LinkedList<>(); // 구슬을 떨어뜨렸을때 최종적으로 부서질 벽돌들을 탐색하기 위한 큐
        boolean[][] visited = new boolean[H][W]; // 구슬을 떨어뜨렸을때 최종적으로 부서질 벽돌들을 메모하기위한 방문배열
        int i = 0;
        while (i<H) { // 구슬을 떨어뜨렸을때 가장 위쪽에 있는 벽돌을 탐색 후 방문체크 + 큐에 추가 후 탈출
            if (map2[i][ball[num]] != 0) {
                visited[i][ball[num]]=true;
                queue.offer(new Brick(i, ball[num], map2[i][ball[num]]));
                break;
            }
            i++;
        }
        while (!queue.isEmpty()) { // 해당 열이 비어있지 않았을때 BFS 시작
            Brick b = queue.poll();
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k < b.crash; k++) {
                    int nx = b.x + dx[j]*k;
                    int ny = b.y + dy[j]*k;
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W || visited[nx][ny] || map2[nx][ny]==0)
                        continue;
                    visited[nx][ny]=true;
                    if (map2[nx][ny]!=1) queue.offer(new Brick(nx,ny,map2[nx][ny])); // 벽돌의 스플래쉬 데미지가 1일경우에는 체크는 해주지만 탐색 시간을 줄이기 위해 큐에는 추가해주지않음
                }
            }
        }
         
        crash(visited,map2); // 체크된 벽돌을 부수는 메서드
         
        move(map2); // 남은 벽돌들을 전부 아래로 땡겨오기
        bfs(ball,num+1,map2);
    }
 
    private static void move(int[][] map2) { // 아래에서 위로 열순서로 탐색
        for (int i= 0; i <W ; i++) { 
            for (int j = H-2 ; j >= 0 ; j--) { // 제일 밑바닥은 어차피 탐색하든말든 의미가 없으니 높이-2 부터 탐색 시작
                if (map2[j][i] !=0 && map2[j+1][i]==0 ) {  // 해당 칸이 벽돌이 있는데 아래칸이 빈 공간일 경우엔
                    int k=1; 
                    while(j+k<H && map2[j+k][i]==0) k++; // 한 칸 아래부터 빈 공간을 체크
                    k--; // 처음에 1로 시작했기 때문에 탈출 후 -1
                    map2[j+k][i]=map2[j][i]; // 벽돌 이동
                    map2[j][i]=0;
                }
            }
        }
    }
 
    private static void crash(boolean[][] visited, int[][] map2) { // 탐색하며 체크된 배열을 모두 부수기
        for (int i = 0 ; i <H ; i ++) {
            for (int j = 0; j<W ; j++) {
                if (visited[i][j]) map2[i][j]=0;
            }
        }
    }
}