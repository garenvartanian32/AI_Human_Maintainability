import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int mini = i;
            for (int j = i; j < n; j++) if (a[j] < a[mini]) mini = j;
            if (mini != i) {
                int t = a[i]; a[i] = a[mini]; a[mini] = t;
                swaps++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { if (i>0) sb.append(' '); sb.append(a[i]); }
        System.out.println(sb);
        System.out.println(swaps);
    }
}