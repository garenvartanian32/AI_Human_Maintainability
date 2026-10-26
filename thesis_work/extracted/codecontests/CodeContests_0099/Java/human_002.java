
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int Q = scanner.nextInt();
        long A[] = new long[N];
        for(int i = 0; i < N; i++){
            A[i] = scanner.nextInt();
        }
        boolean flag[] = new boolean[N + 1];
        long ans = (long)1e18;
        flag[N] = true;
        for(int loop = 0; loop < N; loop++){
            ArrayList<Long> v = new ArrayList<>(), v_tmp = new ArrayList<>();
            for(int i = 0; i <= N; i++){
                if(flag[i]){
                    Collections.sort(v_tmp);
                    for(int j = 0; j <= v_tmp.size() - K; j++){
                        v.add(v_tmp.get(j));
                    }
                    v_tmp.clear();
                }else{
                    v_tmp.add(A[i]);
                }
            }
            if(v.size() >= Q){
                Collections.sort(v);
                ans = Math.min(ans, v.get(Q - 1) - v.get(0));
            }
            int pos = 0;
            long num = (long)1e18;
            for(int i = 0; i < N; i++){
                if(!flag[i] && A[i] < num){
                    pos = i;
                    num = A[i];
                }
            }
            flag[pos] = true;
        }
        System.out.println(ans);
    }
}
