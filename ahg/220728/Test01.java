import java.util.HashMap;
import java.util.Map;

class Solution {
	public int solution(String s) {
		int answer = 0;
        int min_length=s.length();
        for ( int i =1 ; i <s.length()/2+1;i++){
            String result="";
            int cnt=1;
            int a=0;
            String str2="";
            String str1= s.substring(0,i);
            for (int j =i ; j+i<=s.length(); j +=i){
                if (str1.equals(s.substring(j,j+i))){
                    cnt++;
                }else {
                    result= cnt<2 ? result+str1 : result+cnt+str1;
                	str1=s.substring(j,j+i);
                    cnt=1;
                }
                a=j+i;
            }
            result = cnt<2 ? result+str1+s.substring(a,s.length()) : result+cnt+str1+s.substring(a,s.length());
            //System.out.println(result);
            if (min_length>result.length()){
                min_length=result.length();
            }
        }
        
        return min_length;
	}
}