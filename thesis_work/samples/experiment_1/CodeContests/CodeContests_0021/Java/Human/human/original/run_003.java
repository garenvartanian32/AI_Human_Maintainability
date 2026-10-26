import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

     Scanner sc =new Scanner (System.in);
   
    int a =0;
    a=sc.nextInt();
    
    int b =0;
    b=sc.nextInt();
    
    int resta = 0;
    int contar  = 0;
    
    while(a > 0){
    contar += a;
    resta += a%b;
    a = a/b;
    if(resta >= b){
    a+=1;
    resta -= b;
    
}
    
}
    System.out.println(contar);
    
	}
}
    
    