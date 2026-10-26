import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;
    static int find(int x) {
        while (parent[x] != x) { parent[x] = parent[parent[x]]; x = parent[x]; }
        return x;
    }
    static boolean union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) { parent[rx] = ry; return true; }
        return false;
    }
    public static void main(String[] args) throws IOException {
        final long MOD = 1000000007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[m+1];
        for (int i = 0; i <= m; i++) parent[i] = i;
        Set<Integer> singleSeen = new HashSet<Integer>();
        int rankCount = 0;
        List<Integer> chosen = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            String vec = br.readLine().trim();
            List<Integer> ones = new ArrayList<Integer>();
            for (int j = 0; j < vec.length(); j++) if (vec.charAt(j)=='1') ones.add(j+1);
            if (ones.size() == 0) continue;
            else if (ones.size() == 1) {
                int v = ones.get(0);
                if (!singleSeen.contains(v)) {
                    singleSeen.add(v);
                    rankCount++;
                    chosen.add(i+1);
                }
            } else {
                int a = ones.get(0), b = ones.get(1);
                if (union(a,b)) { rankCount++; chosen.add(i+1); }
            }
        }
        long size = 1;
        for (int i = 0; i < rankCount; i++) size = size*2%MOD;
        StringBuilder out = new StringBuilder();
        out.append(size).append("\n");
        out.append(chosen.size()).append("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chosen.size(); i++) { if (i>0) sb.append(' '); sb.append(chosen.get(i)); }
        out.append(sb).append("\n");
        System.out.print(out);
    }
}