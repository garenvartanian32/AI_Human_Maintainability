#include <assert.h>
#include <bits/stdc++.h>

std::vector<std::tuple<long, long>> get_row(std::vector<std::vector<long>> lst, long x) {
    std::vector<std::tuple<long, long>> result;

    for (long row = 0; row < static_cast<long>(lst.size()); ++row) {
        for (long column = static_cast<long>(lst[row].size()) - 1; column >= 0; --column) {
            if (lst[row][column] == x) {
                result.emplace_back(row, column);
            }
        }
    }
    return result;
}