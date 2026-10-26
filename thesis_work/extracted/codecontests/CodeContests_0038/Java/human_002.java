import java.util.*;

public class Main {

  public static void main(String[] args) {
    String[] verdicts = {"AC", "WA", "TLE", "RE"};
    Map<String, Integer> map = new HashMap<>();
    int caseNum = scanner.nextInt();
    for (int i = 0; i < caseNum; i++) {
      String s = scanner.next();
      map.put(s, map.getOrDefault(s, 0) + 1);
    }
    for (String v : verdicts) {
      System.out.println(v + " x " + (map.getOrDefault(v, 0)));
    }
  }

  private static final Scanner scanner = new Scanner(System.in);
}
