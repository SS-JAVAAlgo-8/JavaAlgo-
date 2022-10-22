package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13549_숨바꼭질3 {

    static int N, K;
    static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        visited = new int[100001];
        visited[N] = 1;
        System.out.println(play());

    }

    private static int play() {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { N, 0 });
        
        while (dq.size() > 0) {
            int[] next = dq.removeFirst();
            int posi = next[0];
            int time = next[1];

            if (posi == K)
                return time;

            if (posi * 2 <= 100000 && visited[posi * 2] == 0) {
                visited[posi * 2] = 1;
                dq.addLast(new int[] { posi * 2, time});

            }

            if (posi - 1 >= 0 && visited[posi - 1] == 0) {
                visited[posi - 1] = 1;
                dq.addLast(new int[] { posi - 1, time + 1 });
            }

            if (posi + 1 <=100000 && visited[posi + 1] == 0) {
                visited[posi + 1] = 1;
                dq.addLast(new int[] { posi + 1, time + 1 });
            }
        }

        return 0;
    }
}
