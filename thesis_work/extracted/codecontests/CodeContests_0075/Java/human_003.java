import java.util.Scanner;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Main{
    public static void main(String[]args)throws IOException
    {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = sc.nextInt(),c=sc.nextInt();
            int d = sc.nextInt(),mx=0;
            for(int i=2;i<=n;i++)
            {
               int m=sc.nextInt();
                mx = max(d-m-c,mx);
                d=m;
            }
            out.println(mx);
        out.flush();
    }
    private static int max(int a,int b)
    {
        if(a>b)
            return a;
        else
            return b;
    }
}