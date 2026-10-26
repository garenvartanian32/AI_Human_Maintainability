import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class CommonSubsequence {
    private static String solve(int [] A, int [] B) {
        HashSet<Integer> set = new HashSet<>();
        StringBuilder result = new StringBuilder();
        for(int num : A) set.add(num);

        for(int num : B) {
            if(set.contains(num)) {
                result.append("YES").append("\n").append(1).append(" ").append(num);
                return result.toString();
            }
        }
        return "NO";
    }
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sn.nextInt();
        while(T-- > 0) {
            int N = sn.nextInt();
            int M = sn.nextInt();

            int [] A = new int[N];
            for(int i = 0; i<N; i++) A[i] = sn.nextInt();

            int [] B = new int[M];
            for(int i = 0; i<M; i++) B[i] = sn.nextInt();

            sb.append(solve(A, B)).append("\n");
        }
        System.out.println(sb);
    }
}
