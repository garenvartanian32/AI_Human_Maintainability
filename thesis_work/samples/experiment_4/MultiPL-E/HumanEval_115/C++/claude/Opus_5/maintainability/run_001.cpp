#include<assert.h>
#include<bits/stdc++.h>
// You are given a rectangular grid of wells. Each row represents a single well,
// and each 1 in a row represents a single unit of water.
// Each well has a corresponding bucket that can be used to extract water from it, 
// and all buckets have the same capacity.
// Your task is to use the buckets to empty the wells.
// Output the number of times you need to lower the buckets.
// Example 1:
// >>> max_fill((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>({(long)0, (long)0, (long)1, (long)0}), (std::vector<long>)std::vector<long>({(long)0, (long)1, (long)0, (long)0}), (std::vector<long>)std::vector<long>({(long)1, (long)1, (long)1, (long)1})})), (1))
// (6)
// Example 2:
// >>> max_fill((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>({(long)0, (long)0, (long)1, (long)1}), (std::vector<long>)std::vector<long>({(long)0, (long)0, (long)0, (long)0}), (std::vector<long>)std::vector<long>({(long)1, (long)1, (long)1, (long)1}), (std::vector<long>)std::vector<long>({(long)0, (long)1, (long)1, (long)1})})), (2))
// (5)
// Example 3:
// >>> max_fill((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>({(long)0, (long)0, (long)0}), (std::vector<long>)std::vector<long>({(long)0, (long)0, (long)0})})), (5))
// (0)
// Constraints:
// * all wells have the same length
// * 1 <= grid.length <= 10^2
// * 1 <= grid[:,1].length <= 10^2
// * grid[i][j] -> 0 | 1
// * 1 <= capacity <= 10
long max_fill(std::vector<std::vector<long>> grid, long capacity) {
    // Wells are independent: each one needs its own water divided by the bucket
    // size, rounded up because a part-full bucket still counts as one lowering.
    long totalLowerings = 0;
    for (const std::vector<long>& well : grid) {
        const long unitsOfWater = std::accumulate(well.begin(), well.end(), 0L);
        totalLowerings += (unitsOfWater + capacity - 1) / capacity;
    }
    return totalLowerings;
}