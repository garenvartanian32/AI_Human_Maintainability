import java.util.Scanner;

public class Main{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] l = {5, 7, 5, 7, 7};
        while(true) {
            int n = sc.nextInt();
            if(n == 0) break;
            String[] w = new String[n];
            for(int i = 0; i < n; i++) {
            	w[i] = sc.next();
            }
            int ind = 0;
            int i = 0;
            int ans = 0;
            while(true) {
            	int sum = 0;
            	int j = 0;
            	while(sum < l[ind]) {
            		sum += w[i + j].length();
            		j++;
            	}
            	if(sum == l[ind]) {
            		//System.out.println(ind + " " + i);
            		if(ind == 4) {
            			System.out.println(ans + 1);
            			break;
            		}
            		ind++;
            		i += j;
            		continue;
            	}else {
            		ans++;
            		i = ans;
            		ind = 0;
            	}
            }
        }
    }
}
