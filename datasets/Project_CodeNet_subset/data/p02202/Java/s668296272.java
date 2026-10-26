import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += sc.nextInt() - (i + 1);
        }
        System.out.println(total);
    }
}

