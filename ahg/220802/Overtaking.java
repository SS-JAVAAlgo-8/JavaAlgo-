import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); //비교가 틀릴경우 -> 현재상태 유지,비교가 맞을경우 -> 다음걸ㄹ ㅗ넘어감
		ArrayList<String> start = new ArrayList<>();
		ArrayList<String> end = new ArrayList<>();
		for (int i=0; i <N ; i ++) {
			start.add(br.readLine().trim());
		}
		for (int i=0; i <N ; i ++) {
			end.add(br.readLine().trim());
		}
		int j=0;
		int result=0;
		for (int i =0 ; i<N ; i ++) {
			if (start.get(j).equals(end.get(i))) {
				j++;
			}else {
				result++;
				start.remove(end.get(i));
			}
		}
		System.out.println(result);
	}
}