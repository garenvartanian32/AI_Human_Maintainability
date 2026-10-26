import java.util.Scanner;
public class Main{
	public static void main(String args[]) {
		AtCoder1();
	}

	public static void AtCoder1(){
		 Scanner scanner = new Scanner(System.in);
		int shopNum = Integer.parseInt(scanner.next());
		int isOpen[][] = new int[shopNum][10];
		boolean myOpen[] = new boolean[10];
		int matchNum[] = new int[shopNum];
		int profit[][] = new int[shopNum][11];
		int max=0;
		for(int i=0;i<shopNum*10;i++){
			isOpen[i/10][i%10] = Integer.parseInt(scanner.next());
		}
		System.out.println();
		for(int i=0;i<shopNum*11;i++){
			profit[i/11][i%11] = Integer.parseInt(scanner.next());
		}
		for(int i=1;i<1024;i++){
			int sum=0;
			for(int j=0;j<shopNum;j++){
				matchNum[j]=0;
			}
		        int by = i;
			for(int j=9;j>=0;j--){
				if(by>=((int)(Math.pow(2, j)))){
					by = by - (int)(Math.pow(2, j));
					myOpen[j] = true;
				}
				else{
					myOpen[j] = false;
				}
			}
			for(int j=0;j<shopNum;j++){
				for(int k=0;k<10;k++){
					if(myOpen[k])
						matchNum[j] += isOpen[j][k];
				}
			}
			for(int j=0;j<shopNum;j++){
				sum += profit[j][matchNum[j]];
			}
			if(i==1){
				max = sum;
			}
			if(max<sum){
				max = sum;
			}
		}
		System.out.println(max);

	}
}