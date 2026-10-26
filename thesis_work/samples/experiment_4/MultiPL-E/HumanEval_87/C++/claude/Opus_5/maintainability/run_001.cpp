#include <assert.h>
#include <bits/stdc++.h>

std::vector<std::tuple<long, long>> get_row(std::vector<std::vector<long>> lst, long x) {
    std::vector<std::tuple<long, long>> coordinates;

    for (std::size_t row_index = 0; row_index < lst.size(); ++row_index) {
        const std::vector<long>& row = lst[row_index];

        for (std::size_t column = row.size(); column > 0; --column) {
            const std::size_t column_index = column - 1;

            if (row[column_index] == x) {
                coordinates.emplace_back(
                    static_cast<long>(row_index),
                    static_cast<long>(column_index)
                );
            }
        }
    }

    return coordinates;
}