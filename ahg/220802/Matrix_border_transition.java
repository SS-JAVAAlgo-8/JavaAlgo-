import java.util.*;
class Solution {
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] pan = new int[rows][columns];
		int cnt = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				pan[i][j] = cnt++;
			}
		}
		// queries[][1] queries[][3] , queries[2][1] queries[2][3] -> 가로
		// queries[0][1] queries[2][1] , queries[0][3] queries[2][3] -> 세로
		int start_x, start_y, end_x, end_y;
		for (int i = 0; i < queries.length; i++) {
			if (queries[i][0] > queries[i][2]) {
				start_x = queries[i][2] - 1;
				start_y = queries[i][3] - 1;
				end_x = queries[i][0] - 1;
				end_y = queries[i][1] - 1;
			} else {
				start_x = queries[i][0] - 1;
				start_y = queries[i][1] - 1;
				end_x = queries[i][2] - 1;
				end_y = queries[i][3] - 1;
			}
			answer[i] = pan[start_x][start_y];
            int temp;
			int[] direc = { end_y - start_y, end_x - start_x, end_y - start_y, end_x - start_x };
            int temp2;
            temp=pan[start_x][start_y++];
			for (int k = 0; k < direc[0]-1; k++) {
                if (answer[i]>temp){
                    answer[i]=temp;
                }
                temp2=pan[start_x][start_y];
                pan[start_x][start_y++] =temp;
                temp=temp2;
			}
			for (int k = 0; k < direc[1] ; k++) {
                if (answer[i]>temp){
                    answer[i]=temp;
                }
                temp2=pan[start_x][start_y];
                pan[start_x++][start_y] =temp;
                temp=temp2;
			}
			for (int k = 0; k < direc[2] ; k++) {
                if (answer[i]>temp){
                    answer[i]=temp;
                }
                temp2=pan[start_x][start_y];
                pan[start_x][start_y--] =temp;
                temp=temp2;
			}
			for (int k = 0; k < direc[3] ; k++) {
                if (answer[i]>temp){
                    answer[i]=temp;
                }
                temp2=pan[start_x][start_y];
                pan[start_x--][start_y] =temp;
                temp=temp2;
			}
            pan[start_x][start_y]=temp;
            if (answer[i]>temp){
                answer[i]=temp;
                }

			// start_x,start_y -> start_x,end_y
			// start_x,end_y -> end_x,end_y
			// end_x,end_y - > end_x,start_y
			// end_x,start_y - > start_x,start_y
		}
		return answer;
	}
}