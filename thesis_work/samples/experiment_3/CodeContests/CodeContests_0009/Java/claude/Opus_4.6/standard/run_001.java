import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int k = (int) st.nval;
        List<Integer> dual = new ArrayList<>();
        List<Integer> onlyA = new ArrayList<>();
        List<Integer> onlyB = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st.nextToken(); int t = (int) st.nval;
            st.nextToken(); int a = (int) st.nval;
            st.nextToken(); int b = (int) st.nval;
            if (a == 1 && b == 1) dual.add(t);
            else if (a == 1) onlyA.add(t);
            else if (b == 1) onlyB.add(t);
        }
        Collections.sort(dual);
        Collections.sort(onlyA);
        Collections.sort(onlyB);
        long[] preDual = new long[dual.size() + 1];
        for (int i = 0; i < dual.size(); i++) preDual[i + 1] = preDual[i] + dual.get(i);
        long[] preA = new long[onlyA.size() + 1];
        for (int i = 0; i < onlyA.size(); i++) preA[i + 1] = preA[i] + onlyA.get(i);
        long[] preB = new long[onlyB.size() + 1];
        for (int i = 0; i < onlyB.size(); i++) preB[i + 1] = preB[i] + onlyB.get(i);

        long best = -1;
        for (int j = 0; j <= dual.size(); j++) {
            int need = Math.max(k - j, 0);
            if (need > onlyA.size() || need > onlyB.size()) continue;
            long cost = preDual[j] + preA[need] + preB[need];
            if (best == -1 || cost < best) best = cost;
        }
        System.out.println(best);
    }
}