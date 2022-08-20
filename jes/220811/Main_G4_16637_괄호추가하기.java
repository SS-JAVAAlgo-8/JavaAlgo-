package algostudy.day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Main_G4_16637_괄호추가하기 { // 12104kb 88ms
	static int N, max;
	static String[] sik;
	static List<Integer> input; // 연산자
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sik = br.readLine().split("");
		input = new ArrayList<>();
		for(int i=0; i<sik.length; i++) {
			if(sik[i].equals("+") || sik[i].equals("-") || sik[i].equals("*")) {
				 input.add(i);
			}
		}
		isSelected = new boolean[input.size()];
		max = Integer.MIN_VALUE;
		subset(0);
		System.out.println(max);
	}
	private static void subset(int index) {
		if(index == input.size()) {
			int count= 0;
			for(int i=0; i<input.size(); i++) {
				if(isSelected[i]) count++;
				if(count>=(input.size()+1)/2+1) return;
			}
			int prev = -2;
			List<Integer> operator = new ArrayList<>();
			for(int i=0; i<input.size(); i++) {
				if(isSelected[i]) {
					if(prev+1 != i) {
						prev = i;
						operator.add(input.get(i));
					}
					else {
						return;
					}
				}
			}
			max = Math.max(max, operation(operator));
			return;
		}
		
		isSelected[index] = false;
		subset(index+1);
		isSelected[index] = true;
		subset(index+1);
	}
	
	private static int operation(List<Integer> operator) {
		int res = 0;
		Deque<String> q = new ArrayDeque<>();
		boolean[] isoper = new boolean[N];
		for(int i = 0, index = 0; i<N; i++) {
			if(!operator.isEmpty() && i==operator.get(index) && !operator.get(index).equals(" ")) { // 우선 계산해야 되는 부분 계산
				int a = Integer.parseInt(q.pollLast());
				int b = Integer.parseInt(sik[operator.get(index)+1]);
				if(sik[operator.get(index)].equals("+"))
					q.add(String.valueOf(add(a, Integer.parseInt(sik[operator.get(index)+1]))));
				else if(sik[operator.get(index)].equals("-")) 
					q.add(String.valueOf(sub(a, Integer.parseInt(sik[operator.get(index)+1]))));
				else
					q.add(String.valueOf(mul(a, Integer.parseInt(sik[operator.get(index)+1]))));
				isoper[operator.get(index)-1] = true;
				isoper[operator.get(index)] = true;
				isoper[operator.get(index)+1] = true;
				operator.remove(0);
			}
			else {
				if(!isoper[i]) q.add(sik[i]);
			}
		}
		res = Integer.parseInt(q.poll()); 
		while(!q.isEmpty()) {
			String oper = q.poll();
			int b = Integer.parseInt(q.poll());
			
			if(oper.equals("+")) res += b;
			else if(oper.equals("-")) res -= b;
			else res *= b;
		}
		
		return res;
	}
	
	private static int add(int a, int b) {
		return a+b;
	}
	
	private static int sub(int a, int b) {
		return a-b;
	}
	
	private static int mul(int a, int b) {
		return a*b;
	}
}
