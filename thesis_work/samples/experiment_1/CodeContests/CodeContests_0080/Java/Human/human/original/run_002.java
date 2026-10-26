import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		String c = br.readLine();
		int ar[] = new int[26];
		for(char ch: a.toCharArray()){
		    ar[ch - 'a']++;
		}
	    System.out.print(getString(ar, a, b, c));
    }
    public static String getString(int ar[], String a, String b, String c){
        StringBuffer sb = new StringBuffer();
        int bFreq[] = new int[26];
        for(char ch: b.toCharArray())
            bFreq[ch - 'a']++;
        int cFreq[] = new int[26];
        for(char ch: c.toCharArray())
            cFreq[ch - 'a']++;
	    int maxB = (int)1e9;
	    for(int i = 0; i < 26; i++){
	        if(bFreq[i] == 0)
	            continue;
	        maxB = Math.min(maxB, ar[i]/bFreq[i]);
	    }
	    int countB = 0, countC = 0, max = 0;
	    for(int k = 0; k <= maxB; k++){
            int maxC = (int)1e9;
    	    for(int i = 0; i < 26; i++){
    	        if(cFreq[i] == 0)
    	            continue;
    	        maxC = Math.min(maxC, (ar[i] - k*bFreq[i])/cFreq[i]);
	        }
	        if(maxC + k > max){
	            max = maxC + k;
    	        countB = k;
    	        countC = maxC;
	        }
            
	    }
	    for(int i = 0; i < countB; i++)
	        sb.append(b);
	    for(int i = 0; i < countC; i++)
	        sb.append(c);
	    for(int i = 0; i < 26; i++){
	        ar[i] -= bFreq[i]*countB + cFreq[i]*countC; 
	        while(ar[i]-- > 0)
	            sb.append((char)('a' + i));
	    }
	    return sb.toString();
    }
}