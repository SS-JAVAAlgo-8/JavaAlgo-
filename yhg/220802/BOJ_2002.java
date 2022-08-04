import java.util.*;
import java.io.*;


public class BOJ_2002 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		// 들어오는 차들에게 번호를 부여
		Map<String , Integer> map = new HashMap<>();
		int index = 1;
		int result = 0;
		for (int i = 0; i < n; i++) {
			String car = br.readLine();
			map.put(car, index);
			index +=1;
		}
		
		//터널 이후에 들어오는 차량 확인
		String[] map2 = new String[n];
		
		for (int i = 0; i < n; i++) {
			map2[i] = br.readLine();
		}
		
		//해당 차량을 기준으로 뒤에있는 차를 확인했을 때 , 번호가 작은 차량이 뒤에있으면 추월했다고 판단. 
		for (int i = 0; i < n-1; i++) {
			int start = map.get(map2[i]);
			for (int j = i+1; j < map2.length; j++) {
				int end = map.get(map2[j]);
				if (start > end) {
					result +=1;
					break;
				}
			}
		}
		
		System.out.println(result);
		
	}
}
