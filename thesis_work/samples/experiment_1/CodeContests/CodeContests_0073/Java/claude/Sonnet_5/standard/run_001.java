import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        List<Character> digits = new ArrayList<Character>();
        for (char c : s.toCharArray()) if (c != '+') digits.add(c);
        Collections.sort(digits);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.size(); i++) { if (i>0) sb.append('+'); sb.append(digits.get(i)); }
        System.out.println(sb);
    }
}