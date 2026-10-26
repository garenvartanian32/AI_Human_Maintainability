import java.util.*;
import java.lang.*;
import java.io.*;

public class PairProgramming
{
    public static void main (String[] args) throws IOException {
	
	    InputStreamReader ir = new  InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        int test = Integer.parseInt(br.readLine());
        for(int cas=1;cas<=test;cas++)
        {
             br.readLine();
            String[] line1 = br.readLine().split(" ");
            int k = Integer.parseInt(line1[0]);
            int n = Integer.parseInt(line1[1]);
            int m = Integer.parseInt(line1[2]);
           
            int a1[] = new int[n];
            String[] line2 = br.readLine().split(" ");
            for(int i=0;i<n;i++)
            {
              a1[i]= Integer.parseInt(line2[i]);
               }
            
            int a2[] = new int[m];
            String[] line3 = br.readLine().split(" ");
            for(int i=0;i<m;i++)
            {
              a2[i]= Integer.parseInt(line3[i]);
               }
            
            int p1 =0, p2 =0;
            
            ArrayList<Integer> ans = new ArrayList<>(); 
            
            while(p1<a1.length&&p2<a2.length)
            {
            	//System.out.println("P1= "+p1+" P2= "+p2);
            	if(a1[p1]==0)
            	{ans.add(a1[p1++]); k++;}
            	else if(a2[p2]==0)
                {ans.add(a2[p2++]); k++;}
            	else if(a1[p1]<=k)
            	ans.add(a1[p1++]);
            	else if(a2[p2]<=k)
                ans.add(a2[p2++]);
            	else
            	{ ans.clear();	break; }
            }
            if(ans.size()==0)
               System.out.println(-1);
            else {
            while(p1<a1.length)            
            {
            	//System.out.println("P1= "+p1+" P2= "+p2);
            	if(a1[p1]==0)
            	{ans.add(a1[p1++]); k++;}
            	else if(a1[p1]<=k)
                	ans.add(a1[p1++]);
            	else
            	{ ans.clear();	break; }
            }
            while(p2<a2.length)
            {
            	//System.out.println("P1= "+p1+" P2= "+p2);
             if(a2[p2]==0)
                {ans.add(a2[p2++]); k++;}
            	else if(a2[p2]<=k)
                    ans.add(a2[p2++]);
                	else
                	{ ans.clear();	break; }
            }
            if(ans.size()==0)
            	System.out.println(-1);
            else
            {
            	for(int i: ans)
            		System.out.print(i+" ");
            	System.out.println(); 
            }
           }        
        }
    }
}