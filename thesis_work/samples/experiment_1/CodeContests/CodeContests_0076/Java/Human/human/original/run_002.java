import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n  = input.nextInt();
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; ++i) {
            int v = input.nextInt();
            if (v == 100) {
                x++;
            } else {
                y++;
            }
        }
        System.out.println(((x != 0 && x % 2 == 0) || (x == 0 && y % 2 == 0))? "YES" :"NO");
    }
}