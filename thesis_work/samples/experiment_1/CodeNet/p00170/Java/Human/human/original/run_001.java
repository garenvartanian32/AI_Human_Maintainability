import java.util.Scanner;

//Lunch
public class Main{

	static int[] ans;
	static String[] name;
	static int n;
	static int[] w;
	static int[] s;
	static int[] order;
	static boolean[] used;
	static double best;
	
	static void dfs(int k, int num, int total){
		if(k==0){
			double g = num*1.0/total;
			if(g<best){
				best = g;
				for(int i=1;i<=n;i++)ans[i-1]=order[i];
			}
			return;
		}
		for(int i=0;i<n;i++){
			if(!used[i]&&total<=s[i]){
				used[i] = true;
				order[k] = i;
				dfs(k-1, num+k*w[i], total+w[i]);
				used[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
			n = sc.nextInt();
			if(n==0)break;
			name = new String[n];
			w = new int[n];
			s = new int[n];
			order = new int[n+1];
			best = Integer.MAX_VALUE;
			for(int i=0;i<n;i++){
				name[i] = sc.next();
				w[i] = sc.nextInt();
				s[i] = sc.nextInt();
			}
			used = new boolean[n];
			ans = new int[n];
			dfs(n, 0, 0);
			for(int i=0;i<n;i++){
				System.out.println(name[ans[i]]);
			}
		}
	}
}