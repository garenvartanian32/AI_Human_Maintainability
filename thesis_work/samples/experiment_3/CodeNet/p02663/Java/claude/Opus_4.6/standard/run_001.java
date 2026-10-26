import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int h1 = (int) st.nval;
        st.nextToken(); int m1 = (int) st.nval;
        st.nextToken(); int h2 = (int) st.nval;
        st.nextToken(); int m2 = (int) st.nval;
        st.nextToken(); int k = (int) st.nval;
        int awake = (h2*60+m2) - (h1*60+m1);
        System.out.println(Math.max(0, awake - k));
    }
}