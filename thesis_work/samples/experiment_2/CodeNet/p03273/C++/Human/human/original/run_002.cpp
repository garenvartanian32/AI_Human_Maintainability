#include <bits/stdc++.h>
using namespace std;

#define int long long
const double PI = 3.14159265358979323846;
typedef vector<int> vint;
typedef pair<int, int> pint;
int dx[8] = {1, 0, -1, 0, 1, -1, -1, 1};
int dy[8] = {0, 1, 0, -1, 1, 1, -1, -1};

int H, W;
string a[110];
bool tate[110], yoko[110];

signed main() {
    cin >> H >> W;
    for (int i = 0; i < H; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
            tate[j] |= a[i][j] == '#';
            yoko[i] |= a[i][j] == '#';
        }
    }
    for (int i = 0; i < H; i++) {
        if (!yoko[i])continue;
        for (int j = 0; j < W; j++) {
            if (tate[j])cout << a[i][j];
        }
        cout << endl;
    }

}