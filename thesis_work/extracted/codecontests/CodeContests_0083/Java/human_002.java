import java.util.Scanner;

public class Main {

    public static double max(double a1,double a2,double a3,double a4,double a5,double a6,double a7,double a8,double a9){
        if(a1 >= a2 && a1 >= a3 && a1 >= a4 && a1 >= a5 && a1>= a6 && a1>= a7 && a1>=a8 && a1 >=a9 ) return a1;
        else if(a2 >= a1 && a2 >= a3 && a2 >= a4 && a2 >= a5 && a2>= a6 && a2>= a7 && a2>=a8 && a2 >=a9 ) return a2;
        else if(a3 >= a1 && a3 >= a2 && a3 >= a4 && a3 >= a5 && a3>= a6 && a3>= a7 && a3>=a8 && a3 >=a9 ) return a3;
        else if(a4 >= a1 && a4 >= a2 && a4 >= a3 && a4 >= a5 && a4>= a6 && a4>= a7 && a4>=a8 && a4 >=a9 ) return a4;
        else if(a5 >= a1 && a5 >= a2 && a5 >= a3 && a5 >= a4 && a5>= a6 && a5>= a7 && a5>=a8 && a5 >=a9 ) return a5;
        else if(a6 >= a1 && a6 >= a2 && a6 >= a3 && a6 >= a4 && a6>= a5 && a6>= a7 && a6>=a8 && a6 >=a9 ) return a6;
        else if(a7 >= a1 && a7 >= a2 && a7 >= a3 && a7 >= a4 && a7>= a5 && a7>= a6 && a7>=a8 && a7 >=a9 ) return a7;
        else if(a8 >= a1 && a8 >= a2 && a8 >= a3 && a8 >= a4 && a8>= a5 && a8>=a6  && a8>= a7 && a8 >=a9 ) return a8;
        else return a9;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        double y = sc.nextDouble();
        double z = sc.nextDouble();
        double a1,a2,a3,a4,a5,a6,a7,a8,a9;
        if(x<1 || y<1 || z<1){
            a1 = Math.pow(x, Math.pow(y, z));
            a2 = Math.pow(x, Math.pow(z, y));
            a3 = Math.pow(x, y*z);
            a4 = Math.pow(y, Math.pow(x, z));
            a5 = Math.pow(y, Math.pow(z, x));
            a6 = Math.pow(y, z*x);
            a7 = Math.pow(z, Math.pow(x, y));
            a8 = Math.pow(z, Math.pow(y, x));
            a9 = Math.pow(z, x*y);
        }else{
            a1 = z*Math.log(y)+ Math.log(Math.log(x));
            a2 = y*Math.log(z)+Math.log(Math.log(x));
            a3 = Math.log(y*z*Math.log(x));
            a4 = z*Math.log(x)+ Math.log(Math.log(y));
            a5 = x*Math.log(z)+ Math.log(Math.log(y));
            a6 = Math.log(z*x*Math.log(y));
            a7 = y*Math.log(x)+ Math.log(Math.log(z));
            a8 = x*Math.log(y)+Math.log(Math.log(z));
            a9 = Math.log(x*y*Math.log(z));
        }

        if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a1)System.out.println("x^y^z");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a2)System.out.println("x^z^y");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a3)System.out.println("(x^y)^z");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a4)System.out.println("y^x^z");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a5)System.out.println("y^z^x");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a6)System.out.println("(y^x)^z");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a7)System.out.println("z^x^y");
        else if(max(a1,a2,a3,a4,a5,a6,a7,a8,a9) == a8)System.out.println("z^y^x");
        else System.out.println("(z^x)^y");
    }


}