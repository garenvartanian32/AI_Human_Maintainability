
import java.util.Scanner;


public class JavaApplication79 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int x = sc.nextInt();
        
        String n = sc.next();
        int count8 = 0;
        
        if(!n.contains("8") || n.length() < 11){
            System.out.println(0);
            return;
        }
        int div=n.length() / 11;

        for(int i=n.indexOf('8'); i<n.length(); i++){
            if(n.charAt(i) == '8'){
                count8++;
            }
        }
        
        System.out.println(Math.min(count8, div));
        
        
        
        

        
    }
    
}
