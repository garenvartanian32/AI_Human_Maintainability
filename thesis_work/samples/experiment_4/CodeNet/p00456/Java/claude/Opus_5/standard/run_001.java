import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        int[] w = new int[10], k = new int[10];
        for (int i = 0; i < 10; i++) { st.nextToken(); w[i] = (int) st.nval; }
        for (int i = 0; i < 10; i++) { st.nextToken(); k[i] = (int) st.nval; }
        Integer[] wBox = new Integer[10];
        Integer[] kBox = new Integer[10];
        for (int i = 0; i < 10; i++) { wBox[i] = w[i]; kBox[i] = k[i]; }
        Arrays.sort(wBox, Collections.reverseOrder());
        Arrays.sort(kBox, Collections.reverseOrder());
        int wScore = wBox[0] + wBox[1] + wBox[2];
        int kScore = kBox[0] + kBox[1] + kBox[2];
        System.out.println(wScore + " " + kScore);
    }
}