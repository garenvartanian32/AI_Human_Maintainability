import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;

public class rough{

    public static void main(String args[]) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();
        char[] c = s.toCharArray();
        int[] hash = new int[26];

        for (int i=0 ; i<c.length ; i++){
            hash[c[i]-'a']++;
        }

        String r = "";

        for (int i=0 ; i<26 ; i++){
            if (hash[i] != 0 && hash[i]%k == 0){
                for (int j=0 ; j<hash[i]/k ; j++){
                    r += (char)(i+'a')+"";
                }
            }else if (hash[i]%k != 0){
                System.out.println(-1);
                return;
            }
        }
        String res = "";

        for (int i=0 ; i<k ; i++){
            res += r;
        }

        System.out.println(res);
    }

}