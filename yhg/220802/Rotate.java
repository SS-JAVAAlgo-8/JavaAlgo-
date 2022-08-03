import java.util.*;
public class Rotate {
	static int[][] board; // 숫자 배열
    static int[] result; // 결과를 담을 배열
    static int resultindex = 0; // 결과값 인덱스
    public static void rotate(int[] queries){
        List<Integer> temp = new ArrayList<Integer>();
        int x1 = queries[0];// 시작점의 x좌표
        int y1 = queries[1];// 시작점의 y좌표
        int x2 = queries[2];// 끝지점의 x좌표
        int y2 = queries[3];// 끝지점의 y좌표
        
        // 사각형 한바퀴를 돌면서 List에 값을 하나씩 담는다.
        for(int i = y1-1;  i < y2; i++) { 
        	temp.add(board[x1-1][i]);
        }
        for(int i = x1;  i < x2; i++) { 
        	temp.add(board[i][y2-1]);
        }
        for(int i = y2-2;  i > y1-1; i--) { 
        	temp.add(board[x2-1][i]);
        }
        for(int i = x2-1;  i > x1-1; i--) { 
        	temp.add(board[i][y1-1]);
        }
        
        // 끝값을 list에 제일 앞으로 가져오고 방금 돌았던 인덱스를 돌면서 값을 바꿔준다 --> rotate
        temp.add(0, temp.get(temp.size()-1));
        temp.remove(temp.size()-1);
        
        // list의 값은 모두 회전될 값이므로 그중 제일 작은 값을 저장하고 index옮기기
        result[resultindex] = Collections.min(temp);
        resultindex+=1;
 
        // 방금 돌았던 index를 하나씩 돌면서 list의 값으로 바꿔준다.
        int index = 0;
        for(int i = y1-1;  i < y2; i++) { 
        	board[x1-1][i] = temp.get(index);
        	index +=1;
        }
        for(int i = x1;  i < x2; i++) { 
        	board[i][y2-1] = temp.get(index);
        	index +=1;
        }
        for(int i = y2-2;  i > y1-1; i--) { 
        	board[x2-1][i] = temp.get(index);
        	index +=1;
        }
        for(int i = x2-1;  i > x1-1; i--) { 
        	board[i][y1-1] = temp.get(index);
        	index +=1;
        }
        
    }
    
    public static int[] solution(int rows, int columns, int[][] queries) {
        
        result = new int[queries.length];
        board = new int[rows][columns];
        int n = 1;
        for(int i = 0; i<rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = n;
                n+=1;
            }
        }
        
        for(int[] q : queries){
            rotate(q);
            
        }
        return result;
    }
    
    public static void main(String[] args) {
    	int[][] que =  {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
    	solution(6, 6, que);
    	System.out.println(result);
	}
}
