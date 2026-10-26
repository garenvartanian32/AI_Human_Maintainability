import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        long L = sc.nextInt();
        long R = sc.nextInt();

        if ((R - L) >= 2019) {
            System.out.println(0);
        }
        else {
            long res = 2019;
            for(long i = L; i <= R; i++) {
                for(long j = i + 1; j <= R; j++ ) {
                    long temp = (i * j) % 2019;
                    res = Math.min(res , temp);

                }
            }
            System.out.println(res);
        }

    }
}
