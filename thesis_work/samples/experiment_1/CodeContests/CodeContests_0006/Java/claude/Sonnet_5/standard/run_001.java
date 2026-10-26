import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] x = new long[n], v = new long[n];
        for (int i=0;i<n;i++) x[i]=sc.nextLong();
        for (int i=0;i<n;i++) v[i]=sc.nextLong();
        Long[] idx = new Long[n];
        for (int i=0;i<n;i++) idx[i]=(long)i;
        Arrays.sort(idx, (a,b) -> Long.compare(x[a.intValue()], x[b.intValue()]));
        long total = 0;
        for (int i=0;i<n-1;i++) {
            int a = idx[i].intValue(), b = idx[i+1].intValue();
            if (v[a] <= v[b]) total += x[b]-x[a];
        }
        System.out.println(total);
    }
}