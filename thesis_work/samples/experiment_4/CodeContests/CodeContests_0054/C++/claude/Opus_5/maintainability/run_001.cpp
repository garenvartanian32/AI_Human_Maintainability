#include <algorithm>
#include <iostream>
#include <vector>

namespace {

/// Sorts `values` in place; returns how many real swaps were performed.
int selectionSort(std::vector<int>& values) {
    int swapCount = 0;

    for (std::size_t boundary = 0; boundary < values.size(); ++boundary) {
        std::size_t smallest = boundary;
        for (std::size_t candidate = boundary + 1; candidate < values.size(); ++candidate) {
            if (values[candidate] < values[smallest]) {
                smallest = candidate;
            }
        }

        // Only count a swap when the element actually moves.
        if (smallest != boundary) {
            std::swap(values[boundary], values[smallest]);
            ++swapCount;
        }
    }

    return swapCount;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int count;
    std::cin >> count;
    std::vector<int> values(count);
    for (int& value : values) {
        std::cin >> value;
    }

    const int swapCount = selectionSort(values);

    for (int i = 0; i < count; ++i) {
        if (i > 0) {
            std::cout << ' ';
        }
        std::cout << values[i];
    }
    std::cout << '\n' << swapCount << '\n';
    return 0;
}