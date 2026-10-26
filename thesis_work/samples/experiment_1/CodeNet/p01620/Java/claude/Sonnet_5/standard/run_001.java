import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder alphaBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) alphaBuilder.append((char)('a'+i));
        for (int i = 0; i < 26; i++) alphaBuilder.append((char)('A'+i));
        String alphabet = alphaBuilder.toString();
        Map<Character,Integer> pos = new HashMap<Character,Integer>();
        for (int i = 0; i < alphabet.length(); i++) pos.put(alphabet.charAt(i), i);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine().trim());
            if (n == 0) break;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] keys = new int[n];
            for (int i = 0; i < n; i++) keys[i] = Integer.parseInt(st.nextToken());
            String s = br.readLine().trim();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                int k = keys[i % n];
                int p = ((pos.get(s.charAt(i)) - k) % 52 + 52) % 52;
                result.append(alphabet.charAt(p));
            }
            out.append(result).append("\n");
        }
        System.out.print(out);
    }
}