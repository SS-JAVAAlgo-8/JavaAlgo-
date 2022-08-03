import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17609 {
    // 회문이 완성되기전 안된다는 선언
	static int result = 2;
    // 회문인지 아닌지 체크하기 위한 매서드
	public static void check(int left, int right, String[] list, int count) {
		// 모든 문자에 대한 체크가 끝났을때 count = 0이라면 회문 완성 , 1이면 유사회문 
        if (left >= right) {
			result = Math.min(result, count);
			return;
        // 2면 회문 불가 판정
		}else if(count == 2) {
			result = Math.min(result, 2);
			return ;
		}
		// 문자열이 같으면 인덱스 동시 이동 , 문자열이 다르면 한쪽만 이동시켜 확인한다. 
		if(list[left].equals(list[right])) check(left+1 , right - 1 , list , count);
		else {
			check(left+1 , right , list , count +1);
			check(left , right - 1 , list , count + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			result = 2;
			String[] list = br.readLine().split("");
			int left = 0;
			int right = list.length-1;
			check(left, right, list, 0);
			System.out.println(result);
		}
	}
}
