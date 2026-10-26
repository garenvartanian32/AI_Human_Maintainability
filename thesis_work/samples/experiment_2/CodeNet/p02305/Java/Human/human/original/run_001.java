
import java.util.*;


public class Main {
    static Scanner input = new Scanner(System.in);

    static public void solve() {
        int c1x = input.nextInt();
        int c1y = input.nextInt();
        int c1r = input.nextInt();
       
        int c2x = input.nextInt();
        int c2y = input.nextInt();
        int c2r = input.nextInt();

        input.close();

        double x = (double) c1x - c2x;
        double y = (double) c1y - c2y;
        double l12 = Math.sqrt(x * x + y * y);
        double plus = (double) c1r + c2r;
        double minus = (double) c1r - c2r;
        if (minus < 0) minus = - minus;

        if (l12 > plus) {
            System.out.println("4");
        }
        if (l12 == plus) {
            System.out.println("3");
        }
        if (l12 < plus && l12 > minus) {
            System.out.println("2");
        }
        if (l12 == minus) {
            System.out.println("1");
        }
        if (l12 < minus) {
            System.out.println("0");
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
