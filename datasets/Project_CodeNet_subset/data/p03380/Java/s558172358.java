import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0;i < n;i++) a[i] = sc.nextInt();

        Arrays.sort(a);
        for(int i = 0;i < a.length / 2;i++) {
            int tmp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = tmp;
        }

        double diff = Double.MAX_VALUE;
        double half = (double)a[0] / 2;
        int idx = -1;
        for(int i = 0;i < a.length;i++) {
            double tmpDiff = Math.abs(half - a[i]);
            if(diff >= tmpDiff) {
                diff = tmpDiff;
                idx = i;
            }
        }

        System.out.println(a[0] + " " + a[idx]);
    }
}
