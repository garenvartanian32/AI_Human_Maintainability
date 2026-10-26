import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        cowsandpoker solver = new cowsandpoker();
        solver.solve(1, in, out);
        out.close();
    }

    static class cowsandpoker {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            String players = in.next();
            if (players.indexOf("I") != -1) {
                int count = 0;
                for (int i = 0; i < players.length(); i++) {
                    if (players.charAt(i) == 'I')
                        count++;
                }
                if (count == 1)
                    out.print(count);
                else
                    out.print("0");
            } else {
                int count = 0;
                for (int i = 0; i < players.length(); i++) {
                    if (players.charAt(i) == 'A')
                        count++;
                }
                out.print(count);
            }

        }

    }
}

