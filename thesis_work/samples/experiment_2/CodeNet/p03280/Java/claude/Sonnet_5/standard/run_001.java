import java.io.*;
import java.util.*;
public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); long a = (long) st.nval;
        st.nextToken(); long b = (long) st.nval;
        System.out.println((a-1)*(b-1));
    }
}