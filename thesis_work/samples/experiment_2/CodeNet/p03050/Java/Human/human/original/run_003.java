import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long sum = 0L;
        for(long m = 1; m * m <= n; m++) {
        	if(n % m != 0) continue;
        	long k = n / m;
        	if(k - 1 != 0 && n / (k - 1) == n % (k - 1))
        		sum += k - 1;
        	if(m - 1 != 0 && n / (m - 1) == n % (m - 1))
        		sum += m - 1;
        }
        System.out.println(sum);
    }
}
