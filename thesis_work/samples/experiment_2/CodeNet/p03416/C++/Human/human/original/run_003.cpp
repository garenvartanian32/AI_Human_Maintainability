#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <algorithm>

typedef long long ll;

int main(int argc, char* argv[])
{
  int a, b;
  int cnt = 0;

  std::cin >> a;
  std::cin >> b;

  for (int i = a; i <= b; i++) {
    bool judge = true;
    char c_str[10];
    sprintf(c_str, "%d", i);
    std::string str(c_str);
    
    //std::cout << str << " " << str.size() << std::endl;
    for (int j = 0; j < str.size(); j++) {
      if (str[j] != str[str.size() - j - 1]) {
	judge = false;
      }
    }

    if (judge) {
      cnt++;
    }
  }
  std::cout << cnt << std::endl;
  
  return 0;
}

