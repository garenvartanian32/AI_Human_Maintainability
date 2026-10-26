
import java.util.*;

public class cf1155a {

    static int first = -1;
    static int second = -1;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String x = in.next();
        
        if (sorted(x)) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(first + " " + second);
        }
        

    }

    public static boolean sorted(String s) {

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) < s.charAt(i - 1)) {

                first = i;
                second = i + 1;

                return false;

            }

        }
        return true;

    }

}
