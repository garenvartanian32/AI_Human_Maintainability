import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        long[][] stores = new long[n][2];
        for (int i = 0; i < n; i++) { stores[i][0]=sc.nextLong(); stores[i][1]=sc.nextLong(); }
        Arrays.sort(stores, (x,y) -> Long.compare(x[0], y[0]));
        long total = 0;
        long remaining = m;
        for (long[] s : stores) {
            long take = Math.min(s[1], remaining);
            total += take*s[0];
            remaining -= take;
            if (remaining == 0) break;
        }
        System.out.println(total);
    }
}