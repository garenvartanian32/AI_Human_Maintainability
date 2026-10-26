import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: mihai
 * Date: 2/3/12
 * Time: 10:46 PM
 */
public class problemC {
    private static final int nMax = 100005;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long N1 = N;
        long x = -1, y = -1, d = 2;
        int nrImp = 0;
        
        while(d*d <= N1) {
            while (d*d <= N1 && N1 % d == 0) {
                N1 /= d;
                if (x == -1) x = d;
                else if (y == -1) y = d;
                nrImp ++;
            }
            if (d == 2) d++;
            else d += 2;
        }

        if (nrImp == 0) {
            System.out.println(1);
            System.out.println(0);
        } else {
            if (nrImp == 1) System.out.println(2);
            else {
                System.out.println(1);
                System.out.println(x * y);
            }
        }
    }
}
