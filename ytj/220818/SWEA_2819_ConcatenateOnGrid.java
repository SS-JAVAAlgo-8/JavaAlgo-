package study.day220818.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_2819_ConcatenateOnGrid {

    static int[][] map;
    static int N;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static Set<Integer> set;   // dfs를 통해 완성된 7자리 경우의 수를 Set 에 담아 중복을 제거

    public static void main(String[] args) throws IOException {
    	// DFS (재귀)함수를 통해 7번 반복햇을때 set에 7자리 담기 

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
        	
        	N = 4; 		// 4x4 격차판 고정
        	map = new int[N][N];
        	
            for (int r = 0; r < N; r++) {
            	st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            set = new HashSet<>();
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 모든 좌표에서 시작
                    dfs(0, r, c, map[r][c]);
                }
            }
            System.out.printf("#"+ tc + " " + set.size());
        }
        
    }

    private static void dfs(int len, int r, int c, int pre) {
        // 방문 처리
        if (len == 6) {
            //System.out.println(pre);
            set.add(pre);
            return;
        }

        // 사방 탐색
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isIn(nr, nc)) {
                dfs(len + 1, nr, nc, (pre * 10) + map[nr][nc]); // 1의 자리에 해당 격자판 정보 추가
            }
        }
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

}
