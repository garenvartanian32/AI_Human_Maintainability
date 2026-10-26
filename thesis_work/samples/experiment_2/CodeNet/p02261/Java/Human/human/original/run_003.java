import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int n = Integer.parseInt(scan.nextLine());
	String[] input = scan.nextLine().split(" ");
	Card[] cards = new Card[n];
	Card[] cardsForBubble = new Card[n];
	Card[] cardsForSelect = new Card[n];
	for (int i = 0; i < n; i++) {
	    String suit = input[i].substring(0, 1);
	    int value = Integer.parseInt(input[i].substring(1, 2));
	    cards[i] = new Card(suit, value);
	    cardsForBubble[i] = new Card(suit, value);
	    cardsForSelect[i] = new Card(suit, value);
	}
	Sort sort = new Sort();
	sort.BubbleSort(cardsForBubble, n);
	sort.printCards(cardsForBubble);
	if(sort.isStable(cards, cardsForBubble)) {
	    System.out.println("Stable");
	} else {
	    System.out.println("Not stable");
	}
	sort.SelectionSort(cardsForSelect, n);
	sort.printCards(cardsForSelect);
	if(sort.isStable(cards, cardsForSelect)) {
	    System.out.println("Stable");
	} else {
	    System.out.println("Not stable");
	}
    }
}
class Sort{
    Card[] BubbleSort(Card[] cards, int n) {
	for (int i = 0; i < n; i++) {
	    for (int j = n-1; j > i; j--) {
		if (cards[j].value < cards[j-1].value) {
		    Card tmp = cards[j];
		    cards[j] = cards[j-1];
		    cards[j-1] = tmp;
		}
	    }
	}
	return cards;
    }
    Card[] SelectionSort(Card[] cards, int n) {
	for (int i = 0; i < n; i++) {
	    int minKey = i; 
	    for (int j = i; j < n; j++) {
		if (cards[j].value < cards[minKey].value) {
		    minKey = j;
		}
	    }
	    if (minKey != i) {
		Card tmp = cards[i];
		cards[i] = cards[minKey];
		cards[minKey] = tmp;
	    }
	}
	return cards;
    }
    boolean isStable(Card[] cards, Card[] sortedCards) {
	for (int i = 0; i < sortedCards.length - 1; i++) {
	    if (sortedCards[i].value == sortedCards[i+1].value) {
		int value = sortedCards[i].value;
		String front = sortedCards[i].suit;
		String back = sortedCards[i+1].suit;
		int frontIndex = 0;
		int backIndex = 0;
		for (int j = 0; j < cards.length; j++) {
		    if (cards[j].value == value && cards[j].suit.equals(front)) {
			frontIndex = j;
		    } else if (cards[j].value == value && cards[j].suit.equals(back)) {
			backIndex = j;
		    }
		}
		if (frontIndex > backIndex) {
		    return false;
		}
	    }
	}
	return true;
    }
    void printCards (Card[] cards) {
	for (int i = 0; i < cards.length - 1; i++) {
	    System.out.print(cards[i].suit + cards[i].value + " ");
	}
	System.out.println(cards[cards.length - 1].suit + cards[cards.length - 1].value);
    }
}
class Card{
    String suit;
    int value;
    Card (String suit, int value) {
	this.suit = suit;
	this.value = value;
    }
}