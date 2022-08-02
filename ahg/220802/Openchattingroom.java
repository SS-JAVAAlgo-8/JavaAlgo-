import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer;
        Map<String,String> map = new HashMap<>();
        int index=0;
        for ( int i = record.length-1 ; i >= 0;i--){
            String[] uid = record[i].split(" ");
            if (uid[0].equals("Enter") || uid[0].equals("Leave")){
                index++;
            }
            if ((uid[0].equals("Enter") || uid[0].equals("Change")) && map.containsKey(uid[1])==false){
                map.put(uid[1],uid[2]);
            }
        }
        answer =  new String[index];
        index=0;
        for ( int i = 0 ; i<record.length ; i++){
            String[] temp = record[i].split(" ");
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