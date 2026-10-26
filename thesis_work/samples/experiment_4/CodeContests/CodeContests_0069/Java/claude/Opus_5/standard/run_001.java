import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); long x0 = (long) st.nval;
        st.nextToken(); long y0 = (long) st.nval;
        st.nextToken(); long x1 = (long) st.nval;
        st.nextToken(); long y1 = (long) st.nval;
        st.nextToken(); int n = (int) st.nval;
        Map<Long, Boolean> cells = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st.nextToken(); long r = (long) st.nval;
            st.nextToken(); long a = (long) st.nval;
            st.nextToken(); long b = (long) st.nval;
            for (long c = a; c <= b; c++) {
                cells.put(r * 2000000000L + c, true);
            }
        }
        long start = x0 * 2000000000L + y0;
        long end = x1 * 2000000000L + y1;
        if (!cells.containsKey(start) || !cells.containsKey(end)) {
            System.out.println(-1);
            return;
        }
        Map<Long, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        ArrayDeque<Long> q = new ArrayDeque<>();
        q.add(start);
        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};
        while (!q.isEmpty()) {
            long cur = q.poll();
            if (cur == end) {
                System.out.println(dist.get(cur));
                return;
            }
            long cr = cur / 2000000000L;
            long cc = cur - cr * 2000000000L;
            for (int d = 0; d < 8; d++) {
                long nr = cr + dr[d];
                long nc = cc + dc[d];
                long nxt = nr * 2000000000L + nc;
                if (cells.containsKey(nxt) && !dist.containsKey(nxt)) {
                    dist.put(nxt, dist.get(cur) + 1);
                    q.add(nxt);
                }
            }
        }
        System.out.println(-1);
    }
}