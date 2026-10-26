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
    long total = 0;
    for (auto &row : grid) {
        long s = 0;
        for (long v : row) s += v;
        total += (s + capacity - 1) / capacity;
    }
    return total;
}

int main() {
    std::vector<std::vector<long>> g1 = {{0,0,1,0},{0,1,0,0},{1,1,1,1}};
    std::cout << max_fill(g1, 1) << std::endl;
    std::vector<std::vector<long>> g2 = {{0,0,1,1},{0,0,0,0},{1,1,1,1},{0,1,1,1}};
    std::cout << max_fill(g2, 2) << std::endl;
    return 0;
}