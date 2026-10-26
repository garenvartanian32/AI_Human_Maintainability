import java.util.Scanner;
public class Main {
public static void main(String[]srgs) {
	Scanner sc=new Scanner(System.in);
	int a=sc.nextInt();
	int b[]=new int[a];
	for(int i=0;i<a;i++) {
		b[i]=sc.nextInt();
	}
	int count=0;
	for(int i=0;i<a;i++) {
		if(b[b[i]-1]==i+1) {
			count++;
		}
	}
	System.out.println(count/2);
	}
}