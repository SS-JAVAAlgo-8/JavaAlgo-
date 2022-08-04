// 풀이 방법 : 회전방향을 역방향으로 해서 뒤에서 한 칸씩 값을 댕겨온다.
package study;

import java.util.Arrays;

// rows, columns, queries는 매개변수로 전달받음 (여기서는 편의를 위해 직접 입력함)
public class PRG_77485_행렬테두리회전하기 {
	static int[] dx = {1, 0, -1, 0};	// 방향 탐색 (하 -> 우 -> 상 -> 좌)
	static int[] dy = {0, 1, 0, -1};
	static int[][] arr;
	static int r;
	static int c;
	static int[] answer;
	static int minNum;
	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int[][] queries = {
				{2, 2, 5, 4},
				{3, 3, 6, 6},
				{5, 1, 6, 3}
		};
		answer = new int[queries.length];
		arr = new int[rows][columns];	// 전체 배열 생성
        int num = 1;
		
        for(int i = 0; i < rows; i++) {	// 배열 값 넣기
            for(int j = 0; j < columns; j++) {
                arr[i][j] = num;
                num++;
            }
        }
        for (int i = 0; i < queries.length; i++) {	// 위치 값 전달 (인덱스에 맞춰 -1 한 뒤 전달)
			rotation(--queries[i][0], --queries[i][1], --queries[i][2], --queries[i][3]);
			answer[i] = minNum;
		}
        System.out.println(Arrays.toString(answer));
	}
	
	static void rotation(int x1, int y1, int x2, int y2) {	// x1, y1 - 영역 처음, x2, y2 - 영역 마지막
		r = x1;
		c = y1;
		int temp = arr[r][c];	// 처음 위치 값 저장 (뒤에꺼 댕겨서 받을 거기 때문에 처음 값은 미리 저장해두기)
		minNum = arr[r][c];	// 최소값 저장 변수
		for (int i = 0; i < 4; i++) {
			r += dx[i];
			c += dy[i];
			while(true) {	// 인덱스가 정해진 범위 내에 있을 때만
				if(!(isValue(x1, y1, x2, y2, i))) break;
				arr[r-dx[i]][c-dy[i]] = arr[r][c];	// 한 칸씩 회전
				minNum = Math.min(minNum, arr[r-dx[i]][c-dy[i]]);
				r += dx[i];
				c += dy[i];
			}
			if(i != 3) {	// 마지막 칸에는 temp 를 넣어야 함 !!
				r -= dx[i];	// 인덱스가 유효하지 않으니 원래대로 돌려놓기
				c -= dy[i];
				arr[r][c] = arr[r + dx[i+1]][c + dy[i+1]];	// 방향 바꾼 다음 칸꺼 가져오기
				minNum = Math.min(minNum, arr[r][c]);
			}else break;
		}
		arr[r - dx[3]][y1 - dy[3]] = temp;	//마지막 칸에는 미리 저장해 둔 temp값 할당
	}
	
	static boolean isValue(int x1, int y1, int x2, int y2, int i) {	// 이동 할 인덱스가 유효한지 확인
		switch (i) {
		case 0:
			if(r >= x1 && r <= x2 && c == y1) return true;
			break;
		case 1:
			if(r == x2 && c >= y1 && c <= y2) return true;
			break;
		case 2:
			if(r >= x1 && r <= x2 && c == y2) return true;
			break;
		case 3:
			if(r == x1 && c >= y1 && c <= y2) return true;
			break;
		}
		return false;
	}
}

