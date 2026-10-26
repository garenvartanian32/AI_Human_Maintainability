import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kevin on 2/1/17.
 */
public class a {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int radix = in.nextInt();
        String[][] table = new String[radix - 1][radix - 1];
        for (int row = 0; row < radix - 1; row++)
            for (int col = 0; col < radix - 1; col++)
                table[row][col] = Integer.toString((row + 1) * (col + 1), radix);

        for (String[] row : table){
            String ans = "";
            for (String cell : row){
                if (Integer.parseInt(cell) >= 10)
                    ans += " " + cell;
                else
                    ans += "  " + cell;
            }

            System.out.println(ans.trim());
        }
    }
}
