import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    boolean[] b = new boolean[26];
    for (int i = 0; i < s.length(); i++) {
      b[s.charAt(i) - 'a'] = true;
    }

    for (int i = 0; i < 26; i++) {
      if (!b[i]) {
        System.out.println((char) ('a' + i));
        return;
      }
    }

    System.out.println("None");

  }
}