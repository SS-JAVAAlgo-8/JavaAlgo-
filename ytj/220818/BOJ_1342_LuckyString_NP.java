package study.day220818.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 12572    148

public class BOJ_1342_LuckyString_NP {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static char[] chars;
    static long[] facts;
    static int cnt;

    public static void main(String[] args) throws IOException {

        input = new BufferedReader(new StringReader(src));
        chars = input.readLine().toCharArray();
        Arrays.sort(chars);

        do {
            if (isLucky()) {
                cnt++;
            }
        } while (nextPermutation());

        System.out.println(cnt);
    }

    static boolean isLucky() {
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }
        return true;
    }

    static boolean nextPermutation() {
        int firstPeak = chars.length - 1;
        while (firstPeak > 0 && chars[firstPeak - 1] >= chars[firstPeak]) {
            firstPeak--;
        }

        if (firstPeak == 0) {
            return false;
        }

        int gtBeforeFirstPeak = chars.length - 1;
        while (chars[firstPeak - 1] >= chars[gtBeforeFirstPeak]) {
            gtBeforeFirstPeak--;
        }

        swap(firstPeak - 1, gtBeforeFirstPeak);

        for (int reverseIdx = chars.length - 1; firstPeak < reverseIdx; firstPeak++, reverseIdx--) {
            swap(firstPeak, reverseIdx);
        }
        return true;
    }

    static void swap(int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // REMOVE_START
    private static String src = "aabbbaa";
    // REMOVE_END

}