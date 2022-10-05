package swtest;

/*
 * 메모리 : 26,672 kb , 시간 : 125 ms
 * 
 * 1. 각각의 숫자를 순서대로 진행하면서 연산을 하나씩 진행한다. 
 * 2. 연산을 진행하는 조건은 'visited에 있는 숫자가 연산자보다 작다 == 연산자를 사용할 수 있다' 이다.
 * 3. 각각의 연산에 대하여 visited를 다시 되돌려놓고 다음 연산자를 진행한다. 
 * 4. 만약 index가 끝까지 진행되었다면 max과 min을 초기화 한다. 
 * 
 * */
import java.io.*;
import java.util.*;

public class SWEA_4008_숫자만들기 {
	static int N, T;
	static int[] visited;
	static int[] op;
	static int[] nums;
	static int answer;
	static int max, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			visited = new int[4];
			op = new int[4];
			nums = new int[N];
			
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < input.length; i++) {
				op[i] = Integer.parseInt(input[i]);
			}
			input = br.readLine().split(" ");
			for (int i = 0; i < input.length; i++) {
				nums[i] = Integer.parseInt(input[i]);
			}

			cal(1, nums[0], visited);
			
			System.out.println("#" + testcase + " " + (max - min));
		}

	}

	private static void cal(int index, int result, int[] visited) {
		if (index == nums.length) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (visited[i] < op[i]) {
				visited[i] += 1;
				if (i == 0)cal(index + 1, result + nums[index], visited);
				if (i == 1)cal(index + 1, result - nums[index], visited);
				if (i == 2)cal(index + 1, result * nums[index], visited);
				if (i == 3)cal(index + 1, result / nums[index], visited);
				visited[i] -= 1;
			}
		}
	}
}
