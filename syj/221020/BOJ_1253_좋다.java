/*
투 포인터
- left(0), right(N - 1) 포인터 생성
- 입력받은 수들을 정렬
- 작은 수부터 존재하는지 체크

1. 인덱스 유효한지 체크
2. 본인을 포함하는지 체크
3. 인덱스 조정 
- 만약 투 포인터가 가리키는 두 수의 합이 체크할 수보다 크면 right--
- 만약 투 포인터가 가리키는 두 수의 합이 체크할 수보다 작으면 left++
- 만약 투 포인터가 가리키는 두 수의 합이 체크할 수일 경우 ans++ 다음 수 체크
*/
package study.day1020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {	// 메모리 : 12,808 kb		실행시간 : 760 ms
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		int[] num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);	// 오름차순 정렬
		int left, right;	// 투 포인터
		for (int i = 0; i < N; i++) {	// 모든 수 체크
			int target = num[i];	// 이번에 체크할 수
			left = 0;	// left 맨 처음 가리키기
			right = N-1;	// right 맨 끝 가리키기
			
			while(true) {
				// 인덱스 유효한지 체크 & 두 포인터가 만나면 break
				if(left >= N-1 || left == right) break;
				// 본인을 포함할 경우 포인터 이동 후 continue
				if(left == i) {
					left++;
					continue;
				}else if(right == i) {
					right--;
					continue;
				}
				// 두 포인터가 가리키는 수의 합 저장
				int temp = num[left] + num[right];
				// 더한 수가 체크할 수일 경우 결과 카운트 증가 & break
				if(temp == target) {
					ans++;
					break;
				}
				// 더한 수가 체크할 수보다 클 경우 right--(그래야 더 작아짐)
				if(temp > target) {
					right--;
					continue;
				}else {	// 더한 수가 체크할 수보다 작은 경우 left++(그래야 더 커짐)
					left++;
					right = N-1;
				}
				
			}
		}
		System.out.println(ans);
	}
}
