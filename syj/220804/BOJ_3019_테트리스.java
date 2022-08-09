package study.day0811;

import java.util.Scanner;

public class BOJ_3019_테트리스 {
	static int cnt = 0;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();	// 테트리스 판 크기
		int P = sc.nextInt();	// 테트리스 타입
		arr = new int[C];	// 테트리스 높이 받는 배열
		for (int i = 0; i < C; i++) {
			arr[i] = sc.nextInt();
		}
		switch (P) {	// 도형 타입에 따른 함수 호출
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
	private static void check1() {
		cnt += arr.length;	// 세로로 모든 열에 놓을 수 있음
		outer : for (int i = 0; i <= arr.length - 4; i++) {	// 가로로 같은 길이가 4칸 연속이면 놓을 수 있음
			int len = arr[i];
			for (int j = i; j < i + 4; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
	}
	private static void check2() {	// 가로 세로 둘 다 2칸 연속 같은 높이일 때 놓을 수 있음
		outer : for (int i = 0; i <= arr.length - 2; i++) {
			int len = arr[i];
			for (int j = i; j < i + 2; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
	}
	private static void check3() {	// 1. 한칸 뒤가 1 낮을 때, 2. 2칸 연속 길이가 같고 그 뒤에 칸이 1 높을 때
		for (int i = 0; i < arr.length - 1; i++) {	// 1
			if(arr[i] - arr[i + 1] == 1) cnt++;
		}
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 2
			int len = arr[i];
			for (int j = i; j < i + 2; j++) {
				if(arr[j] != len) continue outer;
			}
			if(arr[i + 2] == len + 1)cnt++;
		}
	}
	private static void check4() {	// 1. 뒤로 두칸 연속 1 낮을 때, 2. 한칸 뒤가 1 높을 때
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 1
			int len = arr[i];
			for (int j = i + 1; j <= i + 2; j++) {
				if(arr[i] - arr[j] != 1) continue outer;
			}
			cnt++;
		}
		for (int i = 0; i < arr.length - 1; i++) {	// 2
			if(arr[i + 1] - arr[i] == 1) cnt++;
		}
	}
	private static void check5() {	// 1. 3칸 연속 높이 같을 때, 2. 양쪽이 1칸 높을 때, 3. 왼쪽이 1칸 높을 때, 4. 오른쪽이 1칸 높을 때
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 1
			int len = arr[i];
			for (int j = i; j < i + 3; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
		for (int i = 1; i < arr.length - 1; i++) {	// 2
			if(arr[i + 1] - arr[i] == 1 && arr[i - 1] - arr[i] == 1) cnt++;
		}
		for (int i = 0; i < arr.length - 1; i++) {	// 3
			if(arr[i] - arr[i + 1] == 1) cnt++;
		}
		for (int i = 0; i < arr.length - 1; i++) {	// 4
			if(arr[i + 1] - arr[i] == 1) cnt++;
		}
		
	}
	private static void check6() {	// 1. 3칸 연속 높이 같을 때, 2. 2칸 연속 같은 높이, 3. 옆으로 두 칸이 1 높을 때, 4. 왼쪽이 2 높을 때
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 1
			int len = arr[i];
			for (int j = i; j < i + 3; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
		outer : for (int i = 0; i <= arr.length - 2; i++) {	// 2
			int len = arr[i];
			for (int j = i; j < i + 2; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 3
			int len = arr[i];
			for (int j = i + 1; j <= i + 2; j++) {
				if(arr[j] - arr[i] != 1) continue outer;
			}
			cnt++;
		}
		for (int i = 0; i < arr.length - 1; i++) {	// 4
			if(arr[i] - arr[i + 1] == 2) cnt++;
		}
	
	}
	private static void check7() {	// 1. 3칸 연속 같은 높이, 2. 2칸 연속 같은 높이, 3. 2칸 연속 같고 그 옆이 1 낮을 때, 4. 오른쪽이 2 높을 때
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 1
			int len = arr[i];
			for (int j = i; j < i + 3; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
		outer : for (int i = 0; i <= arr.length - 2; i++) {	// 2
			int len = arr[i];
			for (int j = i; j < i + 2; j++) {
				if(arr[j] != len) continue outer;
			}
			cnt++;
		}
		outer : for (int i = 0; i <= arr.length - 3; i++) {	// 3
			int len = arr[i];
			for (int j = i; j < i + 2; j++) {
				if(arr[j] != len) continue outer;
			}
			if(arr[i + 2] == len - 1)cnt++;
		}
		for (int i = 0; i < arr.length - 1; i++) {	// 4
			if(arr[i + 1] - arr[i] == 2) cnt++;
		}
	}
}