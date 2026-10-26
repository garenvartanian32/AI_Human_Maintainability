
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexander
 */
public class Problem288D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = in.nextInt();
        str = str.toLowerCase();
        StringBuffer res = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < (n+97))
            {
                res.append(Character.toUpperCase(str.charAt(i)));
            }
            else
            {
                res.append(str.charAt(i));
            }
        }
        System.out.println(res);
    }
}
