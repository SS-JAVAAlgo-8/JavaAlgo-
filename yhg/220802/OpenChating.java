import java.util.*;
public class OpenChating {
	public static String[] solution(String[] record){
		int count = 0;
		Map<String , String> idNick = new HashMap<>();
		List<String[]> command = new ArrayList<>();
		
		for (String re : record) {
			String[] temp = re.split(" "); // 명령어 구분짓기
			if(temp[0].equals("Enter")) { // 입장이면 아이디 : 닉네임 으로 map 구성함 , 
				idNick.put(temp[1], temp[2]);      
				String[] c = {temp[0] , temp[1]}; // 명령어 , 아이디
				command.add(c);                   // [명령어 , 아이디]의 형태로  결과 배열에 추가 , 나중에 출력할 문자열의 길이 + 1 
				count += 1;                       // 나중에 출력할 문자열의 길이 + 1 
			}
			else if(temp[0].equals("Leave")) { // 퇴장이면 [명령어 , 나중에 출력할 문자열의 길이 + 1 
				String[] c = {temp[0] , temp[1]};       // [명령어 , 아이디]의 형태로  결과 배열에 추가
				command.add(c);
				count += 1;                             // 나중에 출력할 문자열의 길이 + 1 
			}else if(temp[0].equals("Change")) { //아이디 변경은 닉네임만 바꿈
				idNick.put(temp[1], temp[2]);
			}
		}
		String[] result = new String[count];  // 출력 카운드 만큼만 돌린다. 
		 
		int index = 0; 
        // 결과 출력
		for (String[] com : command) {
			if(com[0].equals("Enter")) {
				result[index] = idNick.get(com[1]) + "님이 들어왔습니다.";
			}else if(com[0].equals("Leave")) {
				result[index] = idNick.get(com[1]) + "님이 나갔습니다.";
			}
			index += 1;
		}
		
		
		
		return result;
	}
	
	
	
	
	
	public static void main(String[] args) {
		String[] re = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		solution(re);
	}
}
