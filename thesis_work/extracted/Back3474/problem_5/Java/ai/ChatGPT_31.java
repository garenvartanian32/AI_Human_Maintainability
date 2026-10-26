public class Solution {
    public int maximumScore(List<Integer> nums, int k) {
        int MOD = 1000000007;
        int n = nums.size();
        
        // Create an array to store the prime scores for each element.
        int[] primeScores = new int[n];
        
        // Calculate the prime scores for each element using a helper function.
        for (int i = 0; i < n; i++) {
            primeScores[i] = calculatePrimeScore(nums.get(i));
        }
        
        long score = 1L; // Initialize the score as a long integer to avoid overflow.
        
        // Create a max heap to keep track of the elements with the highest prime scores.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(primeScores[b], primeScores[a]));
        
        // Initialize the max heap with the element at index k.
        maxHeap.offer(k);
        
        // Create two arrays to keep track of the left and right boundaries of the subarray.
        int[] left = new int[n];
        int[] right = new int[n];
        
        // Initialize the left and right boundaries.
        left[k] = k;
        right[k] = k;
        
        // Perform at most k operations.
        for (int i = 0; i < k; i++) {
            int maxIndex = maxHeap.poll(); // Get the index with the highest prime score.
            
            // Calculate the new score by multiplying the current score by the selected element.
            score = (score * nums.get(maxIndex)) % MOD;
            
            // Update the left and right boundaries for the subarray.
            int newLeft = left[maxIndex] - 1;
            int newRight = right[maxIndex] + 1;
            
            // Add the new potential candidates to the max heap.
            if (newLeft >= 0) {
                maxHeap.offer(newLeft);
                left[newLeft] = newLeft;
                right[newLeft] = right[maxIndex];
            }
            if (newRight < n) {
                maxHeap.offer(newRight);
                left[newRight] = left[maxIndex];
                right[newRight] = newRight;
            }
        }
        
        return (int)score;
    }
    
    // Helper function to calculate the prime score of an integer.
    private int calculatePrimeScore(int num) {
        Set<Integer> factors = new HashSet<>();
        
        // Find prime factors of the number.
        while (num % 2 == 0) {
            factors.add(2);
            num /= 2;
        }
        
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            while (num % i == 0) {
                factors.add(i);
                num /= i;
            }
        }
        
        if (num > 2) {
            factors.add(num);
        }
        
        return factors.size();
    }
}