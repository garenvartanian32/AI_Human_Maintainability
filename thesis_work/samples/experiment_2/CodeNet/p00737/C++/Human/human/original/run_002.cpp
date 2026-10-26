#if __has_include("../library/Basic/Debug.hpp")

#include "../library/Basic/Debug.hpp"

#else

/* ----- Header Files ----- */
// IO
#include <cstdio>
#include <iomanip>
#include <ios>
#include <iostream>

// algorithm
#include <algorithm>
#include <cmath>
#include <numeric>

// container
#include <vector>
#include <string>
#include <tuple>
#include <set>
#include <map>
#include <stack>
#include <queue>
#include <deque>

// others
#include <random>
#include <limits>
#include <functional>
#include <ctime>
#include <cassert>
#include <cstdint>


/* ----- Type Alias ----- */
using Bool = bool;
using Int = long long int;
using Real = long double;
using Char = char;
using String = std::string;
template <class... Ts>
using Tuple = std::tuple<Ts...>;

template <class T>
using Vector = std::vector<T>;
template <class T>
using Matrix = Vector<Vector<T>>;
template <class T>
using Queue = std::queue<T>;
template <class T>
using Stack = std::stack<T>;
template <class T>
using Deque = std::deque<T>;

template <class T>
using MaxHeap = std::priority_queue<T>;
template <class T>
using MinHeap = std::priority_queue<T, std::vector<T>, std::greater<T>>;
template <class T>
using Set = std::set<T>;
template <class T, class U>
using Map = std::map<T, U>;

template <class T, class... Us>
using Func = std::function<T(Us...)>;

template <class T>
T genv(T v) { return v; }

template <class T, class... Ts>
auto genv(size_t l, Ts... ts) {
    return Vector<decltype(genv<T>(ts...))>(l, genv<T>(ts...));
}

template <class Cost = Int>
struct Edge {
    Int src, dst;
    Cost cost;
    Edge(Int src = -1, Int dst = -1, Cost cost = 1)
        : src(src), dst(dst), cost(cost){};

    bool operator<(const Edge<Cost>& e) const { return this->cost < e.cost; }
    bool operator>(const Edge<Cost>& e) const { return this->cost > e.cost; }
};

template <class Cost = Int>
using Edges = Vector<Edge<Cost>>;
template <class Cost = Int>
using Graph = Vector<Vector<Edge<Cost>>>;

#endif

/* ----- Misc ----- */
void fastio() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
}

struct Fout {
    Int precision;
    Fout(Int precision) : precision(precision) {}
};
std::ostream& operator<<(std::ostream& os, const Fout& fio) {
    os << std::fixed << std::setprecision(fio.precision);
    return os;
}


/* ----- Constants ----- */
constexpr Int INF = std::numeric_limits<Int>::max() / 3;
// constexpr Int MOD = 1000000007;
// constexpr Real PI = acos(-1);
// constexpr Real EPS = 1e-10;
// std::mt19937 mt(int(std::time(nullptr)));

const Vector<Int> dx({0, 1, 0, -1}), dy({1, 0, -1, 0});

Bool solve() {
    Int w, h;
    std::cin >> w >> h;
    if (w == 0) return false;

    Matrix<Int> s = genv<Int>(h, w, 0LL);
    for (auto& v : s) {
        for (auto& x : v) {
            std::cin >> x;
        }
    }

    Vector<Int> c(4);
    for (auto& x : c) std::cin >> x;

    auto dp = genv<Int>(h, w, 4, INF);
    dp[0][0][0] = 0;

    MinHeap<Tuple<Int, Int, Int, Int>> heap;
    heap.emplace(0, 0, 0, 0);
    while (!heap.empty()) {
        Int dist, x, y, d;
        std::tie(dist, x, y, d) = heap.top();
        heap.pop();
        if (dist > dp[x][y][d]) continue;

        for (Int i = 0; i < 4; ++i) {
            Int nd = (d + i) % 4;
            Int nx = x + dx[nd], ny = y + dy[nd];
            Int cost = (i == s[x][y] ? 0 : c[i]);

            if (nx < 0 || h <= nx || ny < 0 || w <= ny ||
                dp[nx][ny][nd] <= dp[x][y][d] + cost) continue;

            dp[nx][ny][nd] = dp[x][y][d] + cost;
            heap.emplace(dp[nx][ny][nd], nx, ny, nd);
        }
    }

    std::cout << *std::min_element(dp[h - 1][w - 1].begin(), dp[h - 1][w - 1].end()) << std::endl;
    return true;
}

int main() {
    while (solve()) {}
    return 0;
}

