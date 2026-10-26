import java.io.*;
import java.util.*;

public class Main {
  BufferedReader br;
  int a, b, c, ans;
  
  public void go() throws Exception {
    br = new BufferedReader(new InputStreamReader(System.in));
    String[] ss = br.readLine().split(" ");
    a = Integer.parseInt(ss[0]);
    b = Integer.parseInt(ss[1]);
    c = Integer.parseInt(ss[2]);
    
    ans = Integer.MAX_VALUE;
    int u = a * c;
    for (int i = 0; i <= 1000010; i++) {
      int du = (i + c) * b;
      if (du >= u) {
        ans = i;
        break;
      }
    }
    
    System.out.println(ans);
  }
  
  public static void main(String[] args) throws Exception {
    new Main().go();
  }
}
