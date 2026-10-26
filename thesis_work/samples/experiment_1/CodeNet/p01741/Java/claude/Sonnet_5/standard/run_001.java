import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double d = sc.nextDouble();
        double best = d * Math.sqrt(2);
        for (int dy = 1; dy <= d + 1e-12; dy++) {
            double valSq = d*d - dy*dy;
            if (valSq >= -1e-12) {
                double dx = Math.sqrt(Math.max(0, valSq));
                if (dx < 1 - 1e-12) {
                    best = Math.max(best, dy + 1);
                }
            }
        }
        System.out.printf("%.12f%n", best);
    }
}