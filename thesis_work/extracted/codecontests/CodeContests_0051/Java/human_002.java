import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int ans;
    static int count;

    public static void main(String[] args) {
        int times = sc.nextInt();
        for (int i = 0; i < times; i++) {
            count = 0;
            int num = sc.nextInt();
            // ?????°???????????????????????????
            KAKESAN(num, false);
            System.out.println(count);
        }
    }

    static void KAKESAN(int num, boolean endFlg) {
        ans = 0;
        int disit = String.valueOf(num).length();
        if (disit == 1) {endFlg = true;}
        if (!endFlg) {
            for (int j = 0; j < disit-1; j++) {
                String numStringA = String.valueOf(num).substring(0, j+1);
                String numStringB = String.valueOf(num).substring(j+1, disit);
                int tmp = Integer.parseInt(numStringA)* Integer.parseInt(numStringB);
                if (tmp > ans) {ans = tmp;}
            }
            count++;
            KAKESAN(ans, false);
        }
    }

}