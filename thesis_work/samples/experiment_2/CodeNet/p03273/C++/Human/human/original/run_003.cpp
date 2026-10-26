#include <iostream>
#include <string>
#include <vector>

bool IsWhiteLine(const std::string s);
std::vector<std::string> RemoveWhiteLine(std::vector<std::string> v);
std::vector<std::string> FindWhiteVertical(std::vector<std::string> v, int H, int W);
std::vector<std::string> RemoveWhiteVertical(std::vector<std::string> v);

int main() {
  int H, W;  // H is row, W is column 
  std::cin >> H >> W;

  std::vector<std::string> v;
  v.reserve(H);

  std::string line;
  while (v.size() < H) {
    std::cin >> line;
    v.push_back(line);
  }

  v = RemoveWhiteLine(v);
  H = v.size();
  v = FindWhiteVertical(v, H, W);
  v = RemoveWhiteVertical(v);

  for (auto s : v) {
    std::cout << s << std::endl;
  }

  return 0;
}

bool IsWhiteLine(const std::string s) {
  // '.' == white, '#' == black
  // return true if All char in line is while
  for (auto c : s)
    if (c == '#') return false;

  return true;
}

std::vector<std::string> RemoveWhiteLine(std::vector<std::string> v) {
  std::vector<std::string> new_v;
  for (auto s : v)
    if (!IsWhiteLine(s)) new_v.push_back(s);

  return new_v;
}

std::vector<std::string> FindWhiteVertical(std::vector<std::string> v, int H, int W) {
  bool flag = false;
  for (int i = 0; i < H; ++i) {
    for (int j = 0; j < W; ++j) {
      flag = false;
      if (v[i][j] == '.') {
        flag = true;
        for (int k = 0; k < H; ++k)
          if (v[i][j] != v[k][j]) flag = false;
      }
      if (flag)
        for (int k = 0; k < H; ++k) v[k][j] = 'w';
    }
  }
  return v;
}

std::vector<std::string> RemoveWhiteVertical(std::vector<std::string> v) {
  std::vector<std::string> new_v;
  std::string line = "";
  for (auto s : v) {
    line = "";
    for (auto c : s)
      if (c != 'w') line += c;
    new_v.push_back(line);
  }
  return new_v;
}
