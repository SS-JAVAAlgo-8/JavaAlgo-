import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 괄호쌍의 부분집합문제
    // 반드시 괄호 안의 연산자는 한개만 있어야 한다
	static String[] num_list;
	static int max_value = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		num_list = br.readLine().split("");
		check(num_list,0,0,3);
		bw.write(String.valueOf(max_value));
		bw.flush();
	}

	static void check(String[] result,int idx,int x,int y) { // 괄호 쌍을 설치 할지 안할지의 부분집합 , x는 괄호의 시작 좌표 , y는 괄호가 닫히는 좌표
		if (y > result.length) { // 끝 괄호가 문자열 전체 길이를 초과 할 경우 연산 진행
			int total=Integer.valueOf(result[0]);
			for(int i = 1; i <result.length-1;i+=2) {
				switch(result[i]) {
				case "+":
					total+=Integer.valueOf(result[i+1]);
					continue;
				case "-":
					total-=Integer.valueOf(result[i+1]);
					continue;
				case "*":
					total*=Integer.valueOf(result[i+1]);
					continue;
				}
			}
			if(max_value<total) {
				max_value=total;
			}
			return;
		}
		check(result,idx+1,x+2,y+2); //현재 위치에 괄호를 집어 넣지 않았을때는 현재위치로부터 연산자 + 숫자 인덱스인 +2를 해줌
		String[] result2=calc(result,x); // 괄호를 넣었다고 가정하고 현재 인덱스와 그 다음 숫자와 연산을 진행해줌
		check(result2,idx+1,x+2,y+2); // 연산한 문자열(괄호를 넣었다고 판단)을 재귀
	}

	static String[] calc(String[] result,int x) { // 연산 진행
		String[] num_list_2= new String[result.length-2];
		for(int i = 0 ; i <x ; i++) {
			num_list_2[i]=result[i];
		}
		int a = Integer.valueOf(result[x]);
		int b = Integer.valueOf(result[x+2]);
		switch(result[x+1]) {
		case "+":
			num_list_2[x]=String.valueOf(a+b);
			break;
		case "-":
			num_list_2[x]=String.valueOf(a-b);
			break;
		case "*":
			num_list_2[x]=String.valueOf(a*b);
			break;
		}
		for(int i = x+1 ; i <num_list_2.length ; i++) {
			num_list_2[i]=result[i+2];
		}
		return num_list_2;
	}
}
