import java.util.Scanner;  
import java.util.*; 
import java.util.Arrays;
 
public class Solution
{ 
    public static void main(String[] args) 
    { 
        Scanner sc = new Scanner(System.in); 
        String ch=sc.next();
        char[] str = ch.toCharArray(); 
        
        int len=str.length;
        for(int i=0;i<len;i=i+2)
        {
            for(int j=0;j<len-i-2;j=j+2)
            {
                if(str[j]>str[j+2])
                {
                    char temp=str[j];
                    str[j]=str[j+2];
                    str[j+2]=temp;
                }
            }
        }
        System.out.println(str);     

    }
}