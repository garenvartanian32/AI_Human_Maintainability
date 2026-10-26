import java.util.*;
import static java.lang.System.out;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int count = 0;
        for (int i = a; i <= b ; i++) {
            if(isKaibunNum(i)){
                count += 1;
            }
        }
        out.print(count);
    }

    private static boolean isKaibunNum(int n){
        CharSequence sb = new StringBuilder(String.valueOf(n));
        int l = sb.length();

        if(l==1){
            return true;
        }
        int checkCount = l/2;

        for (int i = 0; i < checkCount ; i++) {
            char left = sb.charAt(i);
            char right = sb.charAt(l-1-i);
            if(left != right){
                return false;
            }
        }
        return true;
    }
}