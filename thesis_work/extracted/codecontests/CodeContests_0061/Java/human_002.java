import java.util.*;
public class Stones
{

public static void main(String[] args)
{
Scanner sc=new Scanner(System.in);

int t=sc.nextInt();
while(t-->0)
{
int a=sc.nextInt();
int b=sc.nextInt();
int c=sc.nextInt();

int sum=0;
c=c/2;
if(b==0)
{
sum=0;
}else{

if(b<=c)
{
sum=sum+3*b;
b=0;
}else{
sum=sum+3*c;
b=b-c;
}

b=b/2;

if(a<=b)
{
sum=sum+3*a;
}else{
sum=sum+3*b;
}

}
System.out.println(sum);
}

}}