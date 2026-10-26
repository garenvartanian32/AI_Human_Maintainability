import java.util.*;
public class Main{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    char c[] = new char[3];

    for(int i=0 ; i<3 ; i++){
      c[i] = s.charAt(i);
    }
    Arrays.sort(c);
    String d = new String(c);
    if(d.equals("abc")){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }
}
