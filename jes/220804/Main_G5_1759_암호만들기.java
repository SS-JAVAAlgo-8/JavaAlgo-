package algostudy.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 모음, 자음 따로 받아와서 모음은 부분집합, 그안에서 자음은 조합으로 암호 생성
// 최종적으로 만들어진 암호 문자열 정렬하고(모음+자음) TreeSet에 넣어 사전순으로 출력
public class Main_G5_1759_암호만들기 {
	static List<String> mo, ja; // 모음, 자음
	static List<String> string; // 결과 문자열
	static Set<String> result; // 문자열 정렬하여 출력할 때 사용
	static boolean[] isSelected; // 모음 부분집합 때 사용
	static String[] tmp; // 자음 조합 담을 배열
	static int L, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 만들어야 할 문자 개수
		C = Integer.parseInt(st.nextToken()); // 주어지는 문자 개수
		
		mo = new ArrayList<>();
		ja = new ArrayList<>();
		string = new ArrayList<>();
		result = new TreeSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			String tmp = st.nextToken();
			if(tmp.equals("a") || tmp.equals("e") || tmp.equals("i") || tmp.equals("o") || tmp.equals("u"))
				mo.add(tmp);
			else ja.add(tmp);
		}
		
		isSelected = new boolean[mo.size()];
		subset(0);
		for(String r: result) System.out.println(r);
	}
	
	// 모음 선택
	private static void subset(int index) {
		if(index == mo.size()) {
			for(int i=0; i<mo.size(); i++)
				if(isSelected[i]) string.add(mo.get(i));
			// 모음이 선택되었고 자음이 2개 이상 선택될 수 있는 경우 자음 선택
			if(string.size()!=0 && L-string.size()>=2) {
				tmp = new String[L-string.size()];
				combination(0, 0, L-string.size(), string.size());
			}
			string.clear();
			return;
		}
		isSelected[index] = false;
		subset(index+1);
		isSelected[index] = true;
		subset(index+1);
	}
	
	// 모음에 따른 자음 조합
	private static void combination(int start, int cnt, int R, int index) {
		if(cnt == R) {
			for(int i=0; i<R; i++) {
				if(string.size() <= i+index) string.add(tmp[i]);
				else {
					string.remove(i + index);
					string.add(i+index, tmp[i]);
				}
			}
			List<String> list = new ArrayList<>();
			list.addAll(string);
			Collections.sort(list);
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<L; i++) {
				sb.append(list.get(i));
			}
			result.add(sb.toString());
			return;
		}
		
		for(int i=start; i<ja.size(); i++) {
			tmp[cnt] = ja.get(i);
			combination(i+1, cnt+1, R, index);
		}
	}
}
