import java.io.*;
import java.util.*;

public class Sol {
    static int n;
    static char[] buf;
    static StringBuilder out = new StringBuilder();

    static void bt(int pos, int maxUsed) {
        if (pos == n) {
            out.append(new String(buf, 0, n)).append('\n');
            return;
        }
        for (int c = 0; c <= maxUsed + 1; c++) {
            buf[pos] = (char) ('a' + c);
            bt(pos + 1, Math.max(maxUsed, c));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        buf = new char[n];
        bt(0, -1);
        System.out.print(out);
    }
}