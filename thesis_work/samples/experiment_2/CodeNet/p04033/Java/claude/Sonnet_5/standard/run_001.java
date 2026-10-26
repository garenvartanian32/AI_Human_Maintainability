import java.io.*;
public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); long a = (long) st.nval;
        st.nextToken(); long b = (long) st.nval;
        if (a <= 0 && 0 <= b) {
            System.out.println("Zero");
        } else if (a > 0) {
            System.out.println("Positive");
        } else {
            long count = b - a + 1;
            System.out.println(count % 2 == 0 ? "Positive" : "Negative");
        }
    }
}