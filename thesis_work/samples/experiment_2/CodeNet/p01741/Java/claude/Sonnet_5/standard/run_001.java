import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double d = Double.parseDouble(br.readLine().trim());
        double best = d * Math.sqrt(2);
        int floorD = (int) Math.floor(d + 1e-9);
        for (int k = 1; k <= floorD; k++) {
            double rem = d*d - (double)k*k;
            if (rem < 0) rem = 0;
            double dy = Math.sqrt(rem);
            double val = k + Math.max(1.0, dy);
            if (val > best) best = val;
        }
        System.out.printf("%.12f%n", best);
    }
}