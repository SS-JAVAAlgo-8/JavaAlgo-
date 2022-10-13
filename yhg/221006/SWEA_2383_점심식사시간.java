
import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static List<Person> pList;
    static List<Stairs> sList;
    static int[] stair;
    static int[] down;
    static int result;
    static int[] visited;

    static class Person implements Comparable<Person> {
        int x;
        int y;
        int distance;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Person o) {

            return this.distance - o.distance;
        }

    }

    static class Stairs {
        int x;
        int y;
        int cost;

        Stairs(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testacse = 1; testacse <= T; testacse++) {
            N = sc.nextInt();

            pList = new ArrayList<>();
            sList = new ArrayList<>();
            stair = new int[2];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int temp = sc.nextInt();
                    if (temp == 1) {
                        pList.add(new Person(i, j));
                    }

                    if (temp >= 2) {
                        sList.add(new Stairs(i, j, temp));
                    }
                }
            }

            down = new int[pList.size()];

            result = Integer.MAX_VALUE;
            per(0);
            System.out.println("#" + testacse  + " " + result);
        }

    }

    private static void per(int depth) {
        if (depth == pList.size()) {
            check(0);
            check(1);
            int tMax = Math.max(stair[0], stair[1]);
            result = Math.min(result, tMax);
            return;
        }

        for (int i = 0; i < 2; i++) {
            down[depth] = i;
            per(depth + 1);
        }
    }

    private static void check(int sindex) {
        PriorityQueue<Person> pq = new PriorityQueue<>();
        Stairs curSt = sList.get(sindex);
        for (int i = 0; i < pList.size(); i++) {
            if (down[i] == sindex) {
                Person now = pList.get(i);
                now.distance = Math.abs(now.x - curSt.x) + Math.abs(now.y - curSt.y);
                pq.offer(now);
            }
        }

        ArrayList<Person> waiting = new ArrayList<>();

        int time = 0;
        int filled = 0;
        while (pq.size() > 0) {
            time+=1;
            int len = pq.size();
            for (int i = 0; i < len; i++) {
                Person now = pq.poll();
                if (now.distance - 1 > 0) {
                    now.distance -= 1;
                    waiting.add(now);
                } else if (now.distance == 0) {
                    if(filled < 3) {
                        filled +=1;
                        now.distance -=1;
                        waiting.add(now);
                    }
                    else {
                        waiting.add(now);
                    }
                } else if(now.distance == -curSt.cost) {
                    filled-=1;
                } else if(now.distance > -curSt.cost){
                    now.distance -=1;
                    waiting.add(now);
                }
                
            }
            pq.addAll(waiting);
            waiting.clear();
        }
        
        stair[sindex] = time;
    }

}
