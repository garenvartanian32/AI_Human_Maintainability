import java.util.*;
public class Main {
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int L = sc.nextInt();
    int R = sc.nextInt();
    
    if ((R / 2019) > (L / 2019)) {
      System.out.println(0);
      return;
    }

    int ans = Integer.MAX_VALUE;
    for (int l = L; l < R; l++) {
      for (int r = l+1; r <=R; r++) {
        int mod = (int)(((long)l*r)%(long)2019);
        if (mod < ans) {
          ans = mod;
        }
      }
    }
    
    System.out.println(ans);
  }  
}
