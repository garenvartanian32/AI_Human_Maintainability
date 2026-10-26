/*
Written by Kabir Kanha Arora
@kabirkanha
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int needed = (n * y + 99) / 100;
        System.out.println(Math.max(0, needed - x));
    }

}
