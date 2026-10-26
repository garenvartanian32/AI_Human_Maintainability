import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<String, Integer> scoreMap = new LinkedHashMap<>();
        scoreMap.put("AC", 0);
        scoreMap.put("WA", 0);
        scoreMap.put("TLE", 0);
        scoreMap.put("RE", 0);
        for (int i=0; i<n; i++) {
            String s = sc.next();
            scoreMap.put(s, scoreMap.get(s) + 1);
        }
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            System.out.println(entry.getKey() + " x " + entry.getValue());
        }
    }
}