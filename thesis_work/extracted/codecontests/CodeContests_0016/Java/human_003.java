

import java.util.Scanner;

public class c1540easy {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        solve(s.nextInt(), s);
    }


    static long mod = 1000000007;
    public static void solve(int l, Scanner s) {
        int c[] = new int[l];
        for (int i = 0; i < l; i++) {
            c[i] = s.nextInt();
        }

        int b[] = new int[l - 1];
        for (int i = 0; i < l - 1; i++) {
            b[i] = s.nextInt();
        }

        s.nextInt();
        int q = s.nextInt();

        long next[] = new long[]{1};
        long last[];

        int min = q;

        int sum;

        int bsum = 0;

        for (int i = 0; i < l; i++) {
            last = next;
            next = new long[last.length + c[i] + 1];

            for (int j = 0; j < last.length; j++) {
                for (int k = 0; k <= c[i]; k++) {
                    sum = k + j;
                    if (sum >= min) {
                        next[sum] = (next[sum] + last[j]) % mod;
                    }
                }
            }

            bsum += i != l - 1 ? b[i] : 0;
            min += q + bsum;
        }

        long erg = 0;
        for (int i = 0; i < next.length; i++) {
            erg = (erg + next[i]) % mod;
        }

        System.out.println(erg);
    }
}
