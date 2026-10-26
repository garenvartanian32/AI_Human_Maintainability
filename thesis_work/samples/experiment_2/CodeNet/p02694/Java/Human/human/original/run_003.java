import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long x= sc.nextLong();
        long sum = 100;
        int y = 0;

        while(true){
            if(sum>=x){
                break;
            }
            y++;
            sum = sum + (long)Math.floor(sum*0.01);       
        }

            System.out.println(y);
    }
}
