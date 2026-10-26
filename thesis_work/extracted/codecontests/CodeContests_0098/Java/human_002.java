import java.util.*;
import java.awt.*;
import static java.lang.System.*;
import static java.lang.Math.*;
public class Main {
    static int mod=1000000007;
    public static void main(String[]$) {
        Scanner sc = new Scanner(in);
        long n=sc.nextLong();
        long p=sc.nextLong();
        long t=p;
        HashMap<Long,Long> map=new HashMap<>();
        long ans=1;
        long g=(long)sqrt(p);
        for (long i = 2; i <= g+1l;i++) {
            while(p%i==0){
                p/=i;
                map.put(i,map.getOrDefault(i,0l)+1);
            }
        }
        if(p!=1)map.put(p,1l);
        for(Long i:map.keySet()){
            long j=map.get(i)/n;
            for (long k = 0; k < j; k++) {
                ans *= i;
            }
        }
        out.println(ans);
    }
    static long power(long x,int n){
        long mod=1000000007;
        long ans=1;
        while (n>0) {
            if((n&1)==1){
                ans=(ans*x)%mod;
            }
            x=(x*x)%mod;
            n>>=1;
        }
        return ans;
    }
    //最大公約数
    static int gcd (int a,int b) {
        int temp;
        while((temp = a%b)!=0) {
            a = b;
            b = temp;
        }
        return b;
    }
    //※最小公倍数はa*b/gcd(a,b)である
    static class UF{
        static int size=51;
        static int[] par=new int[size];
        //初期化
        static void init(){
            for (int i = 1; i <size ; i++) {
                par[i]=i;
            }
        }
        //根をたどる
        static int root(int x){
            if(par[x]==x){
                return x;
            }else{
                return par[x]=root(par[x]);
            }
        }
        //同じ根をもつか(同じ集合に属するか)判定
        static boolean same(int x,int y){
            return root(x)==root(y);
        }
        //合体
        static void unite(int x,int y){
            x=root(x);
            y=root(y);
            if(x==y)return;
            par[x]=y;
        }
    }
}