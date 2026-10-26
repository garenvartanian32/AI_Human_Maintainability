#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

#define REP(i, n) for(int i = 0; i < (int)(n); ++i)
#define FOR(i, m, n) for(int i = (int)(m); i < (int)(n); ++i)


int main()
{
    long long a, b;
    cin >> a >> b;

    if(a <= 0 and b >= 0) cout << "Zero" << endl;
    else if(b < 0 and (b-a) % 2 == 0) cout << "Negative" << endl;
    else cout << "Positive" << endl;

    return 0;
}