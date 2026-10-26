#include <iostream>
#include <map>
#include <set>
#include <string>
#include <vector>

namespace {

const std::string kBackwardMarker = "->";

/**
 * Returns the carriages from the front, given one walk record.
 *
 * Each move links the current carriage to the next one either behind it
 * ("->") or in front of it. Recording both links builds a doubly linked list,
 * and the front carriage is the only one with nothing ahead of it.
 */
std::string rebuildTrain(const std::string& record) {
    std::map<char, char> carriageBehind;
    std::map<char, char> carriageAhead;
    std::vector<char> seen;
    std::set<char> known;

    char current = record[0];
    seen.push_back(current);
    known.insert(current);

    std::size_t position = 1;
    while (position < record.size()) {
        const bool movedBackward = record.compare(position, kBackwardMarker.size(),
                                                  kBackwardMarker) == 0;
        if (movedBackward) {
            position += kBackwardMarker.size();
        }

        const char nextCarriage = record[position];
        position++;
        if (known.insert(nextCarriage).second) {
            seen.push_back(nextCarriage);
        }

        if (movedBackward) {
            carriageBehind[current] = nextCarriage;
            carriageAhead[nextCarriage] = current;
        } else {
            carriageAhead[current] = nextCarriage;
            carriageBehind[nextCarriage] = current;
        }

        current = nextCarriage;
    }

    char front = 0;
    for (char carriage : seen) {
        if (carriageAhead.find(carriage) == carriageAhead.end()) {
            front = carriage;
            break;
        }
    }

    std::string order;
    char carriage = front;
    while (true) {
        order += carriage;
        auto next = carriageBehind.find(carriage);
        if (next == carriageBehind.end()) {
            break;
        }
        carriage = next->second;
    }
    return order;
}

}  // namespace

int main() {
    int recordCount;
    std::cin >> recordCount;
    std::cin.ignore();

    std::string output;
    for (int i = 0; i < recordCount; ++i) {
        std::string record;
        std::getline(std::cin, record);
        while (!record.empty() && (record.back() == '\r' || record.back() == ' ')) {
            record.pop_back();
        }
        output += rebuildTrain(record) + "\n";
    }

    std::cout << output;
    return 0;
}