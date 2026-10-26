import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Maximum score obtainable by choosing k subarrays greedily by value.
 */
class Solution {

    private static final long MODULUS = 1_000_000_007L;

    /**
     * Returns the maximum score achievable with exactly k operations.
     *
     * <p>Each element may be chosen as the dominant element of any subarray
     * where its prime score beats everything to its left and ties-or-beats
     * everything to its right. We spend the k operations on the largest values
     * first, limited by how many subarrays each element dominates.
     */
    public int maximumScore(List<Integer> nums, int k) {
        int[] primeScores = computePrimeScores(nums);
        long[] reach = countDominatedSubarrays(primeScores);

        Integer[] byValueDescending = new Integer[nums.size()];
        Arrays.setAll(byValueDescending, i -> i);
        Arrays.sort(byValueDescending, (a, b) -> Integer.compare(nums.get(b), nums.get(a)));

        long score = 1;
        long operationsLeft = k;
        for (int index : byValueDescending) {
            if (operationsLeft == 0) {
                break;
            }
            long uses = Math.min(reach[index], operationsLeft);
            score = score * modularPower(nums.get(index), uses) % MODULUS;
            operationsLeft -= uses;
        }

        return (int) score;
    }

    /** Number of distinct prime factors for every value, via a sieve. */
    private int[] computePrimeScores(List<Integer> nums) {
        int limit = 0;
        for (int value : nums) {
            limit = Math.max(limit, value);
        }
        limit++;

        int[] distinctPrimeCounts = new int[limit];
        for (int candidate = 2; candidate < limit; candidate++) {
            if (distinctPrimeCounts[candidate] == 0) { // candidate is prime
                for (int multiple = candidate; multiple < limit; multiple += candidate) {
                    distinctPrimeCounts[multiple]++;
                }
            }
        }

        int[] primeScores = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            primeScores[i] = distinctPrimeCounts[nums.get(i)];
        }
        return primeScores;
    }

    /** For each index, how many subarrays it dominates by prime score. */
    private long[] countDominatedSubarrays(int[] primeScores) {
        int length = primeScores.length;

        int[] previousGreater = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = 0; index < length; index++) {
            while (!stack.isEmpty() && primeScores[stack.peek()] < primeScores[index]) {
                stack.pop();
            }
            previousGreater[index] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(index);
        }

        int[] nextGreaterOrEqual = new int[length];
        stack.clear();
        for (int index = length - 1; index >= 0; index--) {
            while (!stack.isEmpty() && primeScores[stack.peek()] <= primeScores[index]) {
                stack.pop();
            }
            nextGreaterOrEqual[index] = stack.isEmpty() ? length : stack.peek();
            stack.push(index);
        }

        long[] reach = new long[length];
        for (int index = 0; index < length; index++) {
            reach[index] = (long) (index - previousGreater[index])
                    * (nextGreaterOrEqual[index] - index);
        }
        return reach;
    }

    /** Computes base^exponent modulo {@link #MODULUS}. */
    private long modularPower(long base, long exponent) {
        long result = 1;
        long factor = base % MODULUS;
        for (long remaining = exponent; remaining > 0; remaining >>= 1) {
            if ((remaining & 1L) == 1L) {
                result = result * factor % MODULUS;
            }
            factor = factor * factor % MODULUS;
        }
        return result;
    }
}