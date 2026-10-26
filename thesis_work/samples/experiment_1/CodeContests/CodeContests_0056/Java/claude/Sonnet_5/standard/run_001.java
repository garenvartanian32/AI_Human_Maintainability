import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int maxa = 0;
        for (int i = 0; i < n; i++) { a[i] = sc.nextInt(); maxa = Math.max(maxa, a[i]); }
        for (int k = maxa; k <= 300; k++) {
            long elo = 0;
            for (int x : a) elo += Math.min(x, k);
            long awruk = (long) n*k - elo;
            if (awruk > elo) { System.out.println(k); return; }
        }
        System.out.println(maxa+1);
    }
}