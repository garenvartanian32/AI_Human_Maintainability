import java.util.*;
import java.lang.*;
import java.io.*;

public class Bear_and_Raspberry385A {
   public static void main(String arg[]) throws IOException{
       BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
       String[] s1 = reader.readLine().trim().split(" ");
       int n = Integer.parseInt(s1[0]);
       int c = Integer.parseInt(s1[1]);
       String[] s2 = reader.readLine().trim().split(" ");
       int ans=0;
       for(int i=0; i<n-1; i++){
           int a = Integer.parseInt(s2[i]) - Integer.parseInt(s2[i+1]) - c;
           if(ans<a)
           {
               ans=a;
           }
       }
       System.out.println(ans);

   }
}
