import java.util.*;

import java.lang.*;

import java.io.*;


public class HelloWorld{


     public static void main(String []args){

         
         Scanner sc=new Scanner(System.in);

       		 int nb,gl;
     
 nb=sc.nextInt();

         gl=sc.nextInt();
    
     int i=0,j=0;
     
    int b[]=new int[nb+1];
 
        int temp[]=new int[nb+1];
         
         
for(i=1;i<=nb;i++){
             
b[i]=sc.nextInt();
    
     }
        
   
     for(i=1;i<=nb;i++){
     
       if(gl%b[i]==0){
      
          j++;
        
    temp[j]=gl/b[i];
      
      }
      
  }
        
      
  Arrays.sort(temp);
     
   for(i=0;i<nb+2;i++){
    
        if(temp[i]!=0){
     
   System.out.println(temp[i]);
  
      return;
        
    } 
    
    }
      
  
        
     }

}