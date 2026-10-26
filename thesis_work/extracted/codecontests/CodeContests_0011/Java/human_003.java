import java.lang.reflect.Array;
import java.util.*;
public class Solution {

    public static boolean check(String x,String y,String[][] s){
        int n=s.length-1;
        int a=s[0][1].equals(y)?1:0;
        int b=s[1][0].equals(y)?1:0;
        int c=s[n][n-1].equals(x)?1:0;
        int d=s[n-1][n].equals(x)?1:0;
        int res=a+b+c+d;
        if(res<=2)
        {
            System.out.println(res);
            if(a==1)
                System.out.println("1 2");
            if(b==1)
                System.out.println("2 1");
            if(c==1)
                System.out.println((n+1)+" "+n);
            if(d==1)
                System.out.println(n+" "+(n+1));
            return true;
        }
        return false;

    }

    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0) {
            int n = sc.nextInt();
            String[][] array = new String[n][n];
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextLine().split("");
            }
            if(! check("0","1",array))
                check("1","0",array);
        }
    }

}
