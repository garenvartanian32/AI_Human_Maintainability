import java.util.Scanner;
import java.io.IOException;
public class Minimum_Integer
{
    public static int getResult(int l,int r,int d)throws IOException
    {
        if(l==d || (l<d && r>=d))
        return ((r/d)+1)*d;
        else
        return d;
    }
    public static void main(String args[])throws IOException
    {
        Scanner sc=new Scanner(System.in);
        int q = sc.nextInt();
        int i;
        for(i=1;i<=q;i++)
        {
            int l=sc.nextInt();
            int r=sc.nextInt();
            int d=sc.nextInt();
            System.out.println(getResult(l,r,d));
        }
        sc.close();
    }
}