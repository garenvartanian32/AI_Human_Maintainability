import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Maximum elegance of a k-item selection (total profit + distinct categories squared).
 */
class Solution {

    private static final int PROFIT = 0;
    private static final int CATEGORY = 1;

    /**
     * Returns the best elegance over all subsets of exactly k items.
     *
     * <p>Start with the k most profitable items, then repeatedly trade the least
     * profitable duplicate-category item for the most profitable item of a
     * brand-new category. Profit can only fall, but the distinct-category term
     * grows quadratically, so the trade is sometimes worth it.
     */
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (left, right) -> Integer.compare(right[PROFIT], left[PROFIT]));

        long totalProfit = 0;
        Set<Integer> seenCategories = new HashSet<>();
        // Profits of chosen items whose category was already represented;
        // a stack so pop() yields the cheapest one to give up.
        Deque<Integer> droppableProfits = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            totalProfit += items[i][PROFIT];
            boolean categoryIsNew = seenCategories.add(items[i][CATEGORY]);
            if (!categoryIsNew) {
                droppableProfits.push(items[i][PROFIT]);
            }
        }

        long bestElegance = totalProfit + square(seenCategories.size());

        for (int i = k; i < items.length; i++) {
            if (droppableProfits.isEmpty()) {
                break;
            }
            if (seenCategories.contains(items[i][CATEGORY])) {
                continue;
            }

            totalProfit += items[i][PROFIT] - droppableProfits.pop();
            seenCategories.add(items[i][CATEGORY]);
            bestElegance = Math.max(bestElegance, totalProfit + square(seenCategories.size()));
        }

        return bestElegance;
    }

    private static long square(int value) {
        return (long) value * value;
    }
}