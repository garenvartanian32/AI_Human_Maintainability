import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt() , v;
		int  k = in.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();
		for (int  i = 1; i <= n ; i ++){
			v = in.nextInt();
			arr.add(v);
			if(i >= k){
				System.out.println(arr.get(k - 1));
				break;
			}
			k -= i;
		}
	}
}
