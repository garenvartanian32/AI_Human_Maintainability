import java.io.*;
import java.util.*;

public class Main {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        int[] a = new int[n+1];
        tree = new int[n+1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        PriorityQueue<Data> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            pq.offer(new Data(a[i], i));
        }

        HashMap<Integer, ArrayList<Query>> map = new HashMap<>();
        int q = Integer.parseInt(in.readLine());
        for (int i = 1; i <= q; i++) {
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (!map.containsKey(k)) map.put(k, new ArrayList<>());
            map.get(k).add(new Query(p, i));
        }

        int[] ans = new int[q+1];
        int size = 0;
        while (!pq.isEmpty()) {
            Data data = pq.poll();
            size++;
            update(data.index, 1);
            if (!map.containsKey(size)) continue;
            ArrayList<Query> queries = map.get(size);
            for (Query query : queries) {
                int left = 1;
                int right = n;
                while (right-left > 3) {
                    int mid = (right-left)/2 + left;
                    if (query(mid) >= query.pos) right = mid;
                    else left = mid;
                }

                for (int i = left; i <= right; i++) {
                    if (query(i) >= query.pos) {
                        ans[query.id] = i;
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= q; i++) {
            out.println(a[ans[i]]);
        }
        out.close();
    }

    static int[] tree;
    static int query(int k) {
        int res = 0;
        while (k >= 1) {
            res += tree[k];
            k -= k&-k;
        }
        return res;
    }
    static void update(int k, int add) {
        while (k <= n) {
            tree[k] += add;
            k += k&-k;
        }
    }

    static class Query {
        int pos, id;
        public Query(int pos, int id) {
            this.pos = pos;
            this.id = id;
        }
    }

    static class Data implements Comparable<Data> {
        int val, index;
        public Data(int val, int index) {
            this.val = val;
            this.index = index;
        }
        @Override
        public int compareTo(Data data) {
            if (this.val == data.val) {
                return this.index-data.index;
            } else {
                return data.val-this.val;
            }
        }
    }
}
/*

 */