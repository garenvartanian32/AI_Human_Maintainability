class Solution:
  def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
    if x == 0:
      return 0

    arr, best_dist = SortedList([]), float('inf')
    for i in range(x, len(nums)):
      arr.add(nums[i - x])
      v = nums[i]

      pos = arr.bisect_left(v)
      if pos < len(arr):
        best_dist = min(best_dist, abs(arr[pos] - v))
      if pos > 0:
        best_dist = min(best_dist, abs(arr[pos - 1] - v))
    
    return best_dist