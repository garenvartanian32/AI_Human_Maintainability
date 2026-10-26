    import java.util.*;
    import java.lang.*;
     
    public class  Main{
     
        public static void main(String args[]){
        
        Scanner sc= new Scanner(System.in);
    int j,i ,a=0,b=0,f2=0,f1=0;
int t;
    int c = 0;
  
int n=sc.nextInt();

for(i=0;i<n;i++){
t=sc.nextInt();
a += t;}

for(i=0;i<n-1;i++){
t=sc.nextInt();
b += t;}

for(i=0;i<n-2;i++){
t=sc.nextInt();
c+= t;}

System.out.println(a-b);
System.out.println(b-c);
    }
}