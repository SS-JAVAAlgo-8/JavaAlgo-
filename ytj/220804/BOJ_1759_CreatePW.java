package study.day220804.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_1759_CreatePW { // 13,804 KB , 156 ms

	static boolean [] isSelected;
	static char[] pick;
	static int consonants, vowels; // 자음과 모음
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int pickNum = sc.nextInt();
		int alphNum = sc.nextInt();
		
		
		List<String> sorting = new ArrayList<>();
		for (int i = 0; i < alphNum; i++) {
			sorting.add(sc.next());
		}
		
		Collections.sort(sorting);
		
		String str[] = sorting.toArray(new String[alphNum]);
		char list[] = new char[alphNum];
		for (int i = 0; i < alphNum; i++) {
			list[i] = str[i].charAt(0);
		}
		// char[] list = sc.nextLine().replaceAll(" ","").toCharArray();
		
		isSelected = new boolean[alphNum];
		pick = new char[pickNum];
		
		perm(0, pickNum, list, isSelected);
		
	}
	
	private static void perm(int cnt, int pickNum, char[] list, boolean[] isSelected) {
		
		if(cnt == pickNum) {
			
			if(Asc(pickNum) && CV(pickNum)) {
				System.out.println(pick);
			}
			vowels = 0;
			consonants = 0;
			return;
		}
		
		for(int i = 0; i < list.length; i++) {
			if(isSelected[i]) continue;
			
			if(cnt > 1 && pick[cnt-1] > list[i]) continue;
			
			pick[cnt] = list[i];
			
			isSelected[i] = true;
			perm(cnt+1, pickNum, list, isSelected);
			isSelected[i] = false;
		}
		
	}

	private static boolean CV(int pickNum) {
		for (int i = 0; i < pickNum; i++) {
			if(pick[i]== 'a' || pick[i]== 'e' || pick[i]== 'i' || pick[i]== 'o' || pick[i]== 'u')
				vowels++;
			else
				consonants++;
		}
		
		if(vowels >= 1 && consonants >= 2)
			return true;
		else
			return false;
	}

	private static boolean Asc(int pickNum) {
		for (int i = 0; i < pickNum - 1; i++) {
			if( pick[i] > pick[i+1] )
				return false;
		}
		
		return true;
	}

}
