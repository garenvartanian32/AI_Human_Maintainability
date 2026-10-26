#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int n, a[100000], v[100000];

int main() {
    cin >> n;
    for (int i = 0; i < n; ++i)
        cin >> a[i];
    
    for (int i = 0; i < n; ++i) {
        if (v[i] == 0) {
            int t = i;
            queue<int> q;
            while (1) {
                if (v[t] == 1) { //?????????
                    while (!q.empty()) {
                        if (q.front() != t)
                            v[q.front()] = 2;
                        else
                            break;
                        q.pop();
                    }
                    break;
                }
                
                v[t] = 1;
                q.push(t);
                
                (t += a[t]) %= n;
            }
        }
    }
    
    cout << count(v, v + n, 1) << endl;
    
    return 0;
}