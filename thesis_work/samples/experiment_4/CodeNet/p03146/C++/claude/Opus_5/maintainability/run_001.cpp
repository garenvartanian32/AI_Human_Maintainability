#include <iostream>
#include <unordered_set>

namespace {

/// Halves an even term; otherwise triples it and adds one.
long long nextTerm(long long value) {
    return (value % 2 == 0) ? value / 2 : 3 * value + 1;
}

}  // namespace

int main() {
    long long start;
    std::cin >> start;

    // The sequence is deterministic, so the first repeated value is the point
    // at which it has entered a cycle.
    std::unordered_set<long long> seen;
    seen.insert(start);

    long long value = start;
    int index = 1;

    while (true) {
        value = nextTerm(value);
        ++index;
        if (!seen.insert(value).second) {
            std::cout << index << '\n';
            return 0;
        }
    }
}