
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        int m = scan.nextInt();//
        int f = scan.nextInt();//
        int b = scan.nextInt();//
        
        if (m + f < b) {
            System.out.println("NA");
        } else {
            if (m > b) {
                System.out.println(0);
            } else {
                System.out.println(b - m);
            }
        }
        return;
    }
}

