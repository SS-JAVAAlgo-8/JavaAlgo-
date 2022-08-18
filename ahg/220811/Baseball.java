import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Player {
	int number;
	int value;

	public Player(int number, int value) {
		this.number = number;
		this.value = value;
	}
}

public class Main {
	static int max_value = Integer.MIN_VALUE;
	static int[][] stage;
	static int inning;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());
		stage = new int[inning][9];
		for (int i = 0; i < inning; i++) {
			stage[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		int[] order = new int[9];
		boolean[] check = new boolean[10];
		check[3] = true; // 야구 선수를 배치했는지 확인하기 위한 배열
		order[3] = 1; // 1번 선수는 반드시 4번타자여야 하기때문에 미리 설정
		check(order, check, 2);
		System.out.println(max_value);
	}

	static void check(int[] order, boolean[] check, int idx) { // 타자 경우의 수를 도출하는 순열 메서드
		if (idx == 10) {
			baseball(order, 0, 0, 0); // 조합이 짜졌다면 경기 시작
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (!check[i]) {
				check[i] = true; 
				order[i] = idx;
				check(order, check, idx + 1);
				check[i] = false;
			}
		}
	}

	static void baseball(int[] order, int inning_2, int i, int score) {
		int cnt = 0;
		int[] map = new int[3];
		while (true) {
			if (i > 8) // 타자가 한바퀴 돌면 다시 처음부터
				i = 0;
			if (stage[inning_2][order[i] - 1] == 0) { // 해당 타자의 점수를 확인하고 아웃을 증가 시켜줌 , 아웃이 세번일 경우 탈출
				cnt++;
				if (cnt == 3) {
					break;
				}
			}
			if (stage[inning_2][order[i] - 1] > 0) { // 현재 타자가 1점이상을 쳤을 때
				for (int j = 0; j < stage[inning_2][order[i] - 1]; j++) { //각자 타석에 있던 주자들을 점수만큼 반복시켜주면서 1씩 올려줌
					score += map[2];
					map[2] = map[1];
					map[1] = map[0];
					map[0] = 0;
				}
				if (stage[inning_2][order[i]-1] ==4) { // 현재 타자는 홈런을 쳤을 경우 바로 점수 +1
					score++;
				}else {
					map[stage[inning_2][order[i] - 1]-1]++; // 아닐 경우 점수 -1 만큼의 위치에 이동시켜줌
				}
			}
			i++;
		}

		if (inning_2 + 1 == inning) { // 이닝 수가 종료되면 최대값 비교
			if (max_value < score) {
				max_value = score;
			}
			return;
		}
		baseball(order, inning_2 + 1, i + 1, score); // 다음 이닝떄는 다음 타자부터 해야하니까 i+1로 재귀 돌림
	}
}