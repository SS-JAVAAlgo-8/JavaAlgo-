package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1759 {
	static List<String> result = new ArrayList<>();
	static String[] sList;
	static boolean[] visited;
	static String[] temp;
	static StringBuilder sb;
	static int c;
	public static void check(String now, int end, int index, int consonants, int vowels) {
		if (now.length() == end && consonants >= 2 && vowels >= 1) {
				sb.append(now + "\n");
				return;
		}
		
		if(index == c) return;

		String next = now + sList[index];
		int c = consonants;
		int v = vowels;
		boolean c_flag = false;
		boolean v_flag = false;
		if (sList[index].equals("a") || sList[index].equals("e") || sList[index].equals("i")
				|| sList[index].equals("o") || sList[index].equals("u")) {
			v += 1;
			v_flag = true;
		} else {
			c_flag = true;
			c += 1;
		}
		
		check(next, end, index + 1, c, v);
		if(v_flag)  v-=1;
		if(c_flag)  c-=1;
		check(now, end, index + 1, c, v);
		
		}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();;
		int l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		visited = new boolean[c];
		sList = br.readLine().split(" ");
		Arrays.sort(sList);
		check("", l, 0, 0, 0);
		System.out.println(sb);
	}
}
