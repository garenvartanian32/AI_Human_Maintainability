import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class G {

    public void solve() {
        int t = in.nextInt();

        while (t > 0) {
            t--;

            int n = in.nextInt();
            int m = in.nextInt();
            int a[] = new int[n];

            for (int i = 0; i< n; i++) {
                a[i] = in.nextInt();
            }

            TreeMap<Long, Integer> pref = new TreeMap<>();

            long allSum = 0;

            for (int i = 0; i<n; i++) {
                allSum += a[i];
                if (pref.size() == 0 || pref.lastKey() < allSum) {
                    pref.put(allSum, i);
                }
            }

            for (int i = 0; i<m; i++) {
                long x = in.nextLong();
                if (pref.lastKey() >= x) {
                    out.print(pref.ceilingEntry(x).getValue() + " ");
                }
                else if (allSum <= 0) {
                    out.print("-1 ");
                }
                else {
                    long steps = (x - pref.lastKey() + allSum - 1) / allSum;
                    x -= steps * allSum;
                    out.print(steps*n + pref.ceilingEntry(x).getValue());
                    out.print(" ");
                }
            }
            out.println();
        }

    }

    class Pair implements Comparable <Pair>{
        int index;
        int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            return this.value - o.value;
        }

        @Override
        public String toString() {
            return "index="+index+", value="+value;
        }
    }

    String input = "";
    String output = "";
    FastScanner in;
    PrintWriter out;

    void run() throws Exception {
        if (input.length() == 0) {
            in = new FastScanner(System.in);
        } else {
            in = new FastScanner(new File(input));
        }
        if (output.length() == 0) {
            out = new PrintWriter(System.out);
        } else {
            out = new PrintWriter(new File(output));
        }

        solve();

        out.close();

    }

    public static void main(String[] args) throws Exception {
        new G().run();
    }

    class FastScanner {

        BufferedReader bf;
        StringTokenizer st;

        public FastScanner(InputStream is) {
            bf = new BufferedReader(new InputStreamReader(is));
        }

        public FastScanner(File fr) throws FileNotFoundException {
            bf = new BufferedReader(new FileReader(fr));
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(bf.readLine());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] readIntArray(int length) {
            int arr[] = new int[length];
            for (int i = 0; i<length; i++)
                arr[i] = nextInt();
            return arr;
        }
    }
}