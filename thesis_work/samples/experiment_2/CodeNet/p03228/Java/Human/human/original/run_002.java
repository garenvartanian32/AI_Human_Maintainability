import java.util.*;
public class Main {
    public static void main(String[] args) {
  Scanner sc=new Scanner(System.in);
long A=sc.nextInt();
long B=sc.nextInt();
int K=sc.nextInt();
for(int i=0;i<K;i++){
    if(i%2==0){
        B=B+(A-(A%2))/2;
        A=(A-(A%2))/2;
       }
    if(i%2==1){
         A=A+(B-(B%2))/2;
        B=(B-(B%2))/2;
       }
     }
System.out.println(A+" "+B);
    }
}