import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        String s = "";
        int l = 0;
        int x = 0;
        int ans = 0;

        for(int i=A; i<=B; i++){
            s = String.valueOf(i);
            l = s.length();
            if(l%2==0){
                for(int j=0; j<l/2; j++){
                    if(s.charAt(j)==s.charAt(l-1-j)){
                        x += 1;
                    }
                    else{
                        break;
                    }
                    if(x==l/2){
                        ans += 1;
                    }
                }
                x = 0;
            }
            else{
                for(int j=0; j<l/2; j++){
                    if(s.charAt(j)==s.charAt(l-1-j)){
                        x += 1;
                    }
                    else{
                        break;
                    }
                    if(x==(int)l/2){
                        ans += 1;
                    }
                }
                x = 0;
            }
        }
        System.out.println(ans);
    } 
}