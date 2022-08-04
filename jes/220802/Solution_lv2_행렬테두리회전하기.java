package programmers;

import java.util.Arrays;

public class Solution_lv2_행렬테두리회전하기 {

	public static void main(String[] args) {
		int[][] queries = {{1, 1, 2, 2},{1, 2, 2, 3},{2, 1, 3, 2}, {2, 2, 3, 3}};
		int[] result = solution(3, 3, queries);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        Arrays.fill(answer, 0);
        int[][] map = new int[rows][columns];
        
        int num = 1;
        for(int i=0; i<rows; i++) 
        	for(int j=0; j<columns; j++) 
        		map[i][j] = num++;
        
        // 행렬 테두리 회전하면서 min 구하기
        for(int i=0; i<queries.length; i++) {
        	int x1 = queries[i][0]-1;
        	int y1 = queries[i][1]-1;
        	int x2 = queries[i][2]-1;
        	int y2 = queries[i][3]-1;
        	
        	// 이동하기 전 처음 값 빼내기
        	int temp = map[x1][y1];
        	int min = temp;
        	// 상
        	for(int j=x1; j<x2; j++) {
        		map[j][y1] = map[j+1][y1];
        		if(map[j][y1]<min) min = map[j][y1];
        	}
        
        	// 좌
        	for(int j=y1; j<y2; j++) {
        		map[x2][j] = map[x2][j+1];
        		if(map[x2][j]<min) min = map[x2][j];
        	}
        	
        	// 하
        	for(int j=x2; j>x1; j--) {
        		map[j][y2] = map[j-1][y2];
        		if(map[j][y2]<min) min = map[j][y2];
        	}
        	
        	// 우
        	for(int j=y2; j>y1; j--) {
        		map[x1][j] = map[x1][j-1];
        		if(map[x1][j]<min) min = map[x1][j];
        	}
        	
        	// 처음에 빼두었던 값 빈자리에 집어 넣기
        	map[x1][y1+1] = temp;
        	answer[i] = min;
        }
        return answer;
    }
}
