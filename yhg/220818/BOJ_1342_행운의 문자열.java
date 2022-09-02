import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int[] cnt = new int[26];
	static int[] visited = new int[26];
	static String input;
	static Map<Character , Integer> alpha = new HashMap<>();
	static int result = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		input = br.readLine(); 

        //알파벳 별로 몇개 사용했는지 확인하기 위한 map
		for(int i = 0; i<26; i++) {
			alpha.put((char)('a' + i ), 0);
		}
        //입력받은 문자열 내의 알파벳 확인하면서 개수 늘리기
		for (int i = 0; i < input.length(); i++) {
			cnt[input.charAt(i) - 'a'] +=1;
			alpha.put(input.charAt(i),alpha.get(input.charAt(i)) +1);
		}
        //조합하러 가기
		check(' ' , visited , 0 );
		System.out.println(result);
	}
	private static void check(char prev, int[] visited2, int c ) {
        
        //입력받은 알파벳을 전부 사용하면 탈출
		if(c == input.length()) {
			result +=1;
			return;
		}
        //알파벳 구성을 돌면서 이전 알파벳과 다르고 아직 사용할 수 있는 알파벳이 있다면 visited를 현재 조합을 만든만큼만 복사하고 다음 문자열 체크
		for (char key : alpha.keySet()) {
		    if(prev != key  && visited2[key-'a'] < cnt[key-'a']) {
		    	int[] newVisited = Arrays.copyOf(visited2, 26);
		    	newVisited[key-'a'] +=1;
		    	check(key , newVisited , c+1);
		    }
		}
	}
}
