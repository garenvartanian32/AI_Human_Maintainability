import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		Scanner sc=new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(sc.nextLine());
		while(T-->0){
			
			int k=sc.nextInt();
			int n=sc.nextInt();
			int m=sc.nextInt();
			int a[]=new int[n];
			int b[]=new int[m];
			for(int i=0;i<n;i++)
				a[i]=sc.nextInt();
			for(int i=0;i<m;i++)
				b[i]=sc.nextInt();
			
			int i=0,j=0;
			String ans="";
			boolean x=true;
			while(i!=n && j!=m && x==true){
				//System.out.println(i+" "+j+" "+ans);
				if(a[i]==0){
					ans=ans+"0 ";
					i++;
					k++;
				}
				else if(b[j]==0){
					ans=ans+"0 ";
					j++;
					k++;
				}
				else{
					if(a[i]<b[j]){
						if(k<a[i]){
							x=false;
							break;
						}
						else{
							
							ans=ans+a[i]+" ";
							i++;
							
						}
					}
					else{
						if(k<b[j]){
							x=false;
							break;
						}
						else{
							ans=ans+b[j]+" ";
							j++;
							
						}
						
					}
				}
			}
			if(x==false ){
				System.out.println(-1);
				continue;
			}
			while(i!=n){
				if(a[i]>k){
					x=false;
					break;		
				}
				else
					ans=ans+a[i]+" ";
				if(a[i]==0)
					k++;
				i++;
				
			}
			while(j!=m){
				if(b[j]>k){
					x=false;
					break;		
				}
				else
					ans=ans+b[j]+" ";
				if(b[j]==0)
					k++;
				j++;
				
			}
			if(x && i==n && j==m)
				System.out.println(ans);
			else
				System.out.println(-1);
			
			
		}
		bw.flush();
	}
}