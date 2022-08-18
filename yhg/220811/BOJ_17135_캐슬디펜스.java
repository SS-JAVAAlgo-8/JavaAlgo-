package BOJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_17135_캐슬디펜스 {
	static int n;
	static int m;
	static int d;
	static int[][] board;
	static int[][] copyBoard;
	static int archX;
	static int result;
	static List<Integer> archY = new ArrayList<>();  
	static int[][] enemykill;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();
		
		board = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		archX = n;
		archCom(3 , 0);
		
		System.out.println(result);
	}
	
	private static void archCom(int len , int index) {
		if(archY.size() == len) {
			play();
			return;
		}
		
		for (int i = index; i < m; i++) {
			archY.add(i);
			archCom(len , i+1);
			archY.remove(archY.size()-1);
		}
	}
	
	private static void play() {
		boardSet();
		int killcnt = 0;
		for (int t = 0; t < n; t++) {
			enemykillSet();
			for (int i = 0; i < archY.size(); i++) {
				int attackdis = Integer.MAX_VALUE;
				int enemyX = Integer.MAX_VALUE;
				int enemyY = Integer.MAX_VALUE;
				int arY = archY.get(i);
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						if(copyBoard[x][y] == 1) {
							if(attackdis > Math.abs(x-archX) + Math.abs(y - arY)) {
								attackdis = Math.abs(x-archX) + Math.abs(y - arY);
								enemyX = x;
								enemyY = y;
							}
							else if(attackdis == Math.abs(x-archX) + Math.abs(y - arY)) {
								if(y < enemyY) enemyY = y;
							}
						}
					}
				}
				
				if(attackdis <= d) enemykill[enemyX][enemyY] = 1;
				
			}
			
			killcnt += actualKill(0);
			move();
		}
		
		result = Math.max(result, killcnt);
		
	}

	private static void move() {
		
		//나머지 이동
		for (int i = n-1; i >=1; i--) {
			for (int j = 0; j < m; j++) {
				copyBoard[i][j] = copyBoard[i-1][j];
			}
		}
		//맨 윗줄 
		for (int i = 0; i < m; i++) {
			copyBoard[0][i] = 0;
		}
	}

	private static int actualKill(int cnt) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(enemykill[i][j] == 1) {
					copyBoard[i][j] = 0;
					cnt +=1;
				}
				
			}
		}
		return cnt;
	}

	private static void enemykillSet() {
		// TODO Auto-generated method stub
		enemykill = new int[n][m];
	}

	private static void boardSet() {
		copyBoard = new int[n][m];
		for (int i = 0; i < n; i++) {
			copyBoard[i] = Arrays.copyOf(board[i], m);
		}
	}
	
	
}
