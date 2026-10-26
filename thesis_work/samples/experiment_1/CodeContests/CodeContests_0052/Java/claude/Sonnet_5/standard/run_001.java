import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            int n = Integer.parseInt(line);
            if (n == 0) break;
            int count = 0;
            for (int i = 0; i < n; i++) {
                br.readLine();
                count++;
            }
            out.append(count).append("\n");
        }
        System.out.print(out);
    }
}