import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next(), b = sc.next(), c = sc.next();
        int[] remaining = new int[26];
        for (char ch : a.toCharArray()) remaining[ch-'a']++;
        int[] cb = new int[26], cc = new int[26];
        for (char ch : b.toCharArray()) cb[ch-'a']++;
        for (char ch : c.toCharArray()) cc[ch-'a']++;
        StringBuilder used = new StringBuilder();
        while (true) {
            boolean canB = true, canC = true;
            for (int i = 0; i < 26; i++) { if (remaining[i] < cb[i]) canB = false; if (remaining[i] < cc[i]) canC = false; }
            if (canB) {
                for (int i = 0; i < 26; i++) remaining[i] -= cb[i];
                used.append(b);
            } else if (canC) {
                for (int i = 0; i < 26; i++) remaining[i] -= cc[i];
                used.append(c);
            } else break;
        }
        for (int i = 0; i < 26; i++) for (int j = 0; j < remaining[i]; j++) used.append((char)('a'+i));
        System.out.println(used);
    }
}