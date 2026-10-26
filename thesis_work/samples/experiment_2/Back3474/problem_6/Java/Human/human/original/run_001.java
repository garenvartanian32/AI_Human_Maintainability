class Solution {
    public long findMaximumElegance(int[][] A, int k) {
        Arrays.sort(A, (a, b) -> b[0] - a[0]);
        long res = 0, cur = 0;
        List<Integer> dup = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < A.length; ++i) {
            if (i < k) {
                if (seen.contains(A[i][1])) {
                    dup.add(A[i][0]);
                }
                cur += A[i][0];
            } else if (!seen.contains(A[i][1])) {
                if (dup.isEmpty()) break;
                cur += A[i][0] - dup.remove(dup.size() - 1);
            }
            seen.add(A[i][1]);
            res = Math.max(res, cur + 1L * seen.size() * seen.size());
        }
        return res;
    }
}