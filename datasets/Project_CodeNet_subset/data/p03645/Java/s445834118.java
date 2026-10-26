import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      if (a == 1) {
        set1.add(b);
      } else if (b == 1) {
        set1.add(a);
      } else if (a == n) {
        set2.add(b);
      } else if (b == n) {
        set2.add(a);
      }
    }
    for (Integer i : set1) {
      if (set2.contains(i)) {
        System.out.println("POSSIBLE");
        return;
      }
    }
    System.out.println("IMPOSSIBLE");
  }
}