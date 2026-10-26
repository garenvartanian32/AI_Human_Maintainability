import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        long total = a, stubs = a;
        while (stubs >= b) {
            long nw = stubs / b;
            total += nw;
            stubs = stubs % b + nw;
        }
        System.out.println(total);
    }
}