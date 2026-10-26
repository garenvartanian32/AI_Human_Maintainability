import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static final long MOD = 1_000_000_007;

  public static void main(String[] args) {
    int N = sc.nextInt();
    int K = sc.nextInt();
    int[] A = new int[N];
    for (int i = 0; i < N; i++) {
      A[i] = sc.nextInt();
    }
    long[] fact = new long[N + 1];
    fact[0] = 1;
    for (int i = 0; i < N; i++) {
      fact[i + 1] = (i + 1) * fact[i] % MOD;
    }
    long[][][] dp = new long[N + 1][N + 1][256];
    dp[0][0][0] = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= i; j++) {
        for (int k = 0; k < 256; k++) {
          dp[i + 1][j + 1][k] += dp[i][j][k ^ A[i]];
          dp[i + 1][j + 1][k] %= MOD;
          dp[i + 1][j][k] += dp[i][j][k];
          dp[i + 1][j][k] %= MOD;
        }
      }
    }
    long ans = 0;
    for (int i = 0; i <= N; i++) {
      ans += dp[N][i][K] * fact[i] % MOD;
      ans %= MOD;
    }
    System.out.println(ans);
  }

}
