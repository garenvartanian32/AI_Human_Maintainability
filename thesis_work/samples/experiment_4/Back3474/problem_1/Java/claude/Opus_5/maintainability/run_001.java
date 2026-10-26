import java.util.HashMap;
import java.util.Map;

/**
 * Finds the maximum sum of a pair of numbers whose largest decimal digit matches.
 */
class Solution {

    private static final int NO_PAIR_FOUND = -1;

    public int maxSum(int[] nums) {
        // For each largest digit, remember the biggest value seen so far.
        Map<Integer, Integer> bestPerDigit = new HashMap<>();
        int bestSum = NO_PAIR_FOUND;

        for (int value : nums) {
            int digit = largestDigit(value);
            Integer previousBest = bestPerDigit.get(digit);

            if (previousBest != null) {
                bestSum = Math.max(bestSum, previousBest + value);
                bestPerDigit.put(digit, Math.max(previousBest, value));
            } else {
                bestPerDigit.put(digit, value);
            }
        }

        return bestSum;
    }

    /** Returns the greatest decimal digit appearing in {@code number}. */
    private int largestDigit(int number) {
        int largest = 0;
        for (int remaining = number; remaining > 0; remaining /= 10) {
            largest = Math.max(largest, remaining % 10);
        }
        return largest;
    }
}