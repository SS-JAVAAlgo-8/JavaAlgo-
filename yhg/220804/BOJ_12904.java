package BOJ;

import java.io.*;
import java.util.*;



public class BOJ_12904 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuffer S = new StringBuffer(br.readLine());
		StringBuffer target = new StringBuffer(br.readLine());
		int sLen = S.length();
		int tLen = target.length();
		while(true) {
			//처음부터 다 돌리면 길이에 대한 모든 조합을 다 체크해야된다. 
			//그럴바에는 뒤에서 부터 어떤 동작을 했는지만 체크해서 처음으로 되돌아 갈 수 있는지만 체크하면된다. 
			if(sLen == tLen)break;
			// 맨뒤가 A다? -> A를 추가했다 -> 지운다.
			if(target.charAt(tLen-1) == 'A') {
				target.deleteCharAt(tLen-1);
				
			}
			// 맨뒤가 B다? 뒤집고 난 후 B가 추가됐다. -> B 지우고 뒤집는다.
			else if(target.charAt(tLen-1) == 'B') {
				target.deleteCharAt(tLen-1);
				target.reverse();
			}
			tLen-=1;
		}
		//처리한 결과가 처음과 같은지 확인한다. 
		if(target.toString().equals(S.toString())) System.out.println(1);
		else System.out.println(0);
	}
}
