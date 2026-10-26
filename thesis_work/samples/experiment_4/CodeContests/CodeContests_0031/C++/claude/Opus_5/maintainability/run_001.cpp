#include <algorithm>
#include <iostream>
#include <set>
#include <sstream>
#include <string>
#include <vector>

namespace {

constexpr int kFree = 0;
constexpr int kNoRunFound = -1;
const std::string kNullResult = "NULL";
const std::string kIllegalErase = "ILLEGAL_ERASE_ARGUMENT";

/// Fixed-size memory modelled as a vector of owning block identifiers.
class MemoryManager {
public:
    explicit MemoryManager(int size) : cells_(size, kFree) {}

    /// Reserves the first run of free cells that is long enough.
    std::string allocate(int cellsNeeded) {
        const int start = findFreeRun(cellsNeeded);
        if (start == kNoRunFound) {
            return kNullResult;
        }

        const int identifier = nextIdentifier_++;
        std::fill(cells_.begin() + start, cells_.begin() + start + cellsNeeded, identifier);
        liveBlocks_.insert(identifier);
        return std::to_string(identifier);
    }

    /// Frees a live block; reports whether an error message is needed.
    bool erase(int identifier, std::string* error) {
        if (liveBlocks_.erase(identifier) == 0) {
            *error = kIllegalErase;
            return false;
        }
        for (int& owner : cells_) {
            if (owner == identifier) {
                owner = kFree;
            }
        }
        return true;
    }

    /// Slides every allocated block towards the start, preserving order.
    void defragment() {
        std::size_t writePosition = 0;
        for (std::size_t cell = 0; cell < cells_.size(); ++cell) {
            if (cells_[cell] != kFree) {
                cells_[writePosition++] = cells_[cell];
            }
        }
        std::fill(cells_.begin() + writePosition, cells_.end(), kFree);
    }

private:
    int findFreeRun(int cellsNeeded) const {
        int runLength = 0;
        for (std::size_t cell = 0; cell < cells_.size(); ++cell) {
            runLength = (cells_[cell] == kFree) ? runLength + 1 : 0;
            if (runLength == cellsNeeded) {
                return static_cast<int>(cell) - cellsNeeded + 1;
            }
        }
        return kNoRunFound;
    }

    std::vector<int> cells_;
    std::set<int> liveBlocks_;
    int nextIdentifier_ = 1;
};

}  // namespace

int main() {
    int operationCount, memorySize;
    std::cin >> operationCount >> memorySize;
    std::cin.ignore();

    MemoryManager manager(memorySize);
    std::ostringstream output;

    for (int operation = 0; operation < operationCount; ++operation) {
        std::string line;
        if (!std::getline(std::cin, line)) {
            break;
        }
        std::istringstream parts(line);
        std::string command;
        if (!(parts >> command)) {
            --operation;
            continue;
        }

        if (command == "alloc") {
            int cellsNeeded;
            parts >> cellsNeeded;
            output << manager.allocate(cellsNeeded) << '\n';
        } else if (command == "erase") {
            int identifier;
            parts >> identifier;
            std::string error;
            if (!manager.erase(identifier, &error)) {
                output << error << '\n';
            }
        } else if (command == "defragment") {
            manager.defragment();
        }
    }

    std::cout << output.str();
    return 0;
}