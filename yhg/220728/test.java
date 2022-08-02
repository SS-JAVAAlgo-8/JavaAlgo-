class Solution {
    public int solution(String s) {
        int answer = 0;
        int min = Integer.MAX_VALUE;
        String res = "";
	      
	      if(s.length()==1) {
	         res = s;
	         min = 1;
	      }
	      
	      for(int i = 1; i <= s.length()/2; i++) {
	         String tmp = s.substring(0, i); // 비교할 값
	         res = ""; // 길이 잴 값
	         int cnt = 1;
	         
	         for(int j = i; j < s.length(); j += i) {
	            if(j+i < s.length()) {
	               if(tmp.equals(s.substring(j, j+i))) cnt++;
	               else {
	                  if(cnt == 1) res = res + tmp;
	                  else res = res + Integer.toString(cnt) + tmp;
	                  cnt = 1;
	               }
	               tmp = s.substring(j, j+i);
	            }
	            else {
	               if(j+i== s.length()) {
	                  if(tmp.equals(s.substring(j, s.length()))) cnt++;
	                  if(cnt == 1) res = res + tmp + s.substring(j, s.length());
	                  else res = res + Integer.toString(cnt) + tmp;
	               }
	               else {
	                  if(tmp.equals(s.substring(j, s.length()))) cnt++;
	                  if(cnt == 1) res = res + tmp + s.substring(j, s.length());
	                  else res = res + Integer.toString(cnt) + tmp + s.substring(j, s.length()-1);
	               }
	            }
	         }
	         if(res.length() < min) {
	            min = res.length();
	         }
	      }
	      return min;
    }
}