import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int k = (int) st.nval;
        st.nextToken(); int a = (int) st.nval;
        st.nextToken(); int b = (int) st.nval;
        st.nextToken(); int c = (int) st.nval;
        st.nextToken(); int d = (int) st.nval;

        if (n == 4 || (n + 1) > k) {
            System.out.println(-1);
            return;
        }

        Set<Integer> special = new HashSet<>(Arrays.asList(a, b, c, d));
        List<Integer> middle = new ArrayList<>();
        for (int x = 1; x <= n; x++) {
            if (!special.contains(x)) middle.add(x);
        }

        StringBuilder p1 = new StringBuilder();
        p1.append(a).append(' ').append(c);
        for (int x : middle) p1.append(' ').append(x);
        p1.append(' ').append(d).append(' ').append(b);

        StringBuilder p2 = new StringBuilder();
        p2.append(c).append(' ').append(a);
        for (int x : middle) p2.append(' ').append(x);
        p2.append(' ').append(b).append(' ').append(d);

        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}