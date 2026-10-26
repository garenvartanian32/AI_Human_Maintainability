import java.util.*;
import java.io.*;
public class A{
    static PrintWriter out;
    static InputReader in;
    public static void main(String args[]){
        out = new PrintWriter(System.out);
        in = new InputReader();
        new A();
        out.flush(); out.close();
    }   
    A(){
        solve();
    }
    final int mod = 998244353;
    final int maxd = 10, max = 100010;
    int n;
    long a[][] = new long[max][2 * maxd], p10[] = new long[3 * maxd];
    int d[] = new int[maxd], c[] = new int[max];

    long mul(long a, long b){
        a *= b;
        if(a >= mod) a %= mod;
        return a;
    }
    long add(long a, long b){
        a += b;
        if(a >= mod) a -= mod;
        return a;
    }
    void add(int id, int x){ 
        while(x != 0){
            a[id][c[id]++] = x % 10;
            x -= x % 10; x /= 10;
        }
        d[--c[id]]++;
    }
    void solve(){
        int n = in.nextInt();
        for(int i = 1; i <= n; i++){
            add(i, in.nextInt());
        }
        p10[0] = 1;
        for(int i = 1; i < 3 * maxd; i++){
            p10[i] = mul(p10[i - 1], 10);
        }
        long ans = 0;
        for(int k = 1; k <= n; k++){
            for(int i = 0; i < maxd; i++){
                long val = 0;
                for(int j = 0; j <= i + 1; j++){
                    val = add(val, mul(a[k][j], p10[2 * j]));
                }
                for(int j = i + 2; j < maxd; j++){
                    val = add(val, mul(a[k][j], p10[j + i + 1]));
                }
                for(int j = 0; j <= i; j++){
                    val = add(val, mul(a[k][j], p10[2 * j + 1]));
                }
                for(int j = i + 1; j < maxd; j++){
                    val = add(val, mul(a[k][j], p10[j + i + 1]));
                }
                ans = add(ans, mul(val, d[i]));
            }
        }
        out.print(ans);
    }   
    public static class InputReader{
        BufferedReader br;
        StringTokenizer st;
        InputReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public int nextInt(){
            return Integer.parseInt(next());
        }
        public long nextLong(){
            return Long.parseLong(next());
        }
        public double nextDouble(){
            return Double.parseDouble(next());
        }
        public String next(){
            while(st == null || !st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){}
            }
            return st.nextToken();
        }
    }
}
        