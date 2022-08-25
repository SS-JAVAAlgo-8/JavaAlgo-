package study.day220818.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1342_LuckyString {

	static boolean[] isSeleced;
	static int totalCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		char[] ans = new char[input.length];
		Set<String> result = new HashSet<>();
		isSeleced = new boolean[input.length];
		
		permutation(0, input, result, ans);
		System.out.println(result.size());
		
	}

	private static void permutation(int cnt, char[] input, Set<String> result, char[] ans) {
		
		if(cnt == input.length) {
			
			result.add(String.valueOf(ans));
			
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			
			if(isSeleced[i]) continue;
			
			if(cnt > 0 && ans[cnt-1] == input[i]) continue;
			ans[cnt] = input[i];
				
			isSeleced[i] = true;
			permutation(cnt + 1, input, result, ans);
			isSeleced[i] = false;
		}
	}

}
