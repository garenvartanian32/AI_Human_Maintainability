import java.io.*;
import java.text.*;
import java.util.*;

public class B implements Runnable {

    public void solve() throws Exception {
        DecimalFormat df = new DecimalFormat("0.00000000");
        long A = iread(), B = iread(), C = iread();
        if (A == 0) {
            if (B == 0) {
                if (C == 0) {
                    out.write("-1\n");
                    return;
                } else {
                    out.write("0\n");
                    return;
                }
            } else {
                double ans = -C * 1.0 / B;
                out.write("1\n");
                out.write(df.format(ans) + "\n");
                return;
            }
        } else {
            long D = B * B - 4 * A * C;
            if (D < 0) {
                out.write("0\n");
                return;
            } else if (D == 0) {
                double ans = -B * 0.5 / A;
                out.write("1\n");
                out.write(df.format(ans) + "\n");
                return;
            } else {
                double ans1 = (-B - Math.sqrt(D*1.0)) * 0.5 / A;
                double ans2 = (-B + Math.sqrt(D*1.0)) * 0.5 / A;
                if (ans1 > ans2) {
                    double t = ans1;
                    ans1 = ans2;
                    ans2 = t;
                }
                out.write("2\n");
                out.write(df.format(ans1)+"\n");
                out.write(df.format(ans2)+"\n");
            }
        }
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
            solve();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int iread() throws Exception {
        return Integer.parseInt(readword());
    }

    public double dread() throws Exception {
        return Double.parseDouble(readword());
    }

    public long lread() throws Exception {
        return Long.parseLong(readword());
    }

    BufferedReader in;

    BufferedWriter out;

    public String readword() throws IOException {
        StringBuilder b = new StringBuilder();
        int c;
        c = in.read();
        while (c >= 0 && c <= ' ')
            c = in.read();
        if (c < 0)
            return "";
        while (c > ' ') {
            b.append((char) c);
            c = in.read();
        }
        return b.toString();
    }

    public static void main(String[] args) {
        new Thread(new B()).start();
    }
}