import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        int[] units = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            st.nextToken();
            units[i] = (int) st.nval / 100;
            total += units[i];
        }
        if (total % 2 != 0) {
            System.out.println("NO");
            return;
        }
        int half = total / 2;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        for (int u : units) {
            for (int s = half; s >= u; s--) {
                if (dp[s - u]) dp[s] = true;
            }
        }
        System.out.println(dp[half] ? "YES" : "NO");
    }
}