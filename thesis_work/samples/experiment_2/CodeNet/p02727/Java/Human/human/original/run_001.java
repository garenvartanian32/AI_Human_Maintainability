import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();
		int Y = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int[] p = new int[A];
		int[] q = new int[B];
		int[] r = new int[C];
		for(int i=0; i<A; i++)
			p[i] = sc.nextInt();
		for(int i=0; i<B; i++)
			q[i] = sc.nextInt();
		for(int i=0; i<C; i++)
			r[i] = sc.nextInt();
		
		Arrays.sort(p);
		Arrays.sort(q);
		Arrays.sort(r);
		
		ArrayList<Integer> cand = new ArrayList<>();
		for(int i=0; i<X; i++)
			cand.add(p[A-1-i]);
		for(int i=0; i<Y; i++)
			cand.add(q[B-1-i]);
		for(int i=0; i<C; i++)
			cand.add(r[i]);
		Collections.sort(cand);
		
		long ans = 0;
		for(int i=0; i<X+Y; i++)
			ans += cand.get(X+Y+C-1-i);
		
		System.out.println(ans);
		sc.close();
	}
}
