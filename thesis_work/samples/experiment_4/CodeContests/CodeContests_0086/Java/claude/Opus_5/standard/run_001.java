import java.io.*;
import java.util.*;

public class Sol {
    static int[] parent;

    static int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int n = nextInt(in), m = nextInt(in);
        int[] p = new int[n + 1];
        for (int i = 1; i <= n; i++) p[i] = nextInt(in);
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;
        for (int i = 0; i < m; i++) {
            int aj = nextInt(in), bj = nextInt(in);
            int ra = find(aj), rb = find(bj);
            if (ra != rb) parent[ra] = rb;
        }
        List<List<Integer>> groups = new ArrayList<>();
        Map<Integer, Integer> rootToGroup = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int r = find(i);
            Integer gi = rootToGroup.get(r);
            if (gi == null) {
                gi = groups.size();
                rootToGroup.put(r, gi);
                groups.add(new ArrayList<>());
            }
            groups.get(gi).add(i);
        }
        int[] ans = new int[n + 1];
        for (List<Integer> positions : groups) {
            List<Integer> values = new ArrayList<>();
            for (int pos : positions) values.add(p[pos]);
            Collections.sort(values, Collections.reverseOrder());
            for (int k = 0; k < positions.size(); k++) {
                ans[positions.get(k)] = values.get(k);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i > 1) sb.append(' ');
            sb.append(ans[i]);
        }
        System.out.println(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while (b < '0' || b > '9') {
            if (b == '-') break;
            b = in.read();
        }
        boolean neg = false;
        if (b == '-') { neg = true; b = in.read(); }
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return neg ? -ret : ret;
    }
}