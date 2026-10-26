import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        st.nextToken(); int k = (int) st.nval;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) { st.nextToken(); a[i] = (int) st.nval; }
        int[] b = new int[m];
        for (int i = 0; i < m; i++) { st.nextToken(); b[i] = (int) st.nval; }
        List<Integer> merged = new ArrayList<Integer>();
        int i=0, j=0;
        while (i < n && j < m) { merged.add(a[i++]); merged.add(b[j++]); }
        while (i < n) merged.add(a[i++]);
        while (j < m) merged.add(b[j++]);
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < merged.size(); x++) { if (x>0) sb.append(' '); sb.append(merged.get(x)); }
        System.out.println(sb);
    }
}