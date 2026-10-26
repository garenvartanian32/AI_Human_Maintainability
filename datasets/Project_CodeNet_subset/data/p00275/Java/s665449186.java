import java.util.Scanner;
class Main
{
    public static void main(String args[])
    {
	Scanner scan=new Scanner(System.in);
	while(true)
	    {
		int n=scan.nextInt();
		if(n==0)break;
		int s[]=new int[n];
		char x[]=new char[100];
		int f=0;
		String str=scan.next();
		for(int i=0;i<100;i++)
		    {
			x[i]=str.charAt(i);
			if(x[i]=='S')
			    {
				f+=s[i%n]+1;
				s[i%n]=0;
			    }
			else if(x[i]=='M')s[i%n]++;
			else 
			    {
				s[i%n]+=f+1;
				f=0;
			    }
		    }
		for(int i=0;i<n;i++)
		    {
			for(int j=n-1;j>i;j--)
			    {
				if(s[j]<s[j-1])
				    {
				int t=s[j];
				s[j]=s[j-1];
				s[j-1]=t;
				    }
			    }
		    }
		for(int i=0;i<n;i++)
		    {
			System.out.print((i!=n)?s[i]+" ":"");
		    }
		System.out.print(f);
		System.out.println();
	    }
    }
}