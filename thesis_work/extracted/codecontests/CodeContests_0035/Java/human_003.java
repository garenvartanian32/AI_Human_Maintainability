import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int n = obj.nextInt();
        int k = obj.nextInt();
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int x = obj.nextInt();
            if(k%x==0){
                ans = Math.min(ans,k/x);
            }
        }
        System.out.println(ans);
    }
}
