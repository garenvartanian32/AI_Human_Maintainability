import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextLong()-1;
		long gSize=26;
		int length=1;
		while(true) {
			if(N<gSize) {
				String ans = "";
				for(int i=0; i<length; i++) {
					ans = String.valueOf((char)('a'+(N%26)))+ans;
					N/=26;
				}
				System.out.println(ans);
				break;
			} else {
				N-=gSize;
				gSize*=26;
				length++;
			}
		}
		sc.close();
	}
}
