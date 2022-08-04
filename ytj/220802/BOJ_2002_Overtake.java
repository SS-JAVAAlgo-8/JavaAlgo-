package study.day220804.problem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2002_Overtake {
	/* Try 01#
	 * 
	 * 1 2 3 4   ->   4 1 2 3 
	 * ↑________________↑
	 * 
	 * 1 2 3 4 5 ->   2 5 4 1 3
	 * 
	 *  Sol) 원래대로 만드는데 최소 몇개를 옮겨야 하는가? 
	 *  
	 *   📢  Car in, Car out 으로 따로 담아서 순서 비교를 통해 추월한 차를 찾아내자 	   
	 *   1. hashmap에 번호판과 순서 담아서 비교  :  key값 중복 불가	  
	 *   2. 순서비교를 통한 추월 차량수 도출 실패
	 */ 
	
	/* Try 02#
     *  원래 위치에서 1칸 이동
	 *  1 	┐	4 						        1	-	1 : 원래위치 이동 0칸
	 *  2	└	1                               2	┐	3
	 *  3		2                               3	│	4
	 *  4		3                               4	└	2 : 원래위치 이동 2칸
	 *  
	 *  📢 들어온 첫번째 순서부터 나왔을때와 순서가 얼마나 차이가 나는지 비교하여 그 차이만큼 반환
	 *     차이가 0 이라면 들어온 다음 순서를 기준으로 다시 체크  
	 *  
	 *  1. 받은 문자열을 통해 위와 같은 int 배열  도출
	 *  2. 비교 로직을 구현하여 값을 반환
	*/
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("data/over.txt"));
		
		Scanner sc = new Scanner(System.in);
		int carNum = Integer.parseInt(sc.nextLine());
		
		ArrayList<String> car = new ArrayList<>();
		int carin[] = new int[carNum];
		int carout[] = new int[carNum];
		
		for (int i = 0; i < 2*carNum; i++) { // 들어오고 나간 차량번호 저장
			car.add(sc.nextLine());
		}
		
		int ans = overtake(car, carNum);
		System.out.println("추월 차량 수 : " + ans);
		
		
	}

	private static int overtake(ArrayList<String> car, int carNum) {
		for (int i = 0; i < carNum; i++) {
			
			for (int j = 0; j < carNum; j++) {
				
				if( car.get(i).equals(car.get(carNum + j)) ) {
					if(Math.abs(j-i) == 0) break;
					
					return Math.abs(j-i);
				}
					
			}
		}
		return 0;
	}

}
