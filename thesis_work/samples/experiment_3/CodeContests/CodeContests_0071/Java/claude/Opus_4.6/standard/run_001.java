import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            char base = Character.toLowerCase(c);
            int rank = base - 'a' + 1;
            if (rank <= n) sb.append(Character.toUpperCase(base));
            else sb.append(base);
        }
        System.out.println(sb);
    }
}