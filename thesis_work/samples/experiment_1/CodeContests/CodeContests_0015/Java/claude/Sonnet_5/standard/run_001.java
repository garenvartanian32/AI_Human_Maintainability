import java.util.*;
import java.io.*;

public class Main {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        String[][] lines = new String[t][2];
        int maxM = 0;
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = st.nextToken();
            lines[i][1] = st.nextToken();
            maxM = Math.max(maxM, Integer.parseInt(lines[i][1]));
        }
        long[][] length = new long[10][maxM+1];
        for (int d = 0; d < 10; d++) length[d][0] = 1;
        for (int s = 1; s <= maxM; s++) {
            for (int d = 0; d < 10; d++) {
                int nd = d+1;
                String ds = Integer.toString(nd);
                long sum = 0;
                for (char c : ds.toCharArray()) sum = (sum + length[c-'0'][s-1]) % MOD;
                length[d][s] = sum;
            }
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String n = lines[i][0];
            int m = Integer.parseInt(lines[i][1]);
            long total = 0;
            for (char c : n.toCharArray()) total = (total + length[c-'0'][m]) % MOD;
            out.append(total).append("\n");
        }
        System.out.print(out);
    }
}