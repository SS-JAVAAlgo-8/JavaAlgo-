package BOJ_1790;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberContinuation2 {
    // 제시된 숫자범위를 보고 일반적인 탐색방법으로는 시간초과가 난다고 판단
    // 존재하는 규칙을 생각해봄 
	// 1 ~ 9 까지는 1자리 9개
	// 10 ~ 99까지는 2자리 90
	// 100 ~ 999 까지는 3자리 900
	// 1000 ~ 9999 4자리 9000
	// 10000 ~ 99999 5자리 90000
	// 100000 ~ 999999 6자리 900000
	// 1000000 ~ 9999999 7자리 ...
	// 10000000 ~ 99999999 8자리 ... 고정적으로 증가
    // 접근방식은 틀리지 않았으나 구현하는 방법에서 해맴
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st= new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		long k = Integer.parseInt(st.nextToken());
//		long N_Count=0;
//		long Pre_Ncount=0;
//		int i = 1;
//		long j = 9;
//		long start = 1;
//		long l=0;
//		while(N!=0) {
//			N/=10;
//			Pre_Ncount=N_Count; // 이전 숫자의 갯수 카운팅
//          N_Count+=(i*j); // 숫자갯수 카운팅
//			if (N_Count > k) { // k가 해당 숫자 카운트 안에 존재 한다면
//				k=k-Pre_Ncount; //해당 숫자자리의 시작지점부터의 차이를 저장
//				l=(k/i)-1; // 해당 숫자자리에서 몇번째 숫자에 k번째숫자가 존재하는지 확인
//				k%=i; // k번쨰 숫자가 start 내에 몇번째에 존재하는지 확인
//				start+=l; // k번째가 존재하는 숫자 도출
//				System.out.println(String.valueOf(start).charAt((int)k+1 >= i ? i-((int)k+1) : (int)k+1)); // 숫자 내에서 k번째가 어디있는지 출력
//				break;
//			}
//			start*=10;
//			i++;
//			j*=10;
//		}
//		if (N_Count < k) { //
//			System.out.println(-1);
//		}
//	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long k = Integer.parseInt(st.nextToken());
		int i = 1; //자릿수 
		long j = 9; // 해당 자릿수의 갯수
		long start = 0; // 안에 k번쨰를 포함하고 있는 숫자
		long l = 0;
		while (true) {
			if (k <= (i * j)) { // k번째 수가 포함된 범위를 구하면 true
				l = (k-1) / i; // start부터 k번째가 몇번째인지 구하기 x번쨰의 숫자를 체크하면 해당숫자의 다음숫자가 되기때문에 k-1을 해서 저장
				k = (k-1) % i; // start숫자 안에서 k번째가 몇번째인지 구하기 위한 변수
				break;
			}
			k -= (i * j); // k
			start+=j; // 시작할 숫자를 카운팅
			i++; // 자릿수 
			j *= 10; // 해당 자릿수의 숫자갯수
		}
		start = (start +1) + l; //
		//System.out.println(start);

        // 도출된 start가 원래의 숫자보다 크다면 -1 아닐경우 start의 k번째 숫자룰 구해서 출력
		if (N < start) {
			System.out.println(-1);
		}else {
			System.out.println(String.valueOf(start).charAt((int)k));
		}
	}
}
