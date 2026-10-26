import java.io.*;
import java.util.*;

public class C{
    public static void main(String[] args) {
        Scanner f = new Scanner(System.in);
        int w = Integer.parseInt(f.nextLine());
        String s = f.nextLine();
        for (int i = 0; i < w - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                System.out.println("YES");
                System.out.println((i + 1) + " " + (i + 2));
                return;
            }
        }
        System.out.println("NO");
    }
}