import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Labyrinth {
    static int[][] map;
    static boolean result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 1 ; tc<= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            map =new int[100][]; // 100*@ 의 맵 생성
            result=false;
            for (int i = 0; i < 100 ; i ++) { // 맵 데이터 입력받기
                    map[i]= Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            check(map,new boolean[100][100],1,1); // dfs 메서드
            System.out.println("#" + tc + " "  + (result ? 1 : 0));
        }
    }
    static int[] dx= {1,-1,0,0};
    static int[] dy= {0,0,1,-1};
    static void check(int[][] map,boolean[][] visited ,int x,int y) { 
        for (int i = 0 ; i < 4 ; i ++) {
 
            if (isIn(x+dx[i],y+dy[i],visited)) {
                if (map[x+dx[i]][y+dy[i]] == 3) { 
                    result=true;
                    return;
                }
                visited[x+dx[i]][y+dy[i]]=true;
                check(map,visited,x+dx[i],y+dy[i]);
            }
        }
    }
    private static boolean isIn(int i, int j,boolean[][] visited) {
        if (i>=0 && i<100 && j>=0 && j<100 && (!visited[i][j]) &&( map[i][j] ==3 || map[i][j]==0)) return true;
        return false;
    }
}