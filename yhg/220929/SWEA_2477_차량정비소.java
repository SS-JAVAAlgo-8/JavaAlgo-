<<<<<<< HEAD
package swtest;

import java.io.*;
import java.util.*;

public class SWEA_2477_차량정비소 {
	static class Customer implements Comparable<Customer> {

		int num;
		int recepTime;
		int recep;

		Customer(int n, int recepTime, int recep) {
			this.num = n;
			this.recepTime = recepTime;
			this.recep = recep;
		}

		@Override
		public int compareTo(Customer o) {
			if (this.recepTime == o.recepTime) {
				return this.recep - o.recep;
			}

			return this.recepTime - o.recepTime;
		}

	}

	static int N, M, K, rctcheck, rptcheck;
	static int[] receptions;
	static int[] repairs;
	static int[] time;
	static List<Customer> CusList;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			K = Integer.parseInt(input[2]);
			rctcheck = Integer.parseInt(input[3]);
			rptcheck = Integer.parseInt(input[4]);
			int sum = 0;
			CusList = new ArrayList<>();

			receptions = new int[N + 1];
			repairs = new int[M + 1];
			time = new int[K];
			int[] recep = new int[N + 1];
			int[] repair = new int[M + 1];

			input = br.readLine().split(" ");
			for (int i = 1; i < N+1; i++) {
				receptions[i] = Integer.parseInt(input[i-1]);
			}
			input = br.readLine().split(" ");
			for (int i = 1; i < M+1; i++) {
				repairs[i] = Integer.parseInt(input[i-1]);
			}

			input = br.readLine().split(" ");
			for (int i = 0; i < K; i++) {
				time[i] = Integer.parseInt(input[i]);
			}

			for (int i = 0; i < K; i++) {
				int start = time[i];
				int min = Integer.MAX_VALUE;
				int wait = 1;

				for (int j = 1; j <= N; j++) {

					if (recep[j] <= start) {
						recep[j] = start + receptions[j];
						CusList.add(new Customer(i + 1, recep[j], j));
						break;
					}

					if (recep[j] < min) {
						wait = j;
						min = recep[j];
					}

					if (j == N) {
						recep[wait] = min + receptions[wait];
						CusList.add(new Customer(i + 1, recep[wait], wait));
					}
				}
			}

			Collections.sort(CusList);

			for (int i = 0; i < K; i++) {
				Customer now = CusList.get(i);
				int start = now.recepTime;
				int min = Integer.MAX_VALUE;
				int end = 1;

				for (int j = 1; j <= M; j++) {

					if (repair[j] <= start) {
						repair[j] = start + repairs[j];
						if (now.recep == rctcheck && j == rptcheck) {
							sum += now.num;
						}
						break;
					}

					if (repair[j] < min) {
						end = j;
						min = repair[j];
					}

					if (j == M) {
						repair[end] = min + repairs[end];
						if (now.recep == rctcheck && end == rptcheck) {
							sum += now.num;
						}
					}
				}
			}

			int result = 0;
			if (sum == 0) {
				result = -1;
			} else
				result = sum;
			System.out.printf("#%d %d%n", test_case, result);

		}

	}

=======

public class SWEA_2477_차량정비소 {
	public static void main(String[] args) {
		
	}
>>>>>>> b4ef568bb018c519c3d4f2c10744a9ce9dddf516
}
