import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Main{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] a = new int[M];
        int[] b = new int[M];
        int[] c = new int[M];
        int[][] d = new int[N + 1][N + 1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j){
                    d[i][j] = 0;
                }
                else{
                    d[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }
        
        for(int i=0; i<M; i++){
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            c[i] = sc.nextInt();
            d[a[i]][b[i]] = c[i];
            d[b[i]][a[i]] = c[i];
        }
        int ans = 0;
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(d[i][j]>d[i][k]+d[k][j]){
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
        boolean[] memo = new boolean[M];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int k=0; k<M; k++){
                    if(d[i][j]==d[i][a[k]]+c[k]+d[b[k]][j] && !memo[k]){
                        ans++;
                        memo[k] = true;
                    }
                }
            }
        }
        System.out.println(M - ans);
    }
}