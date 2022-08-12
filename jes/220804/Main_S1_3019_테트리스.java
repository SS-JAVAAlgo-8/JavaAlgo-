package algostudy.day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_3019_테트리스 {
	static int[] map;
	static int C, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		map = new int[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			map[i] = Integer.parseInt(st.nextToken()); 
		}
		
		// 각 블록에서 나올 수 있는 경우 모두 찾아서 바닥 높이 배열을 인자로 넘겨줌
		switch(P) {
		case 1:
			drop(new int[]{0});
			drop(new int[]{0, 0, 0, 0});
			break;
		case 2:
			drop(new int[]{0, 0});
			break;
		case 3:
			drop(new int[]{0, 0, 1});
			drop(new int[]{1, 0});
			break;
		case 4:
			drop(new int[]{1, 0, 0});
			drop(new int[]{0, 1});
			break;
		case 5:
			drop(new int[]{0, 0, 0});
			drop(new int[]{0, 1});
			drop(new int[]{1, 0, 1});
			drop(new int[]{1, 0});
			break;
		case 6:
			drop(new int[]{0, 0, 0});
			drop(new int[]{0, 0});
			drop(new int[]{0, 1, 1});
			drop(new int[]{2, 0});
			break;
		case 7:
			drop(new int[]{0, 0, 0});
			drop(new int[]{0, 2});
			drop(new int[]{1, 1, 0});
			drop(new int[]{0, 0});
			break;
		}
		
		System.out.println(cnt);
	}

	// (필드의 높이 - 블록의 바닥 높이) 통해 바닥과 틈이 있는지 확인
	// 블록의 바닥은 무조건 0이 있기 때문에 공중에 떠있을 수 없음!
	private static void drop(int[] block) {
		for(int i=0; i<C-block.length+1; i++) { // 반복 횟수
			boolean isSame = true;
			// 블록 바닥이 1면일 때(1번째 블록 세워져 있을 때)
			if(block.length==1) cnt++;
			else {
				for(int j=0, k = i; j<block.length-1; j++, k++) { // 높이 차 비교
					// 만약 k위치의 차이와 k+1위치의 차이가 같으면 isSame이 true로 유지, 다르면 false
					if(map[k]-block[j] == map[k+1]-block[j+1]) continue;
					else {
						isSame = false; 
						break;
					}
				}
				if(isSame) cnt++;
			}
		}
	}
}
