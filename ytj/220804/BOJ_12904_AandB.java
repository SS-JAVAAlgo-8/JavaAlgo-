package study.day220804.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 *  input , result 의  각각의 A, B 갯수 비교 
 *  두 문자열이 길이 =  A의 갯수 차 + B 갯수 차
 *  
 *  ex) 3 = 2 + 1 , 
 *  
 *  	A A B 로 순열 , 순열이 중복된 결과는 게임 시행 X 
*/
public class BOJ_12904_AandB {
	
	static boolean[] isSelected;
	static int iANum;
	static int iBNum;
	
	static int rANum;
	static int rBNum;
	
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char [] input = br.readLine().toCharArray();
		char [] result = br.readLine().toCharArray();
		
		List<Character> inputlist = new ArrayList<>();
		List<Character> resultlist = new ArrayList<>();		
		
		for (int i = 0; i < input.length; i++) {
			inputlist.add(input[i]);
			if(input[i] == 'A')
				iANum++;
			else
				iBNum++;
		}
		
		for (int i = 0; i < result.length; i++) {
			resultlist.add(result[i]);
			if(result[i] == 'A')
				rANum++;
			else
				rBNum++;
		}
		
		List<Character> permlist = new ArrayList<>();
		for (int i = 0; i < rANum - iANum; i++) {
			permlist.add('A');
		}
		for (int i = 0; i < rBNum - iBNum; i++) {
			permlist.add('B');
		}
		
		//String str = String.valueOf(input);
		
		do {
			// input copy
			List<Character> initlist = new ArrayList<>(inputlist);
			for (int i = 0; i < permlist.size(); i++) {
				if(permlist.get(i) == 'A')
					initlist.add('A');
				else if(permlist.get(i) == 'B')
					initlist.add('B');
			}
			// result 비교, 같은게 잇으면 1 
			if(initlist.equals(resultlist))
				ans = 1;
		}
		while(nextPermutation(permlist));
		
		System.out.println(ans);
	}
	

	private static boolean nextPermutation(List<Character> permlist) {
		int firstPeak = permlist.size() - 1;
		
		while(firstPeak > 0 && permlist.get(firstPeak - 1) >= permlist.get(firstPeak)) {
			firstPeak--;
		}
		
		if(firstPeak == 0) {
			return false;
		}
		
		int gtBeforeFirstPeak = permlist.size() - 1;
		while(permlist.get(firstPeak - 1) >= permlist.get(gtBeforeFirstPeak)) {
			gtBeforeFirstPeak--;
		}
		
		swap(firstPeak - 1, gtBeforeFirstPeak, permlist);
		
		for (int reverseIdx = permlist.size() - 1; reverseIdx > firstPeak; firstPeak++, reverseIdx--) {
			swap(firstPeak, reverseIdx, permlist);
		}
		
		return true;
	}

	private static void swap(int i, int j, List<Character> permlist) {
		char temp = permlist.get(i);
        permlist.set(i, permlist.get(j));
        permlist.set(j, temp);
		
	}


}
