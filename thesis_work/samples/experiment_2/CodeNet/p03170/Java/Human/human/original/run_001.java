import java.io.*;
import java.util.*;

public class Main {

    static int n,a[];
    static Boolean[][]memo;
    static boolean dp(int turn,int rem){
        if (rem<=0)return false;
        if (memo[turn][rem]!=null)return memo[turn][rem];
        boolean ans=false;
        for (int i =0;i<n;i++){
            if (rem>=a[i]){
                ans=ans|(!dp(1-turn,rem-a[i]));
            }
        }
        return memo[turn][rem]=ans;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        n = sc.nextInt();
        int k =sc.nextInt();
        a= new int[n];
        for (int i =0;i<n;i++)a[i]=sc.nextInt();
        memo= new Boolean[2][k+1];
        boolean[][]dp= new boolean[2][k+1];
        for (int i =0;i<=k;i++){
            for (int j=0;j<n;j++){
                if (i+a[j]<=k){
                    dp[0][i+a[j]]|=(!dp[1][i]);
                    dp[1][i+a[j]]|=(!dp[0][i]);
                }
            }
        }
        if (dp[0][k]){
            pw.println("First");
        }
        else pw.println("Second");
        pw.flush();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(FileReader r) {
            br = new BufferedReader(r);
        }

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }

}