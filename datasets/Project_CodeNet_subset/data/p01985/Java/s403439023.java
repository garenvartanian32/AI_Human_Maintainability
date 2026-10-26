import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        Scanner ir=new Scanner(System.in);
        for(;;){
            int n=ir.nextInt();
            int m=ir.nextInt();
            if(n==0){
                return;
            }
            Graph[] g=new Graph[n];
            int[] col=new int[n];
            for(int i=0;i<n;i++){
                g[i]=new Graph();
            }
            for(int i=0;i<m;i++){
                int u=ir.nextInt()-1;
                int v=ir.nextInt()-1;
                g[u].add(v);
                g[v].add(u);
            }
            if(!dfs(0,1,col,g)){
                System.out.println(0);
            }
            else{
                int b=0;
                for(int i=0;i<n;i++){
                    if(col[i]==1){
                        b++;
                    }
                }
                TreeSet<Integer> st=new TreeSet<>();
                if(b>=2&&b%2==0){
                    st.add(b/2);
                }
                if(n-b>=2&&(n-b)%2==0){
                    st.add((n-b)/2);
                }
                System.out.println(st.size());
                for(int x : st){
                    System.out.println(x);
                }
            }
        }
    }

    static boolean dfs(int cur,int c,int[] col,Graph[] g){
        if(col[cur]==0){
            col[cur]=c;
        }
        else if(col[cur]!=c){
            return false;
        }
        else{
            return true;
        }
        for(int to : g[cur]){
            if(!dfs(to,c*-1,col,g)){
                return false;
            }
        }
        return true;
    }

    static class Graph extends ArrayList<Integer>{}
}
