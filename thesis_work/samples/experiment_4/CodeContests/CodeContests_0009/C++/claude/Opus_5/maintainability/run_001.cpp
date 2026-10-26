#include <algorithm>
#include <iostream>
#include <vector>

namespace {

constexpr long long kNotPossible = -1;

/// Returns cumulative reading times after sorting ascending.
std::vector<long long> prefixSums(std::vector<long long> times) {
    std::sort(times.begin(), times.end());
    std::vector<long long> prefix(times.size() + 1, 0);
    for (std::size_t i = 0; i < times.size(); ++i) {
        prefix[i + 1] = prefix[i] + times[i];
    }
    return prefix;
}

}  // namespace

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int bookCount, requiredLikes;
    std::cin >> bookCount >> requiredLikes;

    std::vector<long long> likedByBoth, likedByAliceOnly, likedByBobOnly;
    for (int book = 0; book < bookCount; ++book) {
        long long readingTime, aliceLikes, bobLikes;
        std::cin >> readingTime >> aliceLikes >> bobLikes;

        if (aliceLikes && bobLikes) {
            likedByBoth.push_back(readingTime);
        } else if (aliceLikes) {
            likedByAliceOnly.push_back(readingTime);
        } else if (bobLikes) {
            likedByBobOnly.push_back(readingTime);
        }
    }

    const std::size_t bothCount = likedByBoth.size();
    const std::size_t aliceCount = likedByAliceOnly.size();
    const std::size_t bobCount = likedByBobOnly.size();

    const std::vector<long long> bothPrefix = prefixSums(likedByBoth);
    const std::vector<long long> alicePrefix = prefixSums(likedByAliceOnly);
    const std::vector<long long> bobPrefix = prefixSums(likedByBobOnly);

    // Choosing `shared` both-liked books covers that many likes for each reader;
    // the shortfall must be topped up from each single-reader pile.
    long long bestTime = kNotPossible;
    for (std::size_t shared = 0; shared <= bothCount; ++shared) {
        const long long shortfall =
            std::max<long long>(static_cast<long long>(requiredLikes) - static_cast<long long>(shared), 0);
        if (shortfall > static_cast<long long>(aliceCount)
            || shortfall > static_cast<long long>(bobCount)) {
            continue;
        }

        const long long totalTime =
            bothPrefix[shared] + alicePrefix[shortfall] + bobPrefix[shortfall];
        if (bestTime == kNotPossible || totalTime < bestTime) {
            bestTime = totalTime;
        }
    }

    std::cout << bestTime << '\n';
    return 0;
}