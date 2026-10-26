#include <bits/stdc++.h>

using namespace std;

int main(void) {
  int n;
  while (cin >> n, n) {
    vector<string> a(n);
    for (int i = 0; i < n; i++) {
      cin >> a[i];
    }

    int minx = -1e9 - 2, maxx = 1e9 + 2;
    bool none = false;
    for (int i = 0; i < n; i++) {
      if (a[i] == "x") {
        if ((i > 0 && a[i - 1] == "x") || (i < n - 1 && a[i + 1] == "x")) {
          none = true;
          break;
        } else if (i & 1) {
          if (i > 0) minx = max(minx, atoi(a[i - 1].c_str()) + 1);
          if (i < n - 1) minx = max(minx, atoi(a[i + 1].c_str()) + 1);
        } else {
          if (i > 0) maxx = min(maxx, atoi(a[i - 1].c_str()) - 1);
          if (i < n - 1) maxx = min(maxx, atoi(a[i + 1].c_str()) - 1);
        }
      } else if (i & 1) {
        if (i > 0 && a[i - 1] != "x" && atoi(a[i - 1].c_str()) >= atoi(a[i].c_str())) {
          none = true;
        } else if (i < n - 1 && a[i + 1] != "x" && atoi(a[i].c_str()) <= atoi(a[i + 1].c_str())) {
          none = true;
        }
        if (none) break;
      } else {
        if (i > 0 && a[i - 1] != "x" && atoi(a[i - 1].c_str()) <= atoi(a[i].c_str())) {
          none = true;
        } else if (i < n - 1 && a[i + 1] != "x" && atoi(a[i].c_str()) >= atoi(a[i + 1].c_str())) {
          none = true;
        }
        if (none) break;
      }
    }

    if (none || minx - maxx > 0) {
      cout << "none\n";
    } else if (minx == maxx) {
      cout << minx << endl;
    } else {
      cout << "ambiguous\n";
    }
  }

  return 0;
}
