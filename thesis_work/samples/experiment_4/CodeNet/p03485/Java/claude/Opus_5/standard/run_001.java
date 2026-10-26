import java.io.*;
public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int a = (int) st.nval;
        st.nextToken(); int b = (int) st.nval;
        System.out.println((a + b + 1) / 2);
    }
}