import com.sun.org.apache.bcel.internal.generic.BIPUSH;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class D {
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static MyScanner sc;

    static {
        try {
            sc = new MyScanner();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int MOD = 998244353;
    static BigInteger MOD_BI = BigInteger.valueOf(998244353);

    public static void main(String[] args) {
        doTask();
        out.flush();
    }

    public static void doTask(){
        int n = sc.nextInt();
        long[] a = new long[n];
        int[] d = new int[n];
        int[] dig = new int[11];

//        long result = 0;
        BigInteger result = BigInteger.ZERO;
        for (int i= 0; i<n;i++) {
            a[i] = sc.nextInt();
            long forCnt = a[i];
            while (forCnt > 0) {
                d[i]++;
                forCnt /= 10;
            }
            dig[d[i]]++;
        }
        for (int i= 0; i<n;i++) {
            long num = a[i];
            long a1 = 0;
            long st = 1;
            BigInteger[][] fa = new BigInteger[11][2];
            fa[0][0] = BigInteger.valueOf(a[i]);
            fa[0][1] = BigInteger.valueOf(a[i]);
            int idx = 1;
            long coef = 1;
            BigInteger c = BigInteger.ONE;
            while (idx<=10) {
//                long coef2 = (coef*10);
//                c = BigInteger.valueOf(coef);
                BigInteger c2 = c.multiply(BigInteger.TEN);
//                fa[idx][0] = fa[idx-1][0]/coef2*(coef2*10) + fa[idx-1][0]%coef2;
//                fa[idx][1] = Math.round(fa[idx-1][1]%coef + ((fa[idx-1][1]/coef)*coef2));
                fa[idx][0] = fa[idx-1][0].divide(c2).multiply(c2.multiply(BigInteger.TEN)).add(fa[idx-1][0].mod(c2));
                fa[idx][1] = fa[idx-1][1].mod(c).add((fa[idx-1][1].divide(c)).multiply(c2));
                c = c.multiply(BigInteger.TEN).multiply(BigInteger.TEN);
                idx++;
            }
            for (int j=1; j<=10;j++) {
                int cnt = dig[j];
                if (cnt > 0) {
//                    long aj1 = fa[j][0];
//                    long aj2 = fa[j][1];
//                    if (dig[j] != d[i]) {
//                        result = (result + (aj1 * cnt) % MOD + (aj2 * cnt) % MOD) % MOD;
//                    } else {
//                        result = (result + (aj1 * cnt) % MOD + (aj1%MOD*10 * cnt) % MOD) % MOD;
//                    }
                    BigInteger aj1 = fa[j][0];
                    BigInteger aj2 = fa[j][1];
//                    if (dig[j] != d[i]) {
                    BigInteger cntBi = BigInteger.valueOf(cnt);
                        result = result.add(aj1.multiply(cntBi)).add(aj2.multiply(cntBi)).mod(MOD_BI);
//                    } else {
//                        result = (result + (aj1 * cnt) % MOD + (aj1%MOD*10 * cnt) % MOD) % MOD;
//                    }
                }
            }
        }
        out.println(result);
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
