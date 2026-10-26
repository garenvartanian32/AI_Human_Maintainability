#include<assert.h>
#include<bits/stdc++.h>
// You are given a 2 dimensional data, as a nested vectors,
// which is similar to matrix, however, unlike matrices,
// each row may contain a different number of columns.
// Given lst, and integer x, find integers x in the vector,
// and return vector of tuples, [(x1, y1), (x2, y2) ...] such that
// each tuple is a coordinate - (row, columns), starting with 0.
// Sort coordinates initially by rows in ascending order.
// Also, sort coordinates of the row by columns in descending order.
// Examples:
// >>> get_row((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>({(long)1, (long)2, (long)3, (long)4, (long)5, (long)6}), (std::vector<long>)std::vector<long>({(long)1, (long)2, (long)3, (long)4, (long)1, (long)6}), (std::vector<long>)std::vector<long>({(long)1, (long)2, (long)3, (long)4, (long)5, (long)1})})), (1))
// (std::vector<std::tuple<long, long>>({(std::tuple<long, long>)std::make_tuple(0, 0), (std::tuple<long, long>)std::make_tuple(1, 4), (std::tuple<long, long>)std::make_tuple(1, 0), (std::tuple<long, long>)std::make_tuple(2, 5), (std::tuple<long, long>)std::make_tuple(2, 0)}))
// >>> get_row((std::vector<std::vector<long>>()), (1))
// (std::vector<std::tuple<long, long>>())
// >>> get_row((std::vector<std::vector<long>>({(std::vector<long>)std::vector<long>(), (std::vector<long>)std::vector<long>({(long)1}), (std::vector<long>)std::vector<long>({(long)1, (long)2, (long)3})})), (3))
// (std::vector<std::tuple<long, long>>({(std::tuple<long, long>)std::make_tuple(2, 2)}))
std::vector<std::tuple<long, long>> get_row(std::vector<std::vector<long>> lst, long x) {

    using namespace std;
    vector<tuple<long,long>> result;
    for (size_t r = 0; r < lst.size(); r++) {
        vector<long> cols;
        for (size_t c = 0; c < lst[r].size(); c++) {
            if (lst[r][c] == x) cols.push_back((long) c);
        }
        sort(cols.rbegin(), cols.rend());
        for (long c : cols) result.push_back(std::make_tuple((long) r, c));
    }
    return result;
}