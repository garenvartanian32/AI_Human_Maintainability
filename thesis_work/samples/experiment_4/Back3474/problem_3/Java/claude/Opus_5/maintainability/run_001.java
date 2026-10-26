import java.util.TreeMap;

/**
 * Minimum absolute difference between elements at least x indices apart.
 */
class Solution {

    /**
     * Returns min |nums[i] - nums[j]| over all pairs with |i - j| &gt;= x.
     *
     * <p>A sorted multiset holds every element that is far enough behind the
     * current index. For each position we only need the two entries that
     * bracket the current value.
     */
    public int minAbsoluteDifference(int[] nums, int x) {
        if (x == 0) {
            return 0;
        }

        // Value -> how many copies are currently eligible.
        TreeMap<Integer, Integer> eligibleValues = new TreeMap<>();
        int smallestDifference = Integer.MAX_VALUE;

        for (int index = x; index < nums.length; index++) {
            eligibleValues.merge(nums[index - x], 1, Integer::sum);
            int current = nums[index];

            Integer justBelow = eligibleValues.floorKey(current);
            if (justBelow != null) {
                smallestDifference = Math.min(smallestDifference, current - justBelow);
            }

            Integer justAbove = eligibleValues.ceilingKey(current);
            if (justAbove != null) {
                smallestDifference = Math.min(smallestDifference, justAbove - current);
            }
        }

        return smallestDifference;
    }
}