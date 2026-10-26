import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> list = new ArrayList<String>();
        list.add("a");
        String[] tStrings = new String[] {"a", "b","c","d","e","f","g","h","i","j"};
        for (int i = 2 ; i <= n ; i++) {
            List<String> list2 = new ArrayList<String>();
            for (int j = 0 ; j < list.size() ; j++) {
                for (int k = 0 ; k <= countS(list.get(j)) ; k++ ) {
                    list2.add(list.get(j) + tStrings[k]);
                }
            }
            list = list2;
        }

        for (String s : list) {
            System.out.println(s);
        }

    }

    static int countS(String s) {
        Set<String> t = new HashSet<>();
        for (int i = 0 ; i < s.length() ; i++) {
            t.add(s.substring(i, i+1));
        }
        return t.size();
    }

}