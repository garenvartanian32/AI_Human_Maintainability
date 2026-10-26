import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c1=0, c2=0;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x == 100) c1++; else c2++;
        }
        if (c1 % 2 != 0) { System.out.println("NO"); return; }
        if (c1 == 0 && c2 % 2 != 0) { System.out.println("NO"); return; }
        System.out.println("YES");
    }
}