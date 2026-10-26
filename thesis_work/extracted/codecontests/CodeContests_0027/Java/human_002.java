import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] bulbs = new boolean[m];
        for(int i =0; i<n; i++){
            int k = in.nextInt();
            while(k>0){
                int num = in.nextInt();
                bulbs[num-1]=true;
                k--;
            }
        }
        boolean ok =true;
        for(int i =0; i<m; i++){
            if(!bulbs[i]){
                ok=false;
                break;
            }
        }
        System.out.println(ok?"YES":"NO");
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}