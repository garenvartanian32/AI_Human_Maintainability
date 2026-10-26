import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n=(int)st.nval;
        st.nextToken(); long d=(long)st.nval;
        st.nextToken(); long a=(long)st.nval;
        long[][] monsters = new long[n][2];
        for (int i=0;i<n;i++){
            st.nextToken(); monsters[i][0]=(long)st.nval;
            st.nextToken(); monsters[i][1]=(long)st.nval;
        }
        Arrays.sort(monsters, (p,q)->Long.compare(p[0],q[0]));
        PriorityQueue<long[]> heap = new PriorityQueue<long[]>((p,q)->Long.compare(p[0],q[0]));
        long cumulative = 0;
        long bombs = 0;
        for (long[] m : monsters) {
            long x = m[0], h = m[1];
            while (!heap.isEmpty() && heap.peek()[0] < x) {
                cumulative -= heap.poll()[1];
            }
            long eff = h - cumulative;
            if (eff > 0) {
                long need = (eff + a - 1)/a;
                bombs += need;
                cumulative += need*a;
                long end = x + 2*d;
                heap.add(new long[]{end, need*a});
            }
        }
        System.out.println(bombs);
    }
}