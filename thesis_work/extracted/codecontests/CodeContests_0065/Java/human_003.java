import java.util.ArrayList;
import java.util.Scanner;


public class C107 {
	public static boolean isPrime(int p) {
		boolean r = false;
		for(int i = 2;!r && i <= Math.ceil(Math.sqrt(p)); i++)
			r = p%i == 0;
		return r;
	}
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		long n = Long.parseLong(s.next());
		ArrayList<Integer> f = new ArrayList<Integer>();
		boolean found = true;
		while(n > 1 && found) {
			found = false;
			for(int i = 2; i <= Math.ceil(Math.sqrt(n)); i++)
				if(n%i == 0) {
					while(n%i == 0) {
						n /= i;
						f.add(i); 
					}
					found = true; break;
				}
		}
		if(!found) f.add((int) n);
		
		for(int i = 2; i <= Math.ceil(Math.sqrt(n)); i++)
			if(n%i == 0) f.add(i);
		if(f.isEmpty() || f.size() == 1) {
			System.out.println(1 + "\n" + 0); return;
		}
		if(f.size() == 2) {
			System.out.println(2); return;
		}
		else {
			System.out.println(1);
			System.out.println(f.get(0)*f.get(1));
		}
		
	}
	
	

}
