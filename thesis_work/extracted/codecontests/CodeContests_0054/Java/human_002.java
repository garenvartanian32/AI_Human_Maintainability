import java.util.Scanner;

public class Main{
    Main(){
    Scanner sc = new Scanner(System.in);
    int n, t, s, cnt;
    int[] a;
 
    while(sc.hasNext()){
        n = sc.nextInt();
 
        a = new int[n];
        for(int i = 0; i < n; ++i)
        a[i] = sc.nextInt();
 
        cnt = 0;
        for(int i = 0; i < n; ++i){
        t = i;
        for(int j = i; j < n; ++j)
            if(a[j] < a[t]) t = j;
 
        if(a[t] != a[i]){
            s = a[i];
            a[i] = a[t];
            a[t] = s;
            ++cnt;
        }
        }
 
        for(int i = 0; i < n-1; ++i)
        System.out.printf("%d ", a[i]);
        System.out.println(a[n-1]);
        System.out.println(cnt);
    }
    }
 
    public static void main(String[] args){
    new Main();
    }
}
