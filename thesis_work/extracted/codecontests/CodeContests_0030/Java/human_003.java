import java.util.*;

public class Test {

    int n;
    String first, second;
    Scanner sca = new Scanner(System.in);

    void start() {
        first = sca.next(); second = sca.next();
        n = sca.nextInt();
        for (int i = 0; i <= n; i++) {
            System.out.println(first + " " + second);
            if (i == n) break;
            String killed = sca.next(), replace = sca.next();
            if (killed.equals(first)) {
                first = replace;
            } else if (killed.equals(second)) {
                second = replace;
            }
        }
    }

    public static void main(String[] args) {

        new Test().start();
    }
}