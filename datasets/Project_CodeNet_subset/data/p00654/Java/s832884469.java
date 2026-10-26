//6.12
import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.util.Arrays;

class Main{
    long even[]=new long[1000];
    long gcd(long a,long b){
	return b==0?a:gcd(b,a%b);
    }
    void solve(int n,long oddmin){
	Arrays.sort(even,0,n);
	long ta=even[0];
	for(int i=0;i<n;i++){
	    ta=gcd(ta,even[i]);
	}
	long tx0=even[0]/ta,tx1=even[1]/ta;
	long g2=oddmin/tx0/tx1;
	long g=(long)(Math.sqrt(g2));

	//find A=A'/g
	long evenans=ta/g;
	//find all odd ans
	System.out.println(evenans);
	for(int i=0;i<n;i++){
	    if (i!=0)System.out.print(" ");
	    System.out.print(even[i]/evenans);
	}
	System.out.print("\n");
    }
    void run(){
	Scanner in = new Scanner(System.in);
	//System.setOut(new PrintStream(new BufferedOutputStream(System.out)));
	int n;
	while(true){
	    n=in.nextInt();
	    if(n == 0)break;
	    int m=n*(n+1)/2;
	    int evenp=0;
	    long oddmin=9223372036854775807L;
	    for(int i=0;i<m;i++){
		long tmp;
		tmp=in.nextLong();
		if (tmp%2 == 0)even[evenp++]=tmp;
		else oddmin=(oddmin>tmp)?tmp:oddmin;
	    }
	    solve(n,oddmin);
	}
    }

    public static void main(String args[]){
	Main a = new Main();
	a.run();
    }
}