import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for(int i = 0; i < t; i++) {
      long a = sc.nextLong();
      long b = sc.nextLong();
      long c = sc.nextLong();
      long d = sc.nextLong();
      long g = gcd(b, d);
      long s = b - g + (a % g);
      if(b > a) {
        System.out.println("No");
      } else if(b > d) {
        System.out.println("No");
      } else if(c >= b) {
        System.out.println("Yes");
      } else {
        if(s > (c % b)) {
          System.out.println("No");
        } else {
          System.out.println("Yes");
        }
      }
    }
  }

  public static long gcd(long x, long y) {
    if(y == 0) return x;
    return gcd(y, x % y);
  }
}