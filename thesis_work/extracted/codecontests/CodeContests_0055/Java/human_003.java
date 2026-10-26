import java.io.*;
import java.util.*;
class Main{
  public static void main(String[] args)throws IOException{
    BufferedReader mBufferedReader=new BufferedReader(new InputStreamReader(System.in));
    List<String>list=new ArrayList<String>();
    String[] marks={"S","H","C","D"};
    for(int i=0;i<4;i++){
      for(int j=0;j<13;j++){
        list.add(marks[i]+" "+(j+1));
      }
    }
    int n=Integer.parseInt(mBufferedReader.readLine());
    for(int i=0;i<n;i++){
      list.remove(mBufferedReader.readLine());
    }
    for(String s:list){
      System.out.println(s);
    }
  }
}   
