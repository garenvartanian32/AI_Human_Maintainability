import java.util.Scanner;

public class Games {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int h,a,count = 0;
        int[] home = new int[n];
        int[] guest = new int[n];
        
        for(int i = 0; i < n; i ++){
            h = in.nextInt();
            a = in.nextInt();
            if(i == 0){
                home[i] = h;
                guest[i] = a;
            }
            else{
                for(int gc: guest){
                    if(h == gc){
                        count ++;
                    }
                }
                for(int hc: home){
                    if(a == hc){
                        count ++;
                    }
                }
                home[i] = h;
                guest[i] = a;
            }
            in.nextLine();
        }
        System.out.println(count);
    }
}