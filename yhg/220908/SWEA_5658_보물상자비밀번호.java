package swtest;
import java.io.*;
import java.util.*;
public class SWEA_5658_보물상자비밀번호 {
	static int N , K;
	static List<String> list = new ArrayList<>();
	static TreeSet<Integer> set = new TreeSet<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			String[] input = br.readLine().split(" ");
			
			N = Integer.parseInt(input[0]);
			K = Integer.parseInt(input[1]);
			
			input = br.readLine().split("");
			for (String string : input) {
				list.add(string);
			}
			
			int result = 0;
			int count  =  N / 4;
			for (int k = 0; k < list.size()-1; k++) {
				for (int i = 0; i < list.size(); i+=count) {
					String temp = "";
					for (int j = i; j < i + count; j++) {
						temp += list.get(j);
					}
					set.add(Integer.parseInt(temp , 16));
				}
				Collections.rotate(list, 1);
			}
			
			for (int i = 0; i < K; i++) {
				result = set.pollLast();
			}
			list.clear();
			set.clear();
			
			System.out.println("#" + testcase + " " + result);
		}
	}
}
