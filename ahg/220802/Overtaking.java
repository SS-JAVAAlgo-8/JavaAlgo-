import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); //비교가 틀릴경우 -> 현재상태 유지,비교가 맞을경우 -> 다음걸로 넘어감
		// 시작위치를 저장할 리스트
		ArrayList<String> start = new ArrayList<>();
		// 나가는 위치를 저장할 리스트
		ArrayList<String> end = new ArrayList<>();
		// 터널 in,out 순서대로 리스트에 추가
		for (int i=0; i <N ; i ++) {
			start.add(br.readLine().trim());
		}
		for (int i=0; i <N ; i ++) {
			end.add(br.readLine().trim());
		}

		int j=0;
		int result=0;
		//나간 위치를 고정 후 들어온 위치를 이동하며 검사
		for (int i =0 ; i<N ; i ++) {
			// 만약 나간위치와 들어간 위치가 동일하면 나간위치리스트의 인덱스를 증가
			if (start.get(j).equals(end.get(i))) {
				j++;
			}else {
			// 나간위치와 들어간 위치가 동일하지 않을경우 추월로 카운트 하고 나간 리스트의 해당 차량을 삭제
				result++;
				start.remove(end.get(i));
			}
		}
		System.out.println(result);
	}
}