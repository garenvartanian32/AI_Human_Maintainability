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
    const int size = static_cast<int>(grid.size());
    const int rowSteps[4] = {-1, 1, 0, 0};
    const int columnSteps[4] = {0, 0, -1, 1};

    // The smallest value on the board is 1, so the best path must sit on it and
    // then bounce between it and its cheapest neighbour.
    int oneRow = 0, oneColumn = 0;
    for (int row = 0; row < size; ++row) {
        for (int column = 0; column < size; ++column) {
            if (grid[row][column] == 1) {
                oneRow = row;
                oneColumn = column;
            }
        }
    }

    long cheapestNeighbour = LONG_MAX;
    for (int move = 0; move < 4; ++move) {
        const int row = oneRow + rowSteps[move];
        const int column = oneColumn + columnSteps[move];
        if (row >= 0 && row < size && column >= 0 && column < size) {
            cheapestNeighbour = std::min(cheapestNeighbour, grid[row][column]);
        }
    }

    std::vector<long> path;
    path.reserve(static_cast<std::size_t>(k));
    for (long step = 0; step < k; ++step) {
        path.push_back(step % 2 == 0 ? 1L : cheapestNeighbour);
    }
    return path;
}