import java.util.*;
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    long a=sc.nextLong(),b=sc.nextLong(),f=1;
    if(a<=0&&b>=0){
      System.out.println("Zero");
    }else if(a>0){
      System.out.println("Positive");
    }else{
      if((b-a)%2==0){
        System.out.println("Negative");
      }else{
        System.out.println("Positive");
      }
    }
  }
}
