import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class Solution {
    // 한 번 돌릴 때 마다 숫자가 시계방향으로 한 칸씩 회전
    // 진수 변환이 필요
    // 보물상자의 비밀번호가 만들어 지는 경우의 수는 최대 N/4번
    // 즉, N/4번을 회전 시키면서 모든 경우의 수를 ArrayList에 저장하고 contains를 써서 검사 (16진수로 변환해서 TreeSet에 저장했어도 좋았을듯)
    // 꺼낸 애들을 전부 10진수로 변환해서 정렬 한 후 결과값 도출
    // 요약
    // 1. N/4번 반복문 설정
    // 2. 반복문 내에서  N/4 의 갯수로 자른 값을 저장
    // 3. 저장이 끝났다면 회전
    // 4. 반복문이 끝나면 2진수 변환 후 정렬
    // 5. K번쨰 값을 찾아서 출력
    // 최대 연산횟수는 N/4 * N = 28/7 * 28 =112번
    // Collections.sort의 시간복잡도(O(nlog(n)))
    // 터질 수가 없음
    static int N,K; // N -> 숫자의 갯수 , K -> K번째를 찾기위한 변수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc =1;tc <= T ; tc++) {
            st = new StringTokenizer(br.readLine());
            N= Integer.parseInt(st.nextToken());
            K= Integer.parseInt(st.nextToken());
            int i =1; // 회전수를 체크할 변수
            char[] numList = br.readLine().toCharArray(); // 입력값을 Char 배열로 받기
            int numLength= numList.length; // 입력숫자의 갯수
            int start=0; // 한사이클 회전 시 옆으로 이동시켜줄 변수
            ArrayList<String> result = new ArrayList<>(); // 결과값을 저장할 변수
            while (i<=N/4) { // 사각형의 한 변 만큼 이동하게 되면 초기값이랑 동일해지기 때문에 N/4 까지 반복
                int cnt=0; // char 배열을 돌면서 값들을 탐색할 변수
                int j=0; // 배열을 전부 돌았는지 체크하기 위한 변수
                String str1= ""; // 한변의 16진수값을 저장할 변수
                while(j<numLength) { // 배열 전체 탐색
                    if (cnt!=0 && cnt%(N/4) ==0 && str1 != "" && !result.contains(str1)) { // 시작지점이 아니고, 한변을 다 탐색했고, 문자열이 빈칸이 아니고, 결과리스트에 없는값일경우
                        result.add(str1); // 결과리스트에 16진수 문자열 추가 후
                        str1 = ""; // 초기화
                    }
                    str1+= numList[start+cnt]; // 16진수를 담을 변수를 +
                    cnt++;
                    if ((start+cnt)!=0 && (start+cnt)%numLength==0) cnt = (start*-1); // 만약 시작지점 + 탐색변수가 배열의 크기를 넘어서면 탐색변수를 start*-1로 바꿔줌 (0부터 시작해야하기 때문)
                    j++;
                    if (j==numLength) result.add(str1); // 마지막 반복이면 16진수 문자열을 저장
                }
                start++;
                i++;
            }
            Collections.sort(result,(o1,o2) -> Integer.parseInt(o2,16) - Integer.parseInt(o1,16)); // 내림차순
            System.out.println("#" + tc + " " + Integer.parseInt(result.get(K-1),16));  // 인덱스가 9번부터 시작하기 떄문에 K-1한 값을 16진수로 변환 후 출력
        }
    }
}