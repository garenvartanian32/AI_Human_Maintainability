import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            out.append(parseRecord(s)).append('\n');
        }
        System.out.print(out);
    }

    static String parseRecord(String s) {
        Map<Character, Character> next = new HashMap<>();
        Map<Character, Character> prev = new HashMap<>();
        Set<Character> known = new HashSet<>();
        char first = s.charAt(0);
        known.add(first);
        char curPrev = first;
        int i = 1;
        while (i < s.length()) {
            String dir;
            if (i + 1 < s.length() && s.charAt(i) == '-' && s.charAt(i+1) == '>') {
                dir = "back";
                i += 2;
            } else {
                dir = "fwd";
            }
            char cur = s.charAt(i);
            i++;
            known.add(cur);
            if (dir.equals("back")) {
                next.put(curPrev, cur);
                prev.put(cur, curPrev);
            } else {
                prev.put(curPrev, cur);
                next.put(cur, curPrev);
            }
            curPrev = cur;
        }
        char head = 0;
        for (char c : known) {
            if (!prev.containsKey(c)) { head = c; break; }
        }
        StringBuilder sb = new StringBuilder();
        Character cur = head;
        while (cur != null) {
            sb.append((char) cur);
            cur = next.get(cur);
        }
        return sb.toString();
    }
}