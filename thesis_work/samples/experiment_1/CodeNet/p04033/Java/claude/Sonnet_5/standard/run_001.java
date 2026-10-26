import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        if (a > 0) System.out.println("Positive");
        else if (b < 0) {
            long cnt = b - a + 1;
            System.out.println(cnt % 2 == 1 ? "Negative" : "Positive");
        } else {
            System.out.println("Zero");
        }
    }
}