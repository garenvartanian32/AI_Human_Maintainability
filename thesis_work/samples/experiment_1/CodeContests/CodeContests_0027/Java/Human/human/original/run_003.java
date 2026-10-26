import java.util.*;

public class Bulbs {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		
		for(int i =0 ;i<n;i++) {
			
			int x = sc.nextInt();
			
			for(int j =0; j<x; j++) 
				
				a.add(sc.nextInt());
			
			for(int j=1;j<=m;j++) {
				
				if(a.contains(j)==true&& b.contains(j)==false)
					b.add(j);
				
			}
			
		}
		
		if(b.size()==m)
			System.out.println("YES");
		else
			System.out.println("NO");
 
	}

}
