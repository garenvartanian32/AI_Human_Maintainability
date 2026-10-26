import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int f = sc.nextInt();
        int b = sc.nextInt();
        if (m + f < b) {
            System.out.println("NA");
        } else {
            System.out.println(Math.max(0, b - m));
        }
    }
}

