import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author silviase
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        DStringEquivalence solver = new DStringEquivalence();
        solver.solve(1, in, out);
        out.close();
    }

    static class DStringEquivalence {
        int n;

        public void solve(int testNumber, Scanner in, PrintWriter out) {
            n = in.nextInt();
            dfs("", 'a');
        }

        void dfs(String res, char c) {
            if (res.length() == n) {
                System.out.println(res);
            } else {
                for (char i = 'a'; i <= c; i++) {
                    dfs(res + i, ((i == c) ? (char) (c + 1) : c));
                }
            }
        }

    }
}

