import java.util.*;
import java.io.*;
import java.text.*;

public class Solution{
    static final long mod = (long)1e9+7, IINF = (long)1e19;
    static int MAX = (int)1e6+1, MX = (int)1e7+1, INF = (int)1e7;
    static final double eps = 1e-8;
    static FastReader in;
    static PrintWriter out;
    static boolean multipleTC = false;
    static DecimalFormat df = new DecimalFormat("0.0000000");
    public static void main(String[] args) throws Exception{
        in = new FastReader();
        out = new PrintWriter(System.out);
        for(int i = 1, t = (multipleTC)?ni():1; i<=t; i++)solve(i);
        out.flush();
        out.close();
    }
    
    static void solve(int TC){
        int n = ni();
        String s = n();
        TreeMap<String, Integer> set = new TreeMap<>();
        for(int i = 1; i< s.length(); i++){
            String s1 = s.charAt(i-1)+""+s.charAt(i);
            set.put(s1, set.getOrDefault(s1, 0)+1);
        }   
        String ans = "-";
        int v = 0;
        for(Map.Entry<String, Integer> e:set.entrySet()){
            if(e.getValue()>v){
                v = e.getValue();
                ans = e.getKey();
            }
        }
        pn(ans);
    }
    

    static long modPow(long a, long p){
        long o = 1;
        while(p>0){
            if(p%2==1)o = (a*o)%mod;
            p/=2;
            a = (a*a)%mod;
        }
        return o;
    }

    static int[] ia(int n){
        int[] out = new int[n];
        for(int i = 0; i< n; i++)out[i] = ni();
        return out;
    }

    static long[] la(int n){
        long[] out = new long[n];
        for(int i = 0; i< n; i++)out[i] = nl();
        return out;
    }

    static long gcd(long a, long b){return (b==0)?a:gcd(b,a%b);}
    static int gcd(int a, int b){return (b==0)?a:gcd(b,a%b);}

    static void p(Object o){out.print(o);}
    static void pn(Object o){out.println(o);}
    static void pni(Object o){out.println(o);out.flush();}
    static String n(){return in.next();}
    static String nln(){return in.nextLine();}
    static int ni(){return Integer.parseInt(in.next());}
    static long nl(){return Long.parseLong(in.next());}
    static double nd(){return Double.parseDouble(in.next());}

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws Exception{
            br = new BufferedReader(new FileReader(s));
        }

        String next(){
            while (st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException  e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
} 