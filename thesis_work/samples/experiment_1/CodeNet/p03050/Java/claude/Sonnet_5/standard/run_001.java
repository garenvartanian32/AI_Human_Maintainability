import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long total = 0;
        long q = 1;
        while (q*q < n) {
            if (n % q == 0) {
                long m = n/q - 1;
                if (q < m) total += m;
            }
            q++;
        }
        System.out.println(total);
    }
}