import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); long m = (long) st.nval;
        long[][] stores = new long[n][2];
        for (int i = 0; i < n; i++) {
            st.nextToken(); stores[i][0] = (long) st.nval;
            st.nextToken(); stores[i][1] = (long) st.nval;
        }
        Arrays.sort(stores, (a, b) -> Long.compare(a[0], b[0]));
        long totalCost = 0;
        long remaining = m;
        for (long[] store : stores) {
            long a = store[0], b = store[1];
            long take = Math.min(b, remaining);
            totalCost += take * a;
            remaining -= take;
            if (remaining == 0) break;
        }
        System.out.println(totalCost);
    }
}