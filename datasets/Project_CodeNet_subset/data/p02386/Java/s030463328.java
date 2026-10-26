import java.io.*;
 
class Main {
    public static void main(String[] args)throws IOException{ 
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader(in);
        dice_compe finished = new dice_compe();
        
        int diceNum = Integer.parseInt(read.readLine());
        int [][] dice = new int [diceNum][9];
        for(int count = 0; count < diceNum; count++){
        	for(int counts = 0; counts < 9; counts++){
        		dice[count][counts] = 0;
        	}
        }
        
        int countt,countts;
        
        for(countt = 0; countt < diceNum; countt++){
        	String number = read.readLine();
    		String[] spli = number.split(" ");
        	for(countts = 0; countts < 6; countts++){       		
        		dice[countt][countts] = Integer.parseInt(spli[countts]);           //??¨???????????????
        	}
        }
        
        int [] dice3 = new int [9];
        int [] dice4 = new int [9];
        for(int insert = 0; insert < 9; insert++){
    		dice3[insert] = 0;
    		dice4[insert] = 0;
    	}
        
        for(int N = 0; N < diceNum-1; N++){
        	for(int M = N+1; M < diceNum; M++){
	        	for(int y = 0; y < 6; y++){
        		dice3 [y] = dice[N][y];         		
        		dice4 [y] = dice[M][y];
	        	}
        	if(finished.compare(dice3,dice4) == false){
	        	break;				      
        	}
        	}
    	if(finished.compare(dice3,dice4) == false){
        	break;				      
    	}
        }      	
        if(finished.compare(dice3,dice4) == false){
        	System.out.println("No");
        }
        else{
        	System.out.println("Yes");
        }        
    }
}

class dice_compe{
	public static boolean compare(int dice1[],int dice2[]){
		int spinA,spinB,spinC; 

		for(spinA = 0; spinA < 4; spinA++){
	        		if(dice1[0] == dice2[0] && dice1[1] == dice2[1] && dice1[2] == dice2[2] && dice1[3] == dice2[3] && dice1[4] == dice2[4] && dice1[5] == dice2[5]){
	        			break;
        			}
        			else{
        				dice1[6] = dice1[0];
        				dice1[0] = dice1[1];
        				dice1[1] = dice1[5];
        				dice1[5] = dice1[4];
        				dice1[4] = dice1[6];
        				
		        		for(spinB = 0; spinB < 4; spinB++){
		        			if(dice1[0] == dice2[0] && dice1[1] == dice2[1] && dice1[2] == dice2[2] && dice1[3] == dice2[3] && dice1[4] == dice2[4] && dice1[5] == dice2[5]){
		        				break;
		        			}
		        			else{
		        				dice1[6] = dice1[1];
		        				dice1[7] = dice1[2];
		        				dice1[8] = dice1[4];
		        				dice1[1] = dice1[3];
		        				dice1[2] = dice1[6];
		        				dice1[4] = dice1[7];
		        				dice1[3] = dice1[8];
		        				
			        			for(spinC = 0; spinC < 4; spinC++){
				        			if(dice1[0] == dice2[0] && dice1[1] == dice2[1] && dice1[2] == dice2[2] && dice1[3] == dice2[3] && dice1[4] == dice2[4] && dice1[5] == dice2[5]){
				        				break;
				        			}
				        			else{
				        				dice1[6] = dice1[2];
				        				dice1[7] = dice1[0];
				        				dice1[8] = dice1[3];
				        				dice1[2] = dice1[5];
				        				dice1[0] = dice1[6];
				        				dice1[3] = dice1[7];
				        				dice1[5] = dice1[8];
				        			}
			        			}
			        		}
		        		}
        			}
	        	}
	        	if(dice1[0] == dice2[0] && dice1[1] == dice2[1] && dice1[2] == dice2[2] && dice1[3] == dice2[3] && dice1[4] == dice2[4] && dice1[5] == dice2[5]){
	        		return false;
	        	}
	        	else{
	        		return true;
	        	}
	}
}