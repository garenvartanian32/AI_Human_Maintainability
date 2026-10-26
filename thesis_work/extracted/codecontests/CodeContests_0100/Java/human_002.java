import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long max = Long.MIN_VALUE;
        int n = sc.nextInt();
        int[] binaryF = new int[n];
        int[][] p = new int[n][11];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < 10; j++) {
                int num = sc.nextInt();
                sum += num;
                if (j != 9) sum <<= 1;
            }
            binaryF[i] = sum;
        }
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < 11; j++) {
                p[i][j] = sc.nextInt();
            }
        }
        for(int i = 1; i < 1024; i++) {
            int add  = 0;
            for(int j = 0; j < n; j++) {
                int count = oneCount(i & binaryF[j]);
                    add += p[j][count];
            }
            max = Math.max(max, add);
        }
        System.out.println(max);
    }
    private static int oneCount(int num) {
        int count = 0;
        while(num != 0) {
            if((num & 1) == 1) count++;
            num >>= 1;
        }
        return count;
    }
}