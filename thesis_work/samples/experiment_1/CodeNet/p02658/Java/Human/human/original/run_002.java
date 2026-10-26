import java.math.BigDecimal;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int N = Integer.parseInt(sc.next());
        BigDecimal[] A = new BigDecimal[N];
        
        for (int i = 0; i < A.length; i++)
        {
            A[i] = sc.nextBigDecimal();
        }
        
        BigDecimal ans = BigDecimal.ONE;
        BigDecimal tmp = new BigDecimal("1000000000000000000");
        
        for (int i = 0; i < A.length; i++)
        {
            if (A[i].compareTo(BigDecimal.ZERO) == 0)
            {
                System.out.println("0");
                sc.close();
                return;
            }
        }
        
        for (int i = 0; i < A.length; i++)
        {
            if (A[i].compareTo(tmp) == 1 || ans.compareTo(tmp) == 1)
            {
                System.out.println("-1");
                sc.close();
                return;
            }
            else
            {
                ans = ans.multiply(A[i]);
            }
        }
        
        if (ans.compareTo(tmp) == 1)
        {
            System.out.println("-1");
            sc.close();
            return;
        }
        else
        {
            System.out.println(ans);
            sc.close();
            return;
        }
    }
}
