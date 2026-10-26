import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static Scanner scanner = new Scanner(System.in);
  
  public static long distance(long x1, long x2, long y1, long y2) {
	return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
  }
  
  public static double slope(long x1, long x2, long y1, long y2) {
    return (x1-x2)*(y1-y2);
  }
  
  public static void main(String[] args) {
    
    long x1,y1,x2,y2,x3,y3,k1,k2,d1,d2;
    while(scanner.hasNext()) {
      x1 = scanner.nextInt();
      y1 = scanner.nextInt();
      x2 = scanner.nextInt();
      y2 = scanner.nextInt();
      x3 = scanner.nextInt();
      y3 = scanner.nextInt();
      if(distance(x1, x2, y1, y2)!=distance(x2, x3, y2, y3)) System.out.println("NO");
      else if(slope(x2, x3, y1, y2)==slope(x1, x2, y2, y3)){
        System.out.println("NO");
      }
      else System.out.println("YES");
    }
  }
}
