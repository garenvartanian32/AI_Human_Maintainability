import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) a[i] = scanner.nextInt();
    Arrays.sort(a);
    List<Comb> combs = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int r = upperBound(a, a[i] / 2);
      if (r >= 0) {
        if (r + 1 < i && Math.abs(a[r + 1] * 2 - a[i]) < Math.abs(a[r] * 2 - a[i])) combs.add(new Comb(a[i], a[r + 1]));
        else combs.add(new Comb(a[i], a[r]));
      }
    }
    combs.sort((c1, c2) -> {
      if (c1.n < c2.n && c1.r < c2.r) return -1;
      if (c1.n > c2.n && c1.r > c2.r) return 1;
      if (c1.r == c2.r) return c1.n - c2.n;
      throw new IllegalStateException();
    });
    Comb largest = combs.get(combs.size() - 1);
    System.out.printf("%d %d\n", largest.n, largest.r);
  }

  public static int upperBound(int[] a, int k) {
    int left = -1;
    int right = a.length;
    while (right - left > 1) {
      int mid = (left + right) / 2;
      if (a[mid] <= k) {
        left = mid;
      } else {
        right = mid;
      }
    }
    return left;
  }

  private static class Comb {
    private final int n;
    private final int r;

    private Comb(int n, int r) {
      this.n = n;
      this.r = r;
    }

    @Override public String toString() {
      return "Comb{" +
          "n=" + n +
          ", r=" + r +
          '}';
    }
  }
}
