package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_3019 {
	static int result = 0;
	public static void bar(int C ,String[] board) {
		result += C;
		for(int i = 0; i <C-3; i++) {
			int count = 0;
			String now = board[i];
			for (int j = i+1; j < i+4; j++) {
				if (board[j].equals(now)) count +=1;
				else break;
			}
			if(count == 3) result +=1;
		}
	}
	public static void square(int C ,String[] board) {
		for(int i = 0; i <C-1; i++) {
			if(board[i].equals(board[i+1]))result +=1 ;
		}
	}
	public static void rightS(int C ,String[] board) {
		int left = 0;
		int mid = 0;
		int right = 0;
		/*
		 *  --   
		 * --     
		 */
		for(int i = 0; i <C-2; i++) {
			left = Integer.parseInt(board[i]);
			mid = Integer.parseInt(board[i+1]);
			right = Integer.parseInt(board[i+2]);
			if(left == mid && mid + 1 == right)result +=1 ;
			
		}
		
		/*  |     
		 *  ||   
		 *   |   
		 */
		
		for(int i = 0; i <C-1; i++) {
			left = Integer.parseInt(board[i]);
			right = Integer.parseInt(board[i+1]);
			if(left -1 == right)result +=1 ;
		}
		
		
	}
	public static void leftS(int C ,String[] board) {
		
		int left = 0;
		int mid = 0;
		int right = 0;
		/*
		 * --   
		 *  --     
		 */
		for(int i = 0; i <C-2; i++) {
			left = Integer.parseInt(board[i]);
			mid = Integer.parseInt(board[i+1]);
			right = Integer.parseInt(board[i+2]);
			if(left - 1 == mid && mid == right)result +=1 ;
			
		}
		
		/*   |   
		 *  ||   
		 *  |   
		 */
		
		for(int i = 0; i <C-1; i++) {
			left = Integer.parseInt(board[i]);
			right = Integer.parseInt(board[i+1]);
			if(left + 1 == right) result +=1 ;
		}
		
	}
	public static void minBar(int C ,String[] board) {
		int left = 0;
		int mid = 0;
		int right = 0;
		/*
		 *   -   
		 * - - -     
		 */
		for(int i = 0; i <C-2; i++) {
			left = Integer.parseInt(board[i]);
			mid = Integer.parseInt(board[i+1]);
			right = Integer.parseInt(board[i+2]);
			if(left == mid && mid == right)result +=1 ;
		}
		
		/*
		 * - - -   
		 *   -     
		 */
		for(int i = 0; i <C-2; i++) {
			left = Integer.parseInt(board[i]);
			mid = Integer.parseInt(board[i+1]);
			right = Integer.parseInt(board[i+2]);
			if(left -1 == mid && right -1 == mid)result +=1 ;
		}
		
		/*  |    |
		 *  ||  || 
		 *  |    |
		 */
		
		for(int i = 0; i <C-1; i++) {
			left = Integer.parseInt(board[i]);
			right = Integer.parseInt(board[i+1]);
			if(left + 1 == right) result +=1 ;
			if(left - 1 == right) result +=1 ;
		}
	}
	public static void rightBar(int C ,String[] board) {
		int left = 0;
		int mid = 0;
		int right = 0;
		/*
		 *     -   - - - 
		 * - - -   -    
		 */
		for(int i = 0; i <C-2; i++) {
			left = Integer.parseInt(board[i]);
			mid = Integer.parseInt(board[i+1]);
			right = Integer.parseInt(board[i+2]);
			if(left == mid && mid == right)result +=1 ;
			if(left + 1 == mid && mid == right)result +=1 ;
		} 
		
		/*   -     - -
		 *   -       -
		 *   - -     -  
		 */
		for(int i = 0; i <C-1; i++) {
			left = Integer.parseInt(board[i]);
			right = Integer.parseInt(board[i+1]);
			if(left  == right)result +=1 ;
			if(left -2 == right) result +=1;
		}
		
	}
	public static void leftBar(int C ,String[] board) {
		int left = 0;
		int mid = 0;
		int right = 0;
		/*
		 * -     - - - 
		 * - - -     -    
		 */
		for(int i = 0; i <C-2; i++) {
			left = Integer.parseInt(board[i]);
			mid = Integer.parseInt(board[i+1]);
			right = Integer.parseInt(board[i+2]);
			if(left == mid && mid == right)result +=1 ;
			if(left  == mid && mid - 1== right)result +=1 ;
		} 
		
		/*     -     - -
		 *     -     -
		 *   - -     -  
		 */
		for(int i = 0; i <C-1; i++) {
			left = Integer.parseInt(board[i]);
			right = Integer.parseInt(board[i+1]);
			if(left  == right)result +=1 ;
			if(left + 2 == right) result +=1;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input  = br.readLine().split(" ");
		int c = Integer.parseInt(input[0]);
		int p = Integer.parseInt(input[1]);
		
		
		String[] board = br.readLine().split(" ");
		
		if(p==1)bar(c , board);
		else if(p == 2)square(c , board);
		else if(p == 3)rightS(c , board);
		else if(p == 4)leftS(c , board);
		else if(p == 5)minBar(c , board);
		else if(p == 6)rightBar(c , board);
		else if(p == 7)leftBar(c , board);
		System.out.println(result);
	}
}
