import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong(), y1 = sc.nextLong(), x2 = sc.nextLong(), y2 = sc.nextLong();
        int n = sc.nextInt();
        Set<Long> fset = new HashSet<Long>();
        for (int i = 0; i < n; i++) {
            long X = sc.nextLong(), Y = sc.nextLong();
            fset.add(X * 200000000L + Y);
        }
        double base = Math.abs(x1-x2)*100.0 + Math.abs(y1-y2)*100.0;
        boolean c1 = fset.contains(x1 * 200000000L + y2);
        boolean c2 = fset.contains(x2 * 200000000L + y1);
        if (!c1 && !c2) {
            System.out.println(base);
            return;
        }
        double extra = (Math.PI/2 - 2) * 10;
        System.out.println(base + extra);
    }
}