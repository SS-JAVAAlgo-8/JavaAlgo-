package BOJ_3019;

import java.util.ArrayList;
import java.util.Scanner;

public class Tetris {
    // 단순한 구현문제로 접근
	// 일단 각 블럭마다 블럭을 놓을 수 있는 모든 경우의 수를 구하기
	// 1번 -> 2개의 상태 -> 반드시 열만큼의 횟수는 확보 - > 1씩 증가하며 4만큼 검사해서 갯수 구하기
	// 2번 -> 1개의 상태 -> 양 옆의 높이가 차이날 경우 반드시 불가능
	// 3번 -> 왼쪽높이가 한칸 높거나 왼쪽 길이가 두칸 연속되고 오른쪽 높이가 한칸 높거나
	// 4번 -> 오른쪽 높이가 한칸 높거나 오른쪽 길이가 두칸 연속되고 왼쪽 높이가 한칸 높거나
	// 5번 -> 4개의 상태 -> 같은 높이가 3칸 지속되거나 오른쪽 or 왼쪽 높이가 한칸 높거나  오른쪽+ 왼쪽 높이가 둘다 한칸 높거나
	// 6번 -> 같은 높이가 3칸 or 두칸 지속되거나 왼쪽 높이가 두칸 높거나 오른쪽 높이가 한칸 높고 두칸 이어지거나
	// 7번 -> 같은 높이가 3칸 or 두칸 지속되거나 오른족 높이가 두칸 높거나 왼쪽 높이가 한칸 높고 두칸 이어지거나
	static int C;
	static int cnt;
	static ArrayList<Integer>[] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C=sc.nextInt();
		int P = sc.nextInt();
		sc.nextLine();
		map = new ArrayList[C];
		for (int i =0 ; i < C ; i ++) {
			int high = sc.nextInt();
			map[i]=new ArrayList<>();
			if (high==0) continue;
			else {
				for (int j = 0 ; j<high ; j++) {
					map[i].add(1);
				}
			}
		}
		switch(P) {
		case 1:
			check1();
			break;
		case 2:
			check2();
			break;
		case 3:
			check3();
			break;
		case 4:
			check4();
			break;
		case 5:
			check5();
			break;
		case 6:
			check6();
			break;
		case 7:
			check7();
			break;
		}
		System.out.println(cnt);
	}
	static void check1() {
		cnt+=C;
		for (int i = 0 ; i < C-3 ; i ++) {
			if (map[i].size()==map[i+1].size() && map[i].size()==map[i+2].size() && map[i].size()==map[i+3].size()) {
				cnt++;
			}
		}
	}
	static void check2() {
		for (int i=0 ; i<C-1 ; i ++) {
			if (map[i].size()==map[i+1].size()) {
				cnt++;
			}
		}
	}
	static void check3() {
		for (int i =0 ; i <C-1 ; i ++) {
			if (map[i].size() == (map[i+1].size()+1)) {
				cnt++;
			}
		}
		for (int i = 0; i<C-2 ; i++) {
			if((map[i].size() == map[i+1].size()) && (map[i+1].size()+1) == map[i+2].size()){
				cnt++;
			}
		}
	}
	static void check4() {
		for (int i =0 ; i <C-1 ; i ++) {
			if (map[i].size()+1 == (map[i+1].size())) {
				cnt++;
			}
		}
		for (int i = 0; i<C-2 ; i++) {
			if((map[i].size() == map[i+1].size()+1) && (map[i+1].size()) == map[i+2].size()){
				cnt++;
			}
		}
	}
	static void check5() {
		for (int i  =0 ; i < C-2 ; i++) {
			if(map[i].size() == map[i+1].size() && map[i].size()== map[i+2].size()) {
				cnt++;
			}
		}
		for (int i = 1 ; i <C-1 ; i ++) {
			if (map[i].size()+1 == map[i-1].size()) {
				cnt++;
			}
			if (map[i].size()+1 == map[i+1].size()) {
				cnt++;
			}
			if ((map[i].size()+1 == map[i-1].size()) && (map[i].size()+1 == map[i+1].size())) {
				cnt++;
			}
		}
	}
	static void check6() {
		for (int i  =0 ; i < C-2 ; i++) {
			if(map[i].size() == map[i+1].size() && map[i].size()== map[i+2].size()) {
				cnt++;
			}
		}
		for (int i=0 ; i<C-1 ; i ++) {
			if (map[i].size()==map[i+1].size()) {
				cnt++;
			}
		}
		for (int i = 1 ; i<C ; i ++ ) {
			if (map[i].size()+2 == map[i-1].size()) {
				cnt++;
			}
		}
		for (int i =0; i < C-2; i ++) {
			if (map[i].size()+1 == map[i+1].size() && map[i+1].size() == map[i+2].size()) {
				cnt++;
			}
		}
	}
	static void check7() {
		for (int i  =0 ; i < C-2 ; i++) {
			if(map[i].size() == map[i+1].size() && map[i].size()== map[i+2].size()) {
				cnt++;
			}
		}
		for (int i=0 ; i<C-1 ; i ++) {
			if (map[i].size()==map[i+1].size()) {
				cnt++;
			}
		}
		for (int i = 0 ; i<C-1 ; i ++ ) {
			if (map[i].size()+2 == map[i+1].size()) {
				cnt++;
			}
		}
		for (int i =2; i < C; i ++) {
			if (map[i].size()+1 == map[i-1].size() && map[i-1].size() == map[i-2].size()) {
				cnt++;
			}
		}
	}

}
