import java.io.*;
import java.util.*;

public class Sol {
    static int charToIdx(char c) {
        if (Character.isLowerCase(c)) return c - 'a';
        else return 26 + (c - 'A');
    }

    static char idxToChar(int i) {
        i = ((i % 52) + 52) % 52;
        if (i < 26) return (char) ('a' + i);
        else return (char) ('A' + i - 26);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            int n = Integer.parseInt(line);
            if (n == 0) break;
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int[] keys = new int[n];
            for (int i = 0; i < n; i++) keys[i] = Integer.parseInt(stk.nextToken());
            String s = br.readLine().trim();
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int k = keys[i % n];
                int origIdx = charToIdx(c);
                int decIdx = origIdx - k;
                res.append(idxToChar(decIdx));
            }
            out.append(res).append('\n');
        }
        System.out.print(out);
    }
}