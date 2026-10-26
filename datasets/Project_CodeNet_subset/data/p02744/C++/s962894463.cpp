#include <iostream>
#include <vector>
#include <string>

std::vector<std::string> solv(int N) {
  if (N == 1) return {"a"};

  auto const& rec = solv(N-1);
  std::vector<std::string> ret;

  for (auto const& s : solv(N-1)) {
    char d='a';
    for (auto const c : s) {
      d = std::max(c, d);
    }
    std::string t = s;
    t.push_back('a');
    for (char c='a'; c<d+2; c++) {
      t[N-1] = c;
      ret.push_back(t);
    }
  }

  return ret;
}

int main() {
  int N;
  std::cin >> N;

  for (auto const& s : solv(N)) {
    std::cout << s << '\n';
  }

  return 0;
}
