import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;


public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.programmingContestCalculation();
		return;
	}
	
	//プログラミングコンテストの結果を計算し、表示する
	private void programmingContestCalculation() {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); //標準入力
		
		LinkedList<Integer> testScoreWList = new LinkedList<Integer>();
		LinkedList<Integer> testScoreKList = new LinkedList<Integer>();
		
		try {
			
			//W大学の参加者の得点
			for (int i = 0; i < 10; i++) {
				String inputStr = bufferedReader.readLine();
				testScoreWList.add(Integer.parseInt(inputStr));
			}
			
			//K大学の参加者の得点
			for (int i = 0; i < 10; i++) {
				String inputStr = bufferedReader.readLine();
				testScoreKList.add(Integer.parseInt(inputStr));
			}
			
			//それぞれの大学の上位三名の得点を取得する
			LinkedList<Integer> topThreeScoreWList = this.extractionListOfTheTopThree(testScoreWList);
			LinkedList<Integer> topThreeScoreKList = this.extractionListOfTheTopThree(testScoreKList);
			
			int topThreeScoreW = 0;
			int topThreeScoreK = 0;
			
			for (int i = 0; i < 3; i++) {
				
				topThreeScoreW += topThreeScoreWList.get(i);
				topThreeScoreK += topThreeScoreKList.get(i);
				
			}
			
			System.out.printf("%d %d\n", topThreeScoreW, topThreeScoreK);
			
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	//上位三名の得点をリストから抽出し、戻り値として返す
	private LinkedList<Integer> extractionListOfTheTopThree(LinkedList<Integer> beingProcessedList) {
		
		LinkedList<Integer> topThreeScoreList = new LinkedList<Integer>();
		
		Collections.sort(beingProcessedList);
		Collections.reverse(beingProcessedList);
		
		for (int i = 0; i < 3; i++) {
			topThreeScoreList.add(beingProcessedList.get(i));
		}
		
		return topThreeScoreList;
	}
}