import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); long x0=(long)st.nval;
        st.nextToken(); long y0=(long)st.nval;
        st.nextToken(); long x1=(long)st.nval;
        st.nextToken(); long y1=(long)st.nval;
        st.nextToken(); int n=(int)st.nval;
        long[] r = new long[n], a = new long[n], b = new long[n];
        Map<Long, List<long[]>> rowSegs = new HashMap<Long, List<long[]>>();
        for (int i = 0; i < n; i++) {
            st.nextToken(); r[i]=(long)st.nval;
            st.nextToken(); a[i]=(long)st.nval;
            st.nextToken(); b[i]=(long)st.nval;
            rowSegs.computeIfAbsent(r[i], k -> new ArrayList<long[]>()).add(new long[]{a[i], b[i]});
        }
        Set<Long> candidates = new HashSet<Long>();
        for (int i = 0; i < n; i++) {
            for (long cc : new long[]{a[i]-1,a[i],a[i]+1,b[i]-1,b[i],b[i]+1}) {
                candidates.add(r[i]*2000000000L + (cc+1000000000L));
            }
        }
        candidates.add(x0*2000000000L+(y0+1000000000L));
        candidates.add(x1*2000000000L+(y1+1000000000L));
        Set<Long> validCells = new HashSet<Long>();
        for (long key : candidates) {
            long rr = Math.floorDiv(key, 2000000000L);
            long cc = key - rr*2000000000L - 1000000000L;
            List<long[]> segsHere = rowSegs.get(rr);
            if (segsHere != null) {
                for (long[] seg : segsHere) if (seg[0] <= cc && cc <= seg[1]) { validCells.add(key); break; }
            }
        }
        long startKey = x0*2000000000L+(y0+1000000000L);
        long endKey = x1*2000000000L+(y1+1000000000L);
        if (!validCells.contains(startKey) || !validCells.contains(endKey)) { System.out.println(-1); return; }
        Map<Long,Integer> dist = new HashMap<Long,Integer>();
        dist.put(startKey, 0);
        Deque<Long> q = new ArrayDeque<Long>();
        q.add(startKey);
        while (!q.isEmpty()) {
            long cur = q.poll();
            if (cur == endKey) { System.out.println(dist.get(cur)); return; }
            long cx = Math.floorDiv(cur, 2000000000L);
            long cy = cur - cx*2000000000L - 1000000000L;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    long nx = cx+dx, ny = cy+dy;
                    long nk = nx*2000000000L+(ny+1000000000L);
                    if (validCells.contains(nk) && !dist.containsKey(nk)) {
                        dist.put(nk, dist.get(cur)+1);
                        q.add(nk);
                    }
                }
            }
        }
        System.out.println(-1);
    }
}