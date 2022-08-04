import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		for (int i =0 ; i <N ; i++) {
			//회문,유사회문,해당없음을 체크하기 위한 변수
			int cnt =0;
			String pal = br.readLine();
			// 정방향으로 문자열을 읽어올 리스트와 역방향으로 문자열을 읽어올 리스트를 생성
			ArrayList<Character> pal_1 = new ArrayList<>();
			ArrayList<Character> pal_2 = new ArrayList<>();
			// 각각 정방향,역방향으로 리스트에 문자열 추가
			for (int j =0 ; j < pal.length(); j ++) {
				pal_1.add(pal.charAt(pal.length()-j-1));
				pal_2.add(pal.charAt(j));
			}
			// 정방향과 역방향의 반만큼 검사 하면서 일치하지 않을경우 유사회문인지 체크하는 함수 실행
			for (int j = 0 ; j<=pal_2.size()/2-1; j ++) {
				if (pal_1.get(j)!=pal_2.get(j)) {
					//일단 하나는 틀렸으니 최소 유사회문이기 때문에 cnt를 1로 변환
					cnt=1;
					//정방향,역방향 둘중 하나만 맞아도 유사회문이 가능하기 때문에 cnt_2,cnt_3의 결과 중 최소값을 cnt에 더해주고 검사 탈출
					int cnt_2=check(pal_1,pal_2,j);
					int cnt_3=check(pal_2,pal_1,j);
					cnt+=Math.min(cnt_2, cnt_3);
					break;
				}
			}
			System.out.println(cnt);
		}

		}
		//일치하지 않는 인덱스의 값을 지우고 처음부터 회문 검사 틀린다면 1 맞다면 0을 리턴
	static int check(ArrayList<Character> pal_3,ArrayList<Character> pal_4,int j) {
		ArrayList<Character> pal_1 = new ArrayList<>(pal_3);
		ArrayList<Character> pal_2 = new ArrayList<>(pal_4);
		pal_1.remove(j);
		for (int i=0; i <= pal_1.size()/2-1;i++) {
			if (pal_1.get(i) !=pal_2.get(i)) {
				return 1;
			}
		}
		return 0;
	}
}