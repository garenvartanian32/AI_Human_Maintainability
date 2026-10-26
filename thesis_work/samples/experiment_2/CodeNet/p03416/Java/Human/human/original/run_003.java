import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a, b, c[] = new int[5], cnt = 0;
    
    a = Integer.parseInt(sc.next());
    b = Integer.parseInt(sc.next());
    
    for (int i = a; i <= b; i++) {
        int temp = i;
        for (int j = 4; j >= 0; j--) {
            c[j] = temp / (int)Math.pow(10, j);
            temp = temp % (int)Math.pow(10, j);
        }
        if (c[0] == c[4] && c[1] == c[3])
            cnt++;
    }
    
    System.out.println(cnt);
  }
}