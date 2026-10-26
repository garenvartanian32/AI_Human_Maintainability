import java.util.Scanner;
import java.util.Random;;
import java.util.Arrays;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int A = scan.nextInt();
        int B = scan.nextInt();

        long h[] =new long[N];
        for(int i = 0; i < N; i++){
            h[i] = scan.nextLong();
        }
        Arrays.sort(h);

        long l = 0;
        long r = 1000000000;

        while(l < r) {
            long m = (r + l) / 2;
            //System.out.println(m);
            long need = 0;
            for (int i = 0; i < N; i++) {
                long remind = h[i] - (B * m);
                if (remind > 0) {
                    need += (remind + A - B - 1 )/ (A - B);
                }
            }
            if (need > m) {
                l = m+1;
            } else {
                r = m;
            }
        }
        System.out.println(l);
    }
}
