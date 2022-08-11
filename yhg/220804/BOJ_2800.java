package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class BOJ_2800 {
	static List<String> result = new ArrayList<>();
	static List<int[]> index = new ArrayList<>();
	static List<int[]> clist = new ArrayList<>();
	static String[] input;

	public static void com(int s, int id) {
		if (clist.size() == s) {
			make();
			return;
		}

		for (int i = id; i < index.size(); i++) {
			clist.add(index.get(i));
			com(s, i + 1);
			clist.remove(clist.size() - 1);
		}
	}

	public static void make() {
		String[] temp = input.clone();
		for (int[] is : clist) {
			temp[is[0]] = "";
			temp[is[1]] = "";
			if (result.contains(String.join("", temp)) == false)
				result.add(String.join("", temp));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine().split("");

		Deque<Integer> start = new ArrayDeque<>();
		Deque<Integer> end = new ArrayDeque<>();

		for (int i = 0; i < input.length; i++) {
			if (input[i].equals("("))
				start.addLast(i);
			else if (input[i].equals(")")) {
				index.add(new int[] { start.removeLast(), i });
			}

		}

		for (int i = 0; i < index.size() + 1; i++) {
			com(i, 0);
		}
		Collections.sort(result);
		for (String string : result) {
			System.out.println(string);
		}
	}
}
