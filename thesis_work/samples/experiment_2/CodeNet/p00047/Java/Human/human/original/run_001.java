import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

class Main {

    public static void main(String[] args) throws IOException {
        doit(args, System.in, System.out);
    }

    static void doit(String[] args, InputStream in, PrintStream out)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        boolean[] cups = new boolean[3];
        cups[0] = true;
        while (true) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            int p = line.charAt(0) - 'A';
            int q = line.charAt(2) - 'A';
            boolean tmp = cups[p];
            cups[p] = cups[q];
            cups[q] = tmp;
        }
        for (int i = 0; i < cups.length; i++) {
            if (cups[i]) {
                out.println((char) (i + 'A'));
                break;
            }
        }
    }
}