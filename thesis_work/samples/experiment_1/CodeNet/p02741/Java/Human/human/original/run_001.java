import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
    	Scanner file = new Scanner(System.in);
      	int k = file.nextInt();
      	file.close();
      	int[] a = {1, 1, 1, 2, 1, 2, 1, 5, 2, 2, 1, 5, 1, 2, 1, 14, 1, 5, 1, 5, 2, 2, 1, 15, 2, 2, 5, 4, 1, 4, 1, 51};
      	System.out.println(a[k-1]);
      //	return 0;
    }
}
