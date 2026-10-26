import java.util.Arrays;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {
		Scanner file = new Scanner(System.in);
		int inputs = file.nextInt();
		while(inputs-->0) {
			int n = file.nextInt();
			long l = file.nextLong();
			long r = file.nextLong();
			long[] changes = new long[n+1];
			changes[1] = 2*(n-1);
			for(int i = 2; i <= n; i++) {
				changes[i] = changes[i-1] + Math.max(2*(n-i), 1);
			}
//			System.out.println(Arrays.toString(changes));
			
			int level = 1;
			while(l > changes[level])
				level++;
			long curr = (l-changes[level-1]+1)/2+level;
			StringBuilder ans = new StringBuilder();
			for(long index = l; index <= r; index++) {
				if(index == changes[n])
					ans.append(1);
				else {
					if(index > changes[level]) {
						level++;
						curr = level+1;
					}
					if(index % 2 == 0) {
						ans.append(curr + " ");
						curr++;
					}
					else {
						ans.append(level + " ");
					}
				}
			}
			System.out.println(ans);
		}
		file.close();
	}

}
