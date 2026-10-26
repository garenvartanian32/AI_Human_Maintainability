import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long q = sc.nextLong();
        long n = q;
        List<Long> factors = new ArrayList<Long>();
        for (long d = 2; d*d <= n; d++) {
            while (n % d == 0) { factors.add(d); n /= d; }
        }
        if (n > 1) factors.add(n);
        int K = factors.size();
        if (K <= 1) System.out.println(1);
        else if (K == 2) System.out.println(2);
        else {
            System.out.println(1);
            System.out.println(factors.get(0)*factors.get(1));
        }
    }
}