import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        int xmax = Integer.MIN_VALUE;
        int ymin = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            if (xmax < tmp) {
                xmax = tmp;
            }
        }
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            if (ymin >= tmp) {
                ymin = tmp;
            }
        }
        sc.close();

        if (x < ymin && ymin <= y) {
            if (xmax < ymin) {
                System.out.println("No War");
                return;
            }
        }
        System.out.println("War");

    }
}