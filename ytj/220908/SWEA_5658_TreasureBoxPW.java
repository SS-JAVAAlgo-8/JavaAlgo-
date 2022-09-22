package study.day220908.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.NavigableSet;

/* 걸린 시간 : 1h 10m
 * 
 * input 값을 4 등분으로 나누고 각각 순서대로 배열에 담는다.
 * N/4 만큼의 회전 동안 가능한 모든 생성 가능한 경우의 수를 구한다.
 * 
 * Integer.parseInt(hexInput = 문자열, 16);
 * 
 * 각 경우에 해당하는 16진수를 10진수로 바꾸어 TreeSet에 담아 중복을 없애고, 내림차순으로 나열한다.
 */
public class SWEA_5658_TreasureBoxPW {
	static int N, K, cnt, ans;
	static TreeSet<Integer> set;
	static Deque<Character> qu;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cnt = N / 4;
			ans = 0;
			
			qu = new ArrayDeque<Character>();
			set = new TreeSet<Integer>();
			
			char [] arr = br.readLine().toCharArray();
			
			for (int i = 0; i < N; i++) {
				qu.add(arr[i]);
			}
			
			for (int i = 0; i < cnt; i++) { // 총 회전 횟수는 N/4 와 같다.
				
				qu.offerFirst(qu.pollLast()); // 회전
				
				int n = 0;
				for(Character c : qu) { // 회전 한 후 상태 배열에 담기
					arr[n] = c;
					n++;
				}
				
				String num = "";
				for (int j = 1; j <= N; j++) { //  N/4 씩 나누어 문자열로 담기
					num += arr[j-1];
					
					if(j % cnt == 0) {
						set.add(Integer.parseInt(num, 16)); // 16진수 문자열을 10진수 숫자로 변환
						num = "";
					}
				}
				
			}
			
			int count = 0;
			for(int i : set) {
				if(set.size() - K == count) ans = i;
				count++;
			}
			
			System.out.printf("#%d %d\n", tc, ans);
			/*
			 * NavigableSet<Integer> descSet = set.descendingSet();
			 * Set을 내림차순으로 정렬한 새로운 Set 을 얻을 수 있다.
			*/
		}
	}

}

/*
1
12 10
1B3B3B81F75E
*/