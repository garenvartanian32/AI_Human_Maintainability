import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int i = 5, x = 0;
        while (i>0) {
            x += sc.nextInt();
            i--;
        }

        if (x%5 == 0 && x != 0) {
            System.out.println(x/5);
        }

        else System.out.println("-1");

    }

}