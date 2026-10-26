import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        long total = 0, mx = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            st.nextToken(); a[i] = (int) st.nval;
            total += a[i];
            mx = Math.max(mx, a[i]);
        }
        long k = mx;
        while ((long) n * k <= 2 * total) k++;
        System.out.println(k);
    }
}