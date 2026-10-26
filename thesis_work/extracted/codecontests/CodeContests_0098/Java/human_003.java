import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] in = br.readLine().split(" ");
    long n = Long.parseLong(in[0]);
    long p = Long.parseLong(in[1]);

    br.close();

    Map<Long, Integer> prime = new HashMap<>();

    for(long i = 2; i * i <= p; i++){
      int cnt = 0;
      while(p % i == 0){
        p /= i;
        cnt++;
      }
      if(cnt > 0){
        prime.put(i, cnt);
      }
    }

    if(p > 1) prime.put(p, 1);

    long ans = 1;

    for(Map.Entry<Long, Integer> e : prime.entrySet()){
      int t = e.getValue();
      while(t >= n){
        ans *= e.getKey();
        t -= n;
      }
    }

    System.out.println(ans);
  }
}