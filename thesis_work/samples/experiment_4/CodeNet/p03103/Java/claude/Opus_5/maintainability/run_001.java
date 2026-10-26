import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * Cheapest way to buy a fixed number of drinks across several shops.
 *
 * <p>Drinks are interchangeable, so exhausting the cheapest shop before moving
 * on is optimal.
 */
public class Sol {

    private static final int PRICE = 0;
    private static final int STOCK = 1;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int shopCount = (int) input.nval;
        input.nextToken();
        long drinksWanted = (long) input.nval;

        long[][] shops = new long[shopCount][2];
        for (int i = 0; i < shopCount; i++) {
            input.nextToken();
            shops[i][PRICE] = (long) input.nval;
            input.nextToken();
            shops[i][STOCK] = (long) input.nval;
        }
        Arrays.sort(shops, (left, right) -> Long.compare(left[PRICE], right[PRICE]));

        long totalCost = 0;
        long stillNeeded = drinksWanted;
        for (long[] shop : shops) {
            if (stillNeeded == 0) {
                break;
            }
            long bought = Math.min(shop[STOCK], stillNeeded);
            totalCost += bought * shop[PRICE];
            stillNeeded -= bought;
        }

        System.out.println(totalCost);
    }
}