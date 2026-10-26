import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int w = in.nextInt();
            double f = in.nextInt() / 100.0d;
            long people = (long) Math.ceil(n * f);
            if (people <= w) System.out.println("0");
            else {
                //f((int)(((people-1)/(n*1.0f)))==(int)f)w++;
                System.out.println(people - w);
            }
        }

    }
}

