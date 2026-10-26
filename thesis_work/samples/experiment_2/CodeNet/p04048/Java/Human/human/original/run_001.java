import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long x = sc.nextLong();
        long a = Math.max(n - x, x);
        long b = Math.min(n - x, x);
        long ans = n;
        while (b > 0) {
            long q = a / b;
            long r = a % b;
            ans += b * 2 * q;
            if (r == 0) {
                ans -= b;
            }
            a = b;
            b = r;
        }
        System.out.println(ans);
    }

}
