import java.io.*;
import java.util.*;

/*CODE BY SHIKHAR TYAGI*/

public class A {

    public static void main(String args[]) {
        FastScanner scn = new FastScanner();
        int t = scn.nextInt();
        while (t-- > 0) {
            int a = scn.nextInt();
            int b = scn.nextInt();
            int c = scn.nextInt();
            long ans = 0;
            int turns = c / 2;
            turns = Math.min(b, turns);
            b -= turns;
            ans += 3 * turns;
            turns = b / 2;
            turns = Math.min(a, turns);
            ans += 3 * turns;
            System.out.println(ans);

        }
    }

    public static int bin(ArrayList<Integer> ar, int target) {
        int h = ar.size() - 1;
        int l = 0;
        int ans = -1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (ar.get(m) > target) {
                h = m - 1;
            } else {
                l = m;
                ans = m;
            }
        }
        return ans;
    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(nextToken());
        }

        long nextLong() {
            return Long.parseLong(nextToken());
        }

        double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
}

