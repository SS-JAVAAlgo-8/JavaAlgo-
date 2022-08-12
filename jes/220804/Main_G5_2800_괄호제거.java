package algostudy.day0804;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

// 부분 집합 알고리즘 사용
public class Main_G5_2800_괄호제거 {
	static boolean[] isSelected;
	static List<Point> par;
	static String[] str;
	static Set<String> res;
	
	static class Index{
		String par;
		int idx;
		
		public Index(String par, int idx) {
			super();
			this.par = par;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 처음 수식 저장할 리스트 생성
		str = br.readLine().split("");
		// 괄호 쌍 찾기 위한 스택
		Stack<Index> st = new Stack<>();
		// 괄호 ( ) 위치 저장 리스트
		par = new ArrayList<>();
		// 괄호 쌍 찾아 위치 담기
		for(int i=0; i<str.length; i++) {
			if(str[i].equals("("))
				st.push(new Index(str[i], i));
			else if(str[i].equals(")"))
				// Point 클래스 -> (int, int) 담을 수 있음
				par.add(new Point(st.pop().idx, i));
		}
		isSelected = new boolean[par.size()];
		
		// 사전 순 정렬하기 위해 TreeSet사용
		res = new TreeSet<>();
		subset(0);
		for(String r : res)
			System.out.println(r);
	}

	private static void subset(int index) {
		// 괄호쌍 개수랑 같을 때
		if(index == par.size()) {
			StringBuilder sb = new StringBuilder();
			boolean[] isPass = new boolean[str.length];
			for(int i=0; i<par.size(); i++) {
				// 괄호쌍 각각의 위치 체크(true면 출력X)
				if(isSelected[i]) {
					// isSelected는 괄호 쌍, isPass는 괄호 쌍의 ( ) 좌표 체크
					isPass[par.get(i).x] = true;
					isPass[par.get(i).y] = true;
				}
			}
			// 앞에서 체크되지 않은(false) 부분만 sb에 담기
			for(int i=0; i<str.length; i++){
				if(!isPass[i]) {
					sb.append(str[i]);
				}
			}
			// sb에 담은 길이와 원래 문자열의 길이가 다를 때만 트리셋에 추가
			// why? 부분집합에서 공집합 제외하기 위해서! -> 다른 방법 있는지 생각해보기
			if(sb.length()!=str.length)
				res.add(sb.toString());
			return;
		}
		// 원소 선택
		isSelected[index] = true;
		subset(index+1);
		// 원소 미선택
		isSelected[index] = false;
		subset(index+1);
	}

}
