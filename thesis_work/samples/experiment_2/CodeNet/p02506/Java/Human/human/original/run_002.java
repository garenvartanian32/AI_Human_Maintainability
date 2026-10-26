import java.util.Scanner;

class Main{

    public static void main(String[] args){
	String data,word;
	int count = 0;
	int i = 1;

	Scanner scan = new Scanner(System.in);

	word = scan.next();

	while(i == 1){

	    data = scan.next();
		    
	    if(data.equals("END_OF_TEXT")){
		break;
	    }


	    data = data.replaceAll("\"","");
	    data = data.replaceAll("-","");
	    data = data.replaceAll("\\Q" + "." + "\\E","");
	    data = data.replaceAll("_","");


	    if(word.compareToIgnoreCase(data) == 0){
		count += 1;
	    }
	    
	}
	System.out.println(count);
    }
}