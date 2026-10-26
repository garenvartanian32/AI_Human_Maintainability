public class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        int minAbsDiff = Integer.MAX_VALUE;
        
        // Create a TreeMap to store the indices of elements
        TreeMap<Integer, Integer> indexMap = new TreeMap<>();
        
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            
            // Check if there exists a number in the TreeMap with a difference of at least x
            // from the current number, and update the minimum absolute difference accordingly.
            Integer floorKey = indexMap.floorKey(num - x);
            if (floorKey != null) {
                int index = indexMap.get(floorKey);
                minAbsDiff = Math.min(minAbsDiff, Math.abs(num - nums.get(index)));
            }
            
            // Check if there exists a number in the TreeMap with a difference of at least x
            // from the current number, and update the minimum absolute difference accordingly.
            Integer ceilingKey = indexMap.ceilingKey(num + x);
            if (ceilingKey != null) {
                int index = indexMap.get(ceilingKey);
                minAbsDiff = Math.min(minAbsDiff, Math.abs(num - nums.get(index)));
            }
            
            // Update the index of the current number in the TreeMap.
            indexMap.put(num, i);
        }
        
        return minAbsDiff;
    }
   
}