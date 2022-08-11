import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

// 괄호 쌍의 인덱스번호를 저장할 클래스 
class Bracket {
	int start;
	int end;

	public Bracket(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class RemoveBracket {
	// 스택이랑 완전탐색이랑 합쳐놓은 문제
	// 괄호의 쌍의 갯수를 구하기
	static ArrayList<Bracket> br_num; // 괄호 쌍의 인덱스번호를 저장할 변수
	static char[] ch; // 문자열을 저장할 변수
	static Set<String> answer; // 최종 값들을 저장할 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ch = br.readLine().toCharArray();
		br_num = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '(') {
				stack.push(i);
			}
			if (ch[i] == ')') {
				br_num.add(new Bracket(stack.pop(), i)); // 스택을 통해 각 괄호쌍의 번호를 br_num 리스트에 저장
			}
		}
		boolean[] brackets = new boolean[ch.length]; //괄호쌍의 체크 유무를 판단하기 위한 불리언배열
		answer = new TreeSet<String>(); //결과값의 중복을 제거하고 사전 순서대로 정렬하기 위해 TreeSet 형태로 객체 생성
		result(0, brackets);
		for (String s : answer)
			System.out.println(s);
	}

	// 괄호 목록의 체크한것들을 true로 설정해주고 true로 설정된 괄호들 외의 것을 문자열로 만들어 저장하는 재귀 (완전탐색)
	static void result(int idx, boolean[] brackets) {
		if (idx == br_num.size())
			return;
		// 리스트의 괄호쌍의 인덱스를 체크
		brackets[br_num.get(idx).start] = true;
		brackets[br_num.get(idx).end] = true;
		// 체크한 괄호를 제외한 문자열의 합을 트리에 추가
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < ch.length; j++) { 
			if (brackets[j])
				continue;
			sb.append(ch[j]);
		}
		answer.add(sb.toString());
		// 모든 경우의 수를 고려해야하기 때문에 출발지점을 전부 다르게 설정해서 두번 호출
		// 현재 괄호를 체크한 상태에서 메서드를 다시 호출 ex) 1 0 0 0 0 0 0
		result(idx + 1, brackets);
		// 현재 괄호의 체크를 해제한 상태에서 메서드를 다시 호출 ex) 0 1 0 0 0 0 0
		brackets[br_num.get(idx).start] = false;
		brackets[br_num.get(idx).end] = false;
		result(idx + 1, brackets);
		return;
	}
}
