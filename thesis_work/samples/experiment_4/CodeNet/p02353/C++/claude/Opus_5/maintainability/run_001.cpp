#include <iostream>
#include <sstream>
#include <vector>

namespace {

constexpr int kUpdateCommand = 0;

/// Segment tree supporting assign-on-range and sum-over-range.
class LazySegmentTree {
public:
    explicit LazySegmentTree(int length) {
        width_ = 1;
        while (width_ < length) {
            width_ *= 2;
        }
        sums_.assign(2 * width_, 0);
        pending_.assign(2 * width_, 0);
        hasPending_.assign(2 * width_, 0);
    }

    /// Sets every element in [first, last] to `value`.
    void assign(int first, int last, long long value) {
        assign(1, 0, width_ - 1, first, last, value);
    }

    /// Returns the sum over [first, last].
    long long total(int first, int last) {
        return total(1, 0, width_ - 1, first, last);
    }

private:
    /// Hands a pending assignment down to both children.
    void pushDown(int node, int span) {
        if (!hasPending_[node]) {
            return;
        }

        const long long value = pending_[node];
        const int half = span / 2;
        for (int child = 2 * node; child <= 2 * node + 1; ++child) {
            sums_[child] = value * half;
            pending_[child] = value;
            hasPending_[child] = 1;
        }
        hasPending_[node] = 0;
    }

    void assign(int node, int low, int high, int first, int last, long long value) {
        if (last < low || high < first) {
            return;
        }
        if (first <= low && high <= last) {
            sums_[node] = value * (high - low + 1);
            pending_[node] = value;
            hasPending_[node] = 1;
            return;
        }

        pushDown(node, high - low + 1);
        const int middle = (low + high) / 2;
        assign(2 * node, low, middle, first, last, value);
        assign(2 * node + 1, middle + 1, high, first, last, value);
        sums_[node] = sums_[2 * node] + sums_[2 * node + 1];
    }

    long long total(int node, int low, int high, int first, int last) {
        if (last < low || high < first) {
            return 0;
        }
        if (first <= low && high <= last) {
            return sums_[node];
        }

        pushDown(node, high - low + 1);
        const int middle = (low + high) / 2;
        return total(2 * node, low, middle, first, last)
             + total(2 * node + 1, middle + 1, high, first, last);
    }

    int width_;
    std::vector<long long> sums_;
    std::vector<long long> pending_;
    std::vector<char> hasPending_;
};

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int length, queryCount;
    std::cin >> length >> queryCount;

    LazySegmentTree tree(length);
    std::ostringstream answers;

    for (int query = 0; query < queryCount; ++query) {
        int command, first, last;
        std::cin >> command >> first >> last;

        if (command == kUpdateCommand) {
            long long value;
            std::cin >> value;
            tree.assign(first, last, value);
        } else {
            answers << tree.total(first, last) << '\n';
        }
    }

    std::cout << answers.str();
    return 0;
}