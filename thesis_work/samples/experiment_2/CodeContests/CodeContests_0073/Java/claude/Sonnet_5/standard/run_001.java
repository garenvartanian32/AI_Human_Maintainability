import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        String[] parts = s.split("\\+");
        Integer[] nums = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) nums[i] = Integer.parseInt(parts[i]);
        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) sb.append('+');
            sb.append(nums[i]);
        }
        System.out.println(sb);
    }
}