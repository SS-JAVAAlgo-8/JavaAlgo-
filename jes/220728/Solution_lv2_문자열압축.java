package programmers;

public class Solution_lv2_문자열압축 {
	public static void main(String[] args) {
		String s = "x";
		int min = Integer.MAX_VALUE;
		
		
		if(s.length()==1) min=1;
		for(int i = 1; i <= s.length()/2; i++) {
			String prev = s.substring(0, i); // 비교할 값
			String next = "";
			String res = "";
			int cnt = 1;
			
			for(int j = i; j <= s.length(); j += i) {
				if(j+i >= s.length()) {
					next = s.substring(j, s.length());
				}
				else {
					next = s.substring(j, j+i);
				}
				
				if(next.equals(prev)){
					cnt++;
				}
				else if(cnt == 1) {
					res = res + prev;
					prev = next;
				}
				else {
					res = res +String.valueOf(cnt) + prev;
					prev = next;
					cnt = 1;
				}
			}
			if(i != prev.length())
				res = res + prev;

			min = Math.min(res.length(), min);
		}
		System.out.println(min);
	}
}