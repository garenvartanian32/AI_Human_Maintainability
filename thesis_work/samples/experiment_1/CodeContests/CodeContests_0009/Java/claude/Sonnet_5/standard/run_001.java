import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int k = (int) st.nval;
        List<Long> both = new ArrayList<Long>(), onlyA = new ArrayList<Long>(), onlyB = new ArrayList<Long>();
        for (int i = 0; i < n; i++) {
            st.nextToken(); long t = (long) st.nval;
            st.nextToken(); int a = (int) st.nval;
            st.nextToken(); int b = (int) st.nval;
            if (a==1 && b==1) both.add(t);
            else if (a==1) onlyA.add(t);
            else if (b==1) onlyB.add(t);
        }
        Collections.sort(both); Collections.sort(onlyA); Collections.sort(onlyB);
        long[] prefBoth = new long[both.size()+1];
        for (int i=0;i<both.size();i++) prefBoth[i+1]=prefBoth[i]+both.get(i);
        long[] prefA = new long[onlyA.size()+1];
        for (int i=0;i<onlyA.size();i++) prefA[i+1]=prefA[i]+onlyA.get(i);
        long[] prefB = new long[onlyB.size()+1];
        for (int i=0;i<onlyB.size();i++) prefB[i+1]=prefB[i]+onlyB.get(i);
        Long best = null;
        for (int x = 0; x <= Math.min(both.size(), k); x++) {
            int needA = Math.max(0, k-x);
            int needB = Math.max(0, k-x);
            if (needA > onlyA.size() || needB > onlyB.size()) continue;
            long total = prefBoth[x] + prefA[needA] + prefB[needB];
            if (best == null || total < best) best = total;
        }
        System.out.println(best == null ? -1 : best);
    }
}