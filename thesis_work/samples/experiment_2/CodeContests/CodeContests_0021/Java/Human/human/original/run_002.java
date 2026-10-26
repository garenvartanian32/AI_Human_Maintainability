import java.util.Scanner;
public class NewYearCandles{
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int scand = scn.nextInt();
        int ucand = scn.nextInt();
        int ihrs = scand;
        int rem = scand;
        while (rem >= ucand ){
            ihrs+= scand/ucand ;
            rem=scand/ucand+ scand%ucand;
            scand=rem ;
        }
        System.out.println(ihrs);
    }
}