import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int maxL = 0;
        int minR = Integer.MAX_VALUE;
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            maxL = Math.max(maxL, sc.nextInt());
            minR = Math.min(minR, sc.nextInt());
        }

        System.out.println(Math.max(0, minR - maxL + 1));
    }
}