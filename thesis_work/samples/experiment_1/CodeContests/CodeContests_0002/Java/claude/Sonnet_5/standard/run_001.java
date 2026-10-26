import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            long l = sc.nextLong(), r = sc.nextLong(), d = sc.nextLong();
            if (d < l) sb.append(d).append("\n");
            else sb.append((r/d+1)*d).append("\n");
        }
        System.out.print(sb);
    }
}