
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int f = scanner.nextInt();
        int b = scanner.nextInt();
        if( m > b ){
            System.out.println("0");
        } else if (m + f >= b) {
            System.out.println(b - m);
        } else {
            System.out.println("NA");
        }
    }
}

