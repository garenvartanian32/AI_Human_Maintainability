import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();
        Map<Long,Integer> seen = new HashMap<Long,Integer>();
        long a = s;
        int i = 1;
        seen.put(a, i);
        while (true) {
            if (a % 2 == 0) a = a/2;
            else a = 3*a+1;
            i++;
            if (seen.containsKey(a)) { System.out.println(i); return; }
            seen.put(a, i);
        }
    }
}