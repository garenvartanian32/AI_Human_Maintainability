import java.util.*;
 
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    long a,v,b,w,t;
    
    a = sc.nextInt();
    v = sc.nextInt();
    b = sc.nextInt();
    w = sc.nextInt();
    t = sc.nextInt();
    
    if(Math.abs(a-b) <= ((v-w)*t)){
        System.out.println("YES");
    }else{
   		System.out.println("NO");
   	}
  }
}