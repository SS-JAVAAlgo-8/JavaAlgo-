package day0816.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14891_Gear {

	static int g1 = 0, g2 = 0, g3 = 0, g4 = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Deque<Integer> gear1 = new ArrayDeque<>();
		Deque<Integer> gear2 = new ArrayDeque<>();
		Deque<Integer> gear3 = new ArrayDeque<>();
		Deque<Integer> gear4 = new ArrayDeque<>();
		
		// colletion.rotate()
		
		String st = br.readLine();
		char [] arr = st.toCharArray();
		for (int i = 0; i < 8; i++) {
			gear1.offer(Character.getNumericValue(arr[i]));
		}
		
		st = br.readLine();
		arr = st.toCharArray();
		for (int i = 0; i < 8; i++) {
			gear2.offer(Character.getNumericValue(arr[i]));
		}
		
		st = br.readLine();
		arr = st.toCharArray();
		for (int i = 0; i < 8; i++) {
			gear3.offer(Character.getNumericValue(arr[i]));
		}
		
		st = br.readLine();
		arr = st.toCharArray();
		for (int i = 0; i < 8; i++) {
			gear4.offer(Character.getNumericValue(arr[i]));
		}
		
		int rotateNum = Integer.parseInt(br.readLine());
		
		StringTokenizer st1;
		
		int[] arr1 = gear1.stream().mapToInt(Integer::intValue).toArray();
		int[] arr2 = gear2.stream().mapToInt(Integer::intValue).toArray();
		int[] arr3 = gear3.stream().mapToInt(Integer::intValue).toArray();
		int[] arr4 = gear4.stream().mapToInt(Integer::intValue).toArray();
		
		for (int i = 0; i < rotateNum; i++) {
			st1 = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st1.nextToken());
			int rotateDir = Integer.parseInt(st1.nextToken());
			
			switch (gearNum) {
			case 1:
				if(rotateDir == 1) {
					
					if(arr1[4] != arr2[0]) { // 1번 톱니 3시와 2번 톱니 9시를 돌리기전에 비교, 서로다르면 2번 톱니 반시계 회전
						gear2.offerLast(gear2.pollFirst());
						
						if(arr2[4] != arr3[0]) {
							gear3.offerFirst(gear3.pollLast());
							
							if(arr3[4] != arr4[0]) {
								gear4.offerLast(gear4.pollFirst());
							}
								
						}
						
					}
					gear1.offerFirst(gear1.pollLast()); // 시계 방향 회전
					
				}
				
				else {
					
					if(arr1[4] != arr2[0]) {
						gear2.offerFirst(gear1.pollLast());
						
						if(arr2[4] != arr3[0]) {
							gear3.offerLast(gear3.pollFirst());
							
							if(arr3[4] != arr4[0]) {
								gear4.offerFirst(gear4.pollLast());
							}
						}
						
					}
					gear1.offerLast(gear1.pollFirst()); // 반시계 방향 회전
				}
				
				break;
			case 2:
				if(rotateDir == 1) {
					
					if(arr2[0] != arr1[4]) {
						gear1.offerLast(gear1.pollFirst());
					}
					
					if(arr2[4] != arr3[0]) {
						gear3.offerLast(gear3.pollFirst());
						
						if(arr3[4] != arr4[0]) {
							gear4.offerFirst(gear4.pollLast());
						}
					}
					gear2.offerFirst(gear2.pollLast()); 					
				}
				else {
					
					if(arr2[0] != arr1[4]) {
						gear1.offerFirst(gear1.pollLast());
					}
					
					if(arr2[4] != arr3[0]) {
						gear3.offerFirst(gear3.pollLast());
						
						if(arr3[4] != arr4[0]) {
							gear4.offerLast(gear4.pollFirst());
						}
					}
					gear2.offerLast(gear2.pollFirst());					
				}
				
				break;
			case 3:
				if(rotateDir == 1) {
					
					if(arr3[0] != arr2[4]) {
						gear2.offerLast(gear3.pollFirst());
						
						if(arr2[0] != arr1[4]) {
							gear1.offerFirst(gear1.pollLast());
						}
					}
					
					if(arr3[4] != arr4[0]) {
						gear4.offerLast(gear4.pollFirst());
					}
					gear3.offerFirst(gear3.pollLast()); 
				}
				else {
					
					if(arr3[0] != arr2[4]) {
						gear2.offerFirst(gear3.pollLast());
						
						if(arr2[0] != arr1[4]) {
							gear1.offerLast(gear1.pollFirst());
						}
					}
					
					if(arr3[4] != arr4[0]) {
						gear4.offerFirst(gear4.pollLast());
					}
					gear3.offerLast(gear3.pollFirst());	
				}
				break;
			case 4:
				if(rotateDir == 1) {
					
					if(arr4[0] != arr3[4]) {
						gear3.offerLast(gear3.pollFirst());
						
						if(arr3[0] != arr2[4]) {
							gear2.offerFirst(gear2.pollLast());
							
							if(arr2[0] != arr1[4]) {
								gear1.offerLast(gear1.pollFirst());
							}
						}
					}					
					gear4.offerFirst(gear4.pollLast()); 
				}
				else {
					
					if(arr4[0] != arr3[4]) {
						gear3.offerFirst(gear3.pollLast());
						
						if(arr3[0] != arr2[4]) {
							gear2.offerLast(gear2.pollFirst());
							
							if(arr2[0] != arr1[4]) {
								gear1.offerFirst(gear1.pollLast());
							}
						}
					}
					gear4.offerLast(gear4.pollFirst()); 					
				}
				break;

			default:
				break;
			}
			
			arr1 = gear1.stream().mapToInt(Integer::intValue).toArray();
			arr2 = gear2.stream().mapToInt(Integer::intValue).toArray();
			arr3 = gear3.stream().mapToInt(Integer::intValue).toArray();
			arr4 = gear4.stream().mapToInt(Integer::intValue).toArray();
			
		}
		
		System.out.println(gear1);
		System.out.println(gear2);
		System.out.println(gear3);
		System.out.println(gear4);
		
		for (int i = 0; i < 5; i++) {
			g1 = gear1.pollFirst();
			g2 = gear2.pollFirst();
			g3 = gear3.pollFirst();
			g4 = gear4.pollFirst();
		}
		
		int point = 0;
		if(g1 == 1)
			point++;
		if(g2 == 1)
			point += 2;
		if(g3 == 1)
			point += 4;
		if(g4 == 1)
			point += 8;

		System.out.println(point);
	}

}
