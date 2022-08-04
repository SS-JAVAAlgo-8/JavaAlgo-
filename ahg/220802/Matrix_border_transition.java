import java.util.*;
class Solution {
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		// 맵 생성
		int[][] pan = new int[rows][columns];
		//맵에 초기 데이터 입력
		int cnt = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				pan[i][j] = cnt++;
			}
		}
		// queries[0][1] queries[0][3] , queries[2][1] queries[2][3] -> 가로
		// queries[0][1] queries[2][1] , queries[0][3] queries[2][3] -> 세로

		int start_x, start_y, end_x, end_y;
		for (int i = 0; i < queries.length; i++) {
			//queries 배열 내의 x1,x2,y1,y2의 크기를 비교해 시작지점과 끝나는 지점 설정
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
			//각 x,y 좌표들끼리의 시작지점과 끝 지점의 거리값을 구함
			int[] direc = { end_y - start_y, end_x - start_x, end_y - start_y, end_x - start_x };
            int temp2;
			//시작지점의 값을 temp에 저장 후 다음 인덱스로 넘어감
            temp=pan[start_x][start_y++];
			for (int k = 0; k < direc[0]-1; k++) {
                if (answer[i]>temp){ // 최소값을 비교함
                    answer[i]=temp;
                }
                temp2=pan[start_x][start_y]; // 현재 인덱스 값을 temp2에 저장 후
                pan[start_x][start_y++] =temp; //  temp에 저장한 이전값을 현재인덱스에 넣어주고 인덱스 증가
                temp=temp2; // temp에 현재 인덱스의 값을 넣어줌
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
			//시작위치 바로 전까지 반복을 돌기 때문에 마지막 위치에 한번더 temp입력 후 비교연산 실행
            pan[start_x][start_y]=temp;
            if (answer[i]>temp){
                answer[i]=temp;
                }

			// start_x,start_y -> start_x,end_y 첫번째 반복문 -> 윗줄 가로
			// start_x,end_y -> end_x,end_y 두번째 반복문 -> 오른쪽 세로
			// end_x,end_y - > end_x,start_y 세번째 반복문 -> 아랫줄 가로
			// end_x,start_y - > start_x,start_y 네번째 반복문 -> 왼쪽 세로
		}
		return answer;
	}
}