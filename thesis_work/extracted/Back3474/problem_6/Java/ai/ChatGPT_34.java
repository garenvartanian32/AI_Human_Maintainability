public class Solution {
    public int findMaximumElegance(int[][] items, int k) {
        List<int[]> itemList = new ArrayList<>();
        Map<Integer, Integer> categoryFreq = new HashMap<>();
        int totalProfit = 0;
        int distinctCategories = 0;
        int maxElegance = 0;

        for (int[] item : items) {
            itemList.add(item);
        }

        Collections.sort(itemList, (a, b) -> b[0] - a[0]); // Sort by profit in descending order

        for (int i = 0; i < itemList.size(); i++) {
            int[] item = itemList.get(i);
            int profit = item[0];
            int category = item[1];

            categoryFreq.put(category, categoryFreq.getOrDefault(category, 0) + 1);
            totalProfit += profit;

            if (categoryFreq.get(category) == 1) {
                distinctCategories++;
            }

            int elegance = totalProfit + distinctCategories * distinctCategories;
            maxElegance = Math.max(maxElegance, elegance);

            if (distinctCategories == k) {
                break; // We have reached the required number of distinct categories
            }
        }

        return maxElegance;
    }
}