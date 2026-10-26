import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h1=sc.nextInt(),m1=sc.nextInt(),h2=sc.nextInt(),m2=sc.nextInt(),k=sc.nextInt();
        int total = (h2*60+m2)-(h1*60+m1);
        System.out.println(total-k);
    }
}