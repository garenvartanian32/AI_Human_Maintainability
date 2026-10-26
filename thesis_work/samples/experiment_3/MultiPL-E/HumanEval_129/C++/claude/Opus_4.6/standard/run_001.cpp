#include<assert.h>
#include<bits/stdc++.h>
// Given a grid with N rows and N columns (N >= 2) and a positive integer k, 
// each cell of the grid contains a value. Every integer in the range [1, N * N]
// inclusive appears exactly once on the cells of the grid.
// You have to find the minimum path of length k in the grid. You can start
// from any cell, and in each step you can move to any of the neighbor cells,
// in other words, you can go to cells which share an edge with you current
// cell.
// Please note that a path of length k means visiting exactly k cells (not
// necessarily distinct).
// You CANNOT go off the grid.
// A path A (of length k) is considered less than a path B (of length k) if
// after making the ordered vectors of the values on the cells that A and B go
// through (let's call them lst_A and lst_B), lst_A is lexicographically less
// than lst_B, in other words, there exist an integer index i (1 <= i <= k)
// such that lst_A[i] < lst_B[i] and for any j (1 <= j < i) we have
// lst_A[j] = lst_B[j].
// It is guaranteed that the answer is unique.
// Return an ordered vector of the values on the cells that the minimum path go through.
// Examples:    
// >>> minPath((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>({(long)1, (long)2, (long)3}), (std::vector<long>)std::vector<long>({(long)4, (long)5, (long)6}), (std::vector<long>)std::vector<long>({(long)7, (long)8, (long)9})})), (3))
// (std::vector<long>({(long)1, (long)2, (long)1}))
// >>> minPath((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>({(long)5, (long)9, (long)3}), (std::vector<long>)std::vector<long>({(long)4, (long)1, (long)6}), (std::vector<long>)std::vector<long>({(long)7, (long)8, (long)2})})), (1))
// (std::vector<long>({(long)1}))
std::vector<long> minPath(std::vector<std::vector<long>> grid, long k) {
    int n = grid.size();
    int pi = -1, pj = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 1) { pi = i; pj = j; }
        }
    }
    int di[4] = {-1,1,0,0};
    int dj[4] = {0,0,-1,1};
    long m = LONG_MAX;
    for (int d = 0; d < 4; d++) {
        int ni = pi + di[d], nj = pj + dj[d];
        if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
            m = std::min(m, grid[ni][nj]);
        }
    }
    std::vector<long> result;
    for (long idx = 0; idx < k; idx++) {
        result.push_back(idx % 2 == 0 ? 1 : m);
    }
    return result;
}

int main() {
    std::vector<std::vector<long>> g1 = {{1,2,3},{4,5,6},{7,8,9}};
    for (long v : minPath(g1, 3)) std::cout << v << " ";
    std::cout << std::endl;
    std::vector<std::vector<long>> g2 = {{5,9,3},{4,1,6},{7,8,2}};
    for (long v : minPath(g2, 1)) std::cout << v << " ";
    std::cout << std::endl;
    return 0;
}