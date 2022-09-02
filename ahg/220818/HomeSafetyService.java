import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
class House{
    int x;
    int y;
    public House(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
public class HomeSafetyService {
    static int N, M,K;
    static int max_value;
    static ArrayList<House> house;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            house = new ArrayList<>(); //집들의 위치를 저장할 리스트
            for (int i = 0; i<N ; i++) {
                st =new StringTokenizer(br.readLine());
                for (int j = 0 ; j<N ; j++) {
                    if(st.nextToken().equals("1"))  house.add(new House(i,j)); // 집 위치를 발견하면 좌표 저장
                }
            }
            max_value=0;
            K=1;
            while(K!=N+2) { // K를 N+2 일 때 까지 늘려주면서 체크
                check();
                K++;
            }
            System.out.println("#" + tc +  " " + max_value);
        }
    }
    static void check() { // 현재 K의 크기에서 맵을 완전탐색
        for (int i =0 ; i <N ; i++) {
            for (int j = 0 ; j<N ; j++) {
                check_house(i,j);
            }
        }
    }
     
    static void check_house(int i, int j) { // 완전탐색에서 길이 범위 내를 체크해서 손해인지아닌지 체크하고 아니라면 최대수치를 비교,갱신
        int cnt=0;
        for (int l =0 ; l <house.size() ; l++) {
            if (Math.abs(i-house.get(l).x) + Math.abs(j-house.get(l).y)<K) {
                cnt++;
            }
        }
        if (K*K+(K-1)*(K-1)<=cnt*M){
            max_value=Math.max(max_value, cnt);
        }
    }
}