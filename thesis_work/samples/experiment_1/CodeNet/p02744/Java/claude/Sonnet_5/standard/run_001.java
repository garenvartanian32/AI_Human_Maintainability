import java.util.*;

public class Main {
    static int n;
    static StringBuilder out = new StringBuilder();
    static void rec(int[] s, int len, int maxc) {
        if (len == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append((char)('a'+s[i]));
            out.append(sb).append("\n");
            return;
        }
        for (int c = 0; c <= maxc+1; c++) {
            s[len] = c;
            rec(s, len+1, Math.max(maxc, c));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        rec(new int[n], 0, -1);
        System.out.print(out);
    }
}