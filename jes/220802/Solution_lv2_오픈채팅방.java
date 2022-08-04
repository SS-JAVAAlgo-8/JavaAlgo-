package programmers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 220801
public class Solution_lv2_오픈채팅방 {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		System.out.println(Arrays.toString(solution(record)));
	}
	
	public static String[] solution(String[] record) {
	    StringTokenizer st;    	
	    int N = record.length;
	    Map<String, String> uid_nick = new LinkedHashMap<>(); // uid와 닉네임 묶어서 순서대로 관리
	    String[][] key_uid = new String[N][2]; // 키워드(enter, change, leave)와 uid 관리
	    int cnt = 0;
	    	
	    for(int i =0; i<N; i++) {
	    	st = new StringTokenizer(record[i]);
	    	String keyword = st.nextToken();
	    	if(keyword.equals("Enter")) { // Enter일 때 
	    		key_uid[i][0] = "님이 들어왔습니다.";
	    		key_uid[i][1] = st.nextToken();
	    		uid_nick.put(key_uid[i][1], st.nextToken());
	    		cnt++;
	    	}
	    	else if(keyword.equals("Change")) { // Change일때
	    		key_uid[i][0] = "change";
	    		key_uid[i][1] = st.nextToken();
	    		uid_nick.put(key_uid[i][1], st.nextToken());
	    	}
	    	else { // Leave일 때
	    		key_uid[i][0] = "님이 나갔습니다.";
	    		key_uid[i][1] = st.nextToken();
	    		// 닉네임이 입력으로 주어지지 않기 때문에 uid통해 닉네임 get한 뒤 put
	    		uid_nick.put(key_uid[i][1], uid_nick.get(key_uid[i][1]));
	    		cnt++;
	    	}	
	    }
	    
	    String[] result = new String[cnt];
	    // 결과 배열 생성
	    for(int j=0, i=0; j<N; j++) {
	    	if(key_uid[j][0].equals("change")) 
	    		continue;
	    	result[i] = uid_nick.get(key_uid[j][1]) + key_uid[j][0];
	    	i++;
	    }
	    return result;
	}
}

