import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
 
public class ConcatenateNumberOnGrid {
    static int[][] map;
    static int[] dx= {-1,1,0,0};
    static int[] dy= {0,0,1,-1};
    static Set<String> set;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++ ) {
            map = new int[4][4];
            set = new LinkedHashSet<>(); //중복을 지우기 위해 set 사용
            for (int i = 0 ; i <4 ; i ++) { // 초기데이터 입력받기
                st= new StringTokenizer(br.readLine());
                for (int j =0 ; j < 4; j ++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
             
            int[] result=new int[7]; //숫자를 저장할 배열생성
            for (int i = 0; i<4; i ++) {
                for (int j = 0; j<4 ; j++) {
                    result[0]= map[i][j]; // 첫번쨰에 시작문자를 입력 받음
                    dfs(1,i,j,result.clone()); //입력받은걸 기준으로 dfs탐색
                }
            }
            System.out.println("#" + tc + " " + set.size()); // 나온 문자열의 갯수를 알기 위해 set의 size를 출력
        }
                 
    }
    static void dfs(int idx,int x,int y,int[] result) {
        if(idx == 7) { // 모든 문자가 차면 
            set.add(Arrays.toString(result));  // 배열을 문자열로 바꿔서 set함수에 저장해 중복값 자동으로 삭제
            return;
        }
        for (int i = 0 ; i <4 ; i ++) { //4방탐색 하면서 dfs 탐색
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx <0 || nx>=4 || ny <0 || ny>=4 ) continue;
            result[idx]=map[nx][ny];
            dfs(idx+1,nx,ny,result);
        }
    }
}