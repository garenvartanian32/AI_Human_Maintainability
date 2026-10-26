/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gameofrobots;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author PrudhviNIT
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static long f2(long k){
        //int ans = 0;
        long ans = (long)Math.floor((Math.sqrt(8*k+1d)-1d)/2d);
        //IO.d("Sqrt is "+sqrt);
        long prod = ans*(ans+1)*1l;
        long rprod = 2l*k;
        if(prod==rprod){
            ans--;
        }
        //IO.d("Sqrt is "+sqrt);
        return ans;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        long arr[] = IO.rla();
        long n = arr[0],k = arr[1];
        long id[] = IO.rla();
        long fold = f2(k);
        long sum = (1l*fold*(fold+1))/2;
        //IO.d("sum is "+sum);
        long rem = k-sum-1;
        //IO.d("Ind is "+rem);
        IO.d(id[(int)rem]);
    }
    static class IO{
        private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        static String rs(){
            try {
                return br.readLine().trim();
            } catch (java.io.IOException ex) {
                //Lets hope things won't get this far!!
            }
            return null;
        }
        static String[] rsa(){
            try {
                return br.readLine().trim().split(" ");
            } catch (java.io.IOException ex) {
                //Relax, Nothing need to be done!!
            }
            return null;
        }
        static int[] ria(){
            int arr[] = null;
            try{
            String[] str = br.readLine().trim().split(" ");
            arr = new int[str.length];
            int n = str.length;
            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(str[i]);
            }
            }
            catch(java.io.IOException e){
                
            }
            return arr;
        }
        static int[] ria1(){
            int arr[] = null;
            try{
            String[] str = br.readLine().trim().split(" ");
            arr = new int[str.length+1];
            int n = str.length;
            for(int i=0;i<n;i++){
                arr[i+1] = Integer.parseInt(str[i]);
            }
            }
            catch(java.io.IOException e){
                
            }
            return arr;
        }
        static long[] rla(){
            long arr[] = null;
            try{
            String[] str = br.readLine().trim().split(" ");
            arr = new long[str.length];
            int n = str.length;
            for(int i=0;i<n;i++){
                arr[i] = Long.parseLong(str[i]);
            }
            }
            catch(java.io.IOException e){
                
            }
            return arr;
        }
		static long[] rla1(){
            long arr[] = null;
            try{
            String[] str = br.readLine().trim().split(" ");
            arr = new long[str.length+1];
            int n = str.length;
            for(int i=0;i<n;i++){
                arr[i+1] = Long.parseLong(str[i]);
            }
            }
            catch(java.io.IOException e){
                
            }
            return arr;
        }
        static int ri(){
            try {
                return Integer.parseInt(br.readLine().trim());
            } catch (java.io.IOException ex) {
                //Logger.getLogger(Sample1.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
        }
        static long rl(){
            try {
                return Long.parseLong(br.readLine().trim());
            } catch (java.io.IOException ex) {
                //Logger.getLogger(Sample1.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
        }
        static int pi(String s){
            return Integer.parseInt(s.trim());
        }
        static long pl(String s){
            return Long.parseLong(s.trim());
        }
        static double pd(String s){
            return Double.parseDouble(s.trim());
        }
        static float pf(String s){
            return Float.parseFloat(s.trim());
        }
        static void d(String s){
            System.out.println(s);
        }
        static void d(int i){
            System.out.println(i);
        }
        static void d(long l){
            System.out.println(l);
        }
        static void d(float f){
            System.out.println(f);
        }
        static void d(double d){
            System.out.println(d);
        }
        static void d(int arr[]){
            System.out.print("{");
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ,");
            }
            System.out.println("\b}");
        }
        static void d(long arr[]){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
        static void d(){
            System.out.println();
        }
    }
}
