import java.util.*;
import java.io.*;

public class F {
    public static long MOD = 1000000007;

    public static int[] par;
    public static int[] sz;

    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[m];
        StringBuilder outedges = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(b, n+1);
            }else{
                edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }
        par = new int[n+2];
        Arrays.fill(par, -1);
        sz = new int[n+2];
        Arrays.fill(sz, 1);
        for(int i = 0; i < m; i++){
            if(find(edges[i].a) != find(edges[i].b)){
                union(edges[i].a, edges[i].b);
                cnt++;
                outedges.append((i+1)+" ");
            }
        }
        long val = 1;
        for(int i = 0; i < cnt; i++) val = (val*2)%MOD;
        out.println(val + " " + cnt);
        out.println(outedges.toString().trim());
        out.close();
    }
    static class Edge{
        public int a;
        public int b;
        public Edge(int a, int b){
            this.a=a;
            this.b=b;
        }
    }
    public static void union(int x, int y){
        int xs = find(x);
        int ys = find(y);
        if(sz[xs] > sz[ys]){
            par[xs] = ys;
            sz[xs]+=sz[ys];
        }else{
            par[ys] = xs;
            sz[ys]+=sz[xs];
        }
    }
    public static int find(int y){
        if(par[y] == -1) return y;
        int x = find(par[y]);
        par[y] = x;
        return x;
    }
}
