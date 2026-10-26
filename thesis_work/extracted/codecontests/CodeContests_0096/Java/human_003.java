import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int n = scan.nextInt();
		int d = s.length();

		int dp[][][] = new int[d+1][2][5];
		dp[0][0][0] = 1;

		for(int i = 0; i < d; i++){
			int k = s.charAt(i)-'0';
			for(int small = 0; small < 2; small++){
				for(int not0 = 0; not0 < 5; not0++){
					for(int j = 0; j <= ((small == 1)?9:k); j++){
						if(small == 1 || (j < k)){
							if(not0 == 4 || j == 0){
								dp[i + 1][1][not0] += dp[i][small][not0]; 
							}
							else{
								dp[i + 1][1][not0+1] += dp[i][small][not0]; 
							}
						}
						else{
							if(not0 == 4 || j == 0){
								dp[i + 1][0][not0] += dp[i][small][not0]; 
							}
							else{
								dp[i + 1][0][not0+1] += dp[i][small][not0]; 
							}
						}
					}
				}
			}
		}
		System.out.println(dp[d][1][n] + dp[d][0][n]);
	}
}