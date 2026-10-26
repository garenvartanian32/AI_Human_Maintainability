class Solution {
    private static final int MOD = 1_000_000_007;

    public int findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        List<int[]>[] categoryItems = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            categoryItems[i] = new ArrayList<>();
        }
        for (int[] item : items) {
            categoryItems[item[1]].add(item);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long[] dp = new long[k + 1];
        dp[0] = 0;
        int totalItems = 0;
        for (List<int[]> itemList : categoryItems) {
            if (itemList.isEmpty()) continue;

            Collections.sort(itemList, Comparator.comparingInt(a -> -a[0]));
            int categoryProfit = 0;
            for (int[] item : itemList) {
                categoryProfit += item[0];
                pq.offer(item[0]);
                if (pq.size() > totalItems + 1) {
                    categoryProfit -= pq.poll();
                }
            }

            for (int j = Math.min(k, totalItems + itemList.size()); j >= 0; j--) {
                for (int l = Math.max(0, j - totalItems); l <= Math.min(j, itemList.size()); l++) {
                    dp[j] = Math.max(dp[j], (j - l > 0 ? dp[j - l - 1] : 0) + categoryProfit);
                }
            }

            totalItems += itemList.size();
        }

        long res = 0;
        for (int i = 1; i <= k; i++) {
            res = Math.max(res, dp[i] + (long)i * i);
        }

        return (int)(res % MOD);
    }
}