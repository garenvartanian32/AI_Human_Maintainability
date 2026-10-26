import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pos = "A";
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(",");
            String x = parts[0], y = parts[1];
            if (pos.equals(x)) pos = y;
            else if (pos.equals(y)) pos = x;
        }
        System.out.println(pos);
    }
}