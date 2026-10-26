#include <iostream>

namespace {

/// Returns how many candles burn in total, recycling stubs as we go.
long long totalCandlesBurned(long long initialCandles, long long stubsPerCandle) {
    long long burned = initialCandles;
    long long stubsOnHand = initialCandles;

    while (stubsOnHand >= stubsPerCandle) {
        const long long recycled = stubsOnHand / stubsPerCandle;
        burned += recycled;
        // The recycled candles burn down into stubs of their own.
        stubsOnHand = stubsOnHand % stubsPerCandle + recycled;
    }

    return burned;
}

}  // namespace

int main() {
    long long initialCandles, stubsPerCandle;
    std::cin >> initialCandles >> stubsPerCandle;
    std::cout << totalCandlesBurned(initialCandles, stubsPerCandle) << '\n';
    return 0;
}