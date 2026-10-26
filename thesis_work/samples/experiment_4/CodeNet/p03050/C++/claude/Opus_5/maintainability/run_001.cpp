#include <iostream>
#include <set>

int main() {
    long long n;
    std::cin >> n;

    // Writing q for the shared value, n = q*m + q = q*(m + 1), so m + 1 must
    // divide n. Enumerating divisors of n therefore enumerates all candidates.
    long long total = 0;
    for (long long divisor = 1; divisor * divisor <= n; ++divisor) {
        if (n % divisor != 0) {
            continue;
        }

        // A divisor pairs with n / divisor; a set avoids double counting when
        // they coincide at a perfect square.
        const std::set<long long> candidates{divisor - 1, n / divisor - 1};
        for (long long candidate : candidates) {
            if (candidate >= 1 && n / candidate == n % candidate) {
                total += candidate;
            }
        }
    }

    std::cout << total << '\n';
    return 0;
}