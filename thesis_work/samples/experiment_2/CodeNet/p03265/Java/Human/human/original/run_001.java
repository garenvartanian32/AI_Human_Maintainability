import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            final String s = reader.readLine();

            final String[] sl = s.split(" ");
            int x1 = Integer.parseInt(sl[0]);
            int y1 = Integer.parseInt(sl[1]);
            int x2 = Integer.parseInt(sl[2]);
            int y2 = Integer.parseInt(sl[3]);

            int dx = x2 - x1;
            int dy = y2 - y1;

            int tx = x2;
            int ty = y2;
            for (int i = 0; i < 2; i++) {
                int tmp = dx;
                dx = -dy;
                dy = tmp;
                tx = tx + dx;
                ty = ty + dy;
                System.out.print(tx);
                System.out.print(" ");
                System.out.print(ty);
                if (i != 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
