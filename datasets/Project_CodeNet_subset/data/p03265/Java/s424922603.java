import java.io.*;
import java.util.*;

public class Main {
    static MyReader in = new MyReader();

    public static void main(String[] args) {
        int x1 = in.i();
        int y1 = in.i();
        int x2 = in.i();
        int y2 = in.i();
        int x3 = x2 + y1 - y2;
        int y3 = y2 + x2 - x1;
        int x4 = x1 + y1 - y2;
        int y4 = y1 + x2 - x1;
        System.out.printf("%d %d %d %d\n", x3, y3, x4, y4);
    }
}

class MyReader extends BufferedReader {
    char[] cbuf = new char[1024];
    int head = 0;
    int tail = 0;

    MyReader() {
        super(new InputStreamReader(System.in));
    }

    char next() {
        if (head == tail) {
            try {
                tail = super.read(cbuf, 0, cbuf.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            head = 0;
        }
        return cbuf[head++];
    }

    int i() {
        int k = next() - '0';
        boolean minus = k == -3;
        int n = minus ? 0 : k;
        while (0 <= (k = next() - '0') && k <= 9) n = 10 * n + k;
        return minus ? -n : n;
    }

    int[] ii(final int N) {
        int[] a = new int[N];
        for (int j = 0; j < a.length; j++) a[j] = i();
        return a;
    }

    long l() {
        int k = next() - '0';
        boolean minus = k == -3;
        long n = minus ? 0 : k;
        while (0 <= (k = next() - '0') && k <= 9) n = 10 * n + k;
        return minus ? -n : n;
    }

    char[] s(final int N) {
        char[] s = new char[N];
        for (int i = 0; i < N; i++) {
            s[i] = next();
        }
        next();
        return s;
    }

    public int read(char[] cbuf) {
        int i;
        char c;
        for (i = 0; (c = next()) != ' ' && c != '\n'; i++) cbuf[i] = c;
        return i;
    }

    public String readLine() {
        try {
            return super.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
