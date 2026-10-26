import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		while(true){
			int n = stdIn.nextInt();
			if(n==0){
				return;
			}
			int[][] d = new int[n][4];
			for(int i=0;i<n;i++){
				for(int j=0;j<4;j++){
					d[i][j] = stdIn.nextInt();
				}
			}
			// sort
			for(int i=0;i<n;i++){
				int max = i;
				for(int j=i+1;j<n;j++){
					if(d[max][2]<d[j][2]){
						max = j;
					}
				}
				for(int j=0;j<4;j++){
					int tmp = d[i][j];
					d[i][j] = d[max][j];
					d[max][j] = tmp;
				}
			}
			for(int i=0;i<n;i++){
				int max = i;
				for(int j=i+1;j<n;j++){
					if(d[max][2]==d[j][2]&&d[max][3]>d[j][3]){
						max = j;
					}
				}
				for(int j=0;j<4;j++){
					int tmp = d[i][j];
					d[i][j] = d[max][j];
					d[max][j] = tmp;
				}
			}
			for(int i=0;i<n;i++){
				int max = i;
				for(int j=i+1;j<n;j++){
					if(d[max][2]==d[j][2]&&d[max][3]==d[j][3]&&d[max][0]>d[j][0]){
						max = j;
					}
				}
				for(int j=0;j<4;j++){
					int tmp = d[i][j];
					d[i][j] = d[max][j];
					d[max][j] = tmp;
				}
			}
			int pass = 0;
			for(int i=0;i<n;i++){
				if(pass<10){
					int my = 0;
					for(int j=0;j<i;j++){
						if(d[i][1]==d[j][1]){
							my++;
						}
					}
					if(my<3){
						pass++;
						System.out.println(d[i][0]);
					}
				}
				else if(pass<20){
					int my = 0;
					for(int j=0;j<i;j++){
						if(d[i][1]==d[j][1]){
							my++;
						}
					}
					if(my<2){
						pass++;
						System.out.println(d[i][0]);
					}
				}
				else if(pass<26){
					int my = 0;
					for(int j=0;j<i;j++){
						if(d[i][1]==d[j][1]){
							my++;
						}
					}
					if(my==0){
						pass++;
						System.out.println(d[i][0]);
					}
				}
			}
		}
	}
}