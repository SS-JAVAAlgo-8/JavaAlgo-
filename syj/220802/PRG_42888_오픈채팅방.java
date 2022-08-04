/*
 해시맵을 이용해 입력받을 때 해당 아이디에 닉네임만 계속해서 변경
 
 풀이 방법 :

- 해시맵 사용
- 해시맵은 key가 존재하면 value 값만 변경된다는 점 활용
- 먼저 닉네임 변경 후 출력값 저장 (for문 두번 활용)
 */
package study;

import java.util.Arrays;
import java.util.HashMap;

public class PRG_42888_오픈채팅방 {
	static public String[] solution(String[] record) {
		HashMap<String, String> map = new HashMap<>();
		int cnt = 0;
		for (int i = 0; i < record.length; i++) {	// ㄴ닉네임 저장
			String[] temp = record[i].split(" ");
			if(temp[0].equals("Enter")) {
				map.put(temp[1], temp[2]);
				cnt ++;
			}else if(temp[0].equals("Change")) {
				map.put(temp[1], temp[2]);
			}else if(temp[0].equals("Leave"))cnt++;
		}
		String[] answer = new String[cnt];
		for (int j = 0, index = 0; j < record.length; j++) {	// 출력값 저장
			String[] temp = record[j].split(" ");
			if(temp[0].equals("Enter")) {
				answer[index++] = map.get(temp[1]) + "님이 들어왔습니다.";
			}else if(temp[0].equals("Leave")) {
				answer[index++] = map.get(temp[1]) + "님이 나갔습니다.";
			}
		}
		return answer;
	}
	public static void main(String[] args) {	// 테스트용
		String[] record = {"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"
				};
		String[] result = solution(record);
		System.out.println(Arrays.toString(result));
	}
}
