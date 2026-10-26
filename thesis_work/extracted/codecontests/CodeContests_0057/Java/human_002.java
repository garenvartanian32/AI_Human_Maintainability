import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solveaproblem {
    private static int gcd(int a, int b) {
        return b==0 ? a : gcd(b, a%b);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] a = new int[n][n];
        for (int i=0; i<n; i++) {
            char[] arr = reader.readLine().toCharArray();
            for (int j=0; j<n/4; j++) {
                char xx = arr[j];
                int x = xx - '0';
                if (xx>'A') x = xx - 'A' + 10;
                for (int k=0; k<4; k++) {
                    a[i][j*4 + k] = (x >> (3 - k)) & 1;
                }
            }
        }
        boolean[] mark = new boolean[n];
        for (int i=1; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (a[i][j]!=a[i-1][j] || a[j][i]!=a[j][i-1]) mark[i] = true;
            }
        }
        int res = n;
        for (int i=0; i<n; i++) {
            if (mark[i]) res = gcd(i, res);
        }
        System.out.println(res);
    }
}