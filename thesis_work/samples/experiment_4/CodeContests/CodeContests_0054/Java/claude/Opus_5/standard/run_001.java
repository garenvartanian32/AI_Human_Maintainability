import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) { st.nextToken(); a[i] = (int) st.nval; }
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int mini = i;
            for (int j = i; j < n; j++) {
                if (a[j] < a[mini]) mini = j;
            }
            if (mini != i) {
                int tmp = a[i]; a[i] = a[mini]; a[mini] = tmp;
                swaps++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        sb.append('\n').append(swaps);
        System.out.println(sb);
    }
}