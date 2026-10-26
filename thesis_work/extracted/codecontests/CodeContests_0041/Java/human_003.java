import java.util.*;

public class Main {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        for (int tmp = 0; tmp < m; tmp++) {
            int w = sc.nextInt();

            list.add(w);
        }

        long INF = 1000000007;
        long[] dp = new long[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 0; i < m; i++) {
            dp[list.get(i)] = -1;
        }

        for (int j = 2; j <= n; j++) {
            if (dp[j] != -1 && dp[j - 1] != -1 && dp[j - 2] != -1) {
                dp[j] = (dp[j - 1] + dp[j - 2]) % INF;
            } else if (dp[j] != -1 && dp[j - 1] == -1) {
                dp[j] = (dp[j - 2]);
            } else if (dp[j] != -1 && dp[j - 2] == -1) {
                dp[j] = (dp[j - 1]);
            }


        }
        if (dp[n] == -1) {
            System.out.println(0);
        } else {
            System.out.println(dp[n] % INF);
        }
    }
}