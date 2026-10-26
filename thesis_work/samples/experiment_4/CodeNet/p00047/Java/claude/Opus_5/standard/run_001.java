import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char ball = 'A';
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(",");
            char x = parts[0].charAt(0);
            char y = parts[1].charAt(0);
            if (ball == x) ball = y;
            else if (ball == y) ball = x;
        }
        System.out.println(ball);
    }
}