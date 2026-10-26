import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine().trim();
        String b = br.readLine().trim();
        String c = br.readLine().trim();

        int[] fa = new int[26], fb = new int[26], fc = new int[26];
        for (char ch : a.toCharArray()) fa[ch - 'a']++;
        for (char ch : b.toCharArray()) fb[ch - 'a']++;
        for (char ch : c.toCharArray()) fc[ch - 'a']++;

        int maxX = a.length();
        for (int i = 0; i < 26; i++) {
            if (fb[i] > 0) maxX = Math.min(maxX, fa[i] / fb[i]);
        }

        int bestTotal = -1, bestX = 0, bestY = 0;
        for (int x = 0; x <= maxX; x++) {
            int y = a.length();
            for (int i = 0; i < 26; i++) {
                if (fc[i] > 0) {
                    int remaining = fa[i] - x * fb[i];
                    y = Math.min(y, remaining / fc[i]);
                }
            }
            int total = x + y;
            if (total > bestTotal) {
                bestTotal = total;
                bestX = x;
                bestY = y;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bestX; i++) sb.append(b);
        for (int i = 0; i < bestY; i++) sb.append(c);
        int[] used = new int[26];
        for (int i = 0; i < 26; i++) used[i] = bestX * fb[i] + bestY * fc[i];
        for (int i = 0; i < 26; i++) {
            int cnt = fa[i] - used[i];
            for (int k = 0; k < cnt; k++) sb.append((char) ('a' + i));
        }
        System.out.println(sb.toString());
    }
}