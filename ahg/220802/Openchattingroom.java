import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer;
        //uid를 key값으로 두고 닉네임을 value값으로 가질 map 선언
        Map<String,String> map = new HashMap<>();
        //출력할 들어온공지와 나간공지의 갯수를 세는 변수
        int index=0;
        //제일 마지막에 바꾼 닉네임으로 모든 채팅이 바뀌니 뒤에서부터 검색
        for ( int i = record.length-1 ; i >= 0;i--){
            //띄어쓰기를 기준으로 행동,uid,닉네임으로 나눠서 배열에 저장
            String[] uid = record[i].split(" ");
            //출력할 배열의 길이를 카운팅
            if (uid[0].equals("Enter") || uid[0].equals("Leave")){
                index++;
            }
            // 바꾸거나, 들어왔을때의 uid를 기준으로 닉네임 저장
            if ((uid[0].equals("Enter") || uid[0].equals("Change")) && map.containsKey(uid[1])==false){
                map.put(uid[1],uid[2]);
            }
        }
        answer =  new String[index];
        index=0;
        for ( int i = 0 ; i<record.length ; i++){
            //또다시 문자열을 나누는데 uid를 다시 갖다가 쓰는게 더 나앗을듯
            String[] temp = record[i].split(" ");
            // Enter나 Leave일 경우 해당 Uid값에 맞는 닉네임 +행동을 정답 배열에 추가
            if (temp[0].equals("Enter")){
                answer[index++]=map.get(temp[1]) + "님이 들어왔습니다.";   
            }
            if (temp[0].equals("Leave")){
                answer[index++]=map.get(temp[1]) + "님이 나갔습니다.";
            }
        }
        return answer;

    }
}