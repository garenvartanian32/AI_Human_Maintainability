import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());
        int[] nxt = new int[n];
        for (int i = 0; i < n; i++) nxt[i] = (int)((i + a[i]) % n);
        int[] state = new int[n];
        boolean[] onCycle = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (state[i] != 0) continue;
            List<Integer> path = new ArrayList<Integer>();
            int cur = i;
            while (state[cur] == 0) {
                state[cur] = 1;
                path.add(cur);
                cur = nxt[cur];
            }
            if (state[cur] == 1) {
                int idx = path.indexOf(cur);
                for (int k = idx; k < path.size(); k++) onCycle[path.get(k)] = true;
            }
            for (int node : path) state[node] = 2;
        }
        int count = 0;
        for (boolean b : onCycle) if (b) count++;
        System.out.println(count);
    }
}