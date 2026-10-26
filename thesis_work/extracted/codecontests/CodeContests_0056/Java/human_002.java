 
import java.util.Scanner;
 
public class java1 {
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); 
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
                 sum += a;
               max = Math.max(a, max);}

               int k = 2*sum/n + 1;
                 if (k < max )
                 System.out.println(max) ; 
              else 
               System.out.println(k) ;
      
    }
}