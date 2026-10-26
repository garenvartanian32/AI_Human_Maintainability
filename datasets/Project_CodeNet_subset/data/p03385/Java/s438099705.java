import java.io.IOException;
import java.util.Scanner;
 
public class Main {
  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    
    String S = s.next();
    
    if (S.contains("a") && S.contains("b") && S.contains("c")) {
    	System.out.println("Yes");
    } else {
    	System.out.println("No");
    }

  }
}
