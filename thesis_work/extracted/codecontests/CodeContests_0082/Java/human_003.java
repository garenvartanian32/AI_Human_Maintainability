
import java.io.*;
import java.util.*;

public class Main implements Runnable {

    

    private void solve() throws IOException {
         int l=0;
       TreeSet<String> names = new TreeSet<String>();
            String cad,currN;
           
 while ((cad = reader.readLine())!=null){

 if(cad.contains(":")){
     if(cad.charAt(cad.length()-1)!=':')
        currN=cad.split(":")[1];
     else
         currN="";

l+=currN.length()*names.size();
 }
 else {
 currN=cad.substring(1);
 if(cad.charAt(0)=='+')
     names.add(currN);
 else
     names.remove(currN);
 }
 }
            
   
        writer.println(l);
    }
    public static void main(String[] args) {
        new Main().run();
    }

    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintWriter writer;

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
            writer = new PrintWriter(System.out);
            solve();
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }
}