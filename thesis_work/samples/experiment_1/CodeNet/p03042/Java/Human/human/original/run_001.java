import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String mymy = s.next();
        String first = mymy.substring(0, 2);
        String second = mymy.substring(2, 4);
        int fi = Integer.parseInt(first);
        int se = Integer.parseInt(second);
        String alg;
        if (0 < fi && fi <= 12) {
            if (0 < se && se <= 12) alg = "AMBIGUOUS";
            else alg = "MMYY";
        } else {
            if (0 < se && se <= 12) alg = "YYMM";
            else alg = "NA";
        }
        System.out.println(alg);
    }
}