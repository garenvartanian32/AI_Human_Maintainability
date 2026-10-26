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
    std::vector<std::tuple<long, long>> result;
    for (size_t i = 0; i < lst.size(); i++) {
        for (size_t j = 0; j < lst[i].size(); j++) {
            if (lst[i][j] == x) {
                result.push_back(std::make_tuple((long)i, (long)j));
            }
        }
    }
    std::sort(result.begin(), result.end(), [](const std::tuple<long,long>& a, const std::tuple<long,long>& b) {
        if (std::get<0>(a) != std::get<0>(b)) return std::get<0>(a) < std::get<0>(b);
        return std::get<1>(a) > std::get<1>(b);
    });
    return result;
}

int main() {
    std::vector<std::vector<long>> g1 = {{1,2,3,4,5,6},{1,2,3,4,1,6},{1,2,3,4,5,1}};
    for (auto &t : get_row(g1, 1)) std::cout << "(" << std::get<0>(t) << "," << std::get<1>(t) << ") ";
    std::cout << std::endl;
    for (auto &t : get_row({}, 1)) std::cout << "(" << std::get<0>(t) << "," << std::get<1>(t) << ") ";
    std::cout << std::endl;
    std::vector<std::vector<long>> g2 = {{}, {1}, {1,2,3}};
    for (auto &t : get_row(g2, 3)) std::cout << "(" << std::get<0>(t) << "," << std::get<1>(t) << ") ";
    std::cout << std::endl;
}