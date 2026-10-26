#include <bits/stdc++.h>
#define rep(i, a, n) for(int i = a; i < n; i++)
#define repb(i, a, b) for(int i = a; i >= b; i--)
#define all(a) a.begin(), a.end()
#define o(a) cout << a << endl
#define int long long
#define first fi
#define second se
using namespace std;
typedef pair<int, int> P;

int dy[8] = {1, 1, 1, 0, 0, -1, -1, -1};
int dx[8] = {1, 0, -1, 1, -1, 1, -1, 0};

signed main(){
    int h, w;
    while(1){
        cin >> h >> w;
        if(h + w == 0) break;
        string s[11];
        rep(i, 0, h) cin >> s[i];
        string ans = "";
        set<string> st;
        rep(i, 0, h){
            rep(j, 0, w){
                rep(k, 0, 8){
                    int y = i;
                    int x = j;
                    string now = "";
                    now += s[i][j];                    
                    for(int l = 1;; l++){
                        y = (y + dy[k] + h) % h;
                        x = (x + dx[k] + w) % w;
                        if(y == i && x == j) break;
                        now += s[y][x];
                        if(!st.count(now)) st.insert(now);
                        else{
                            if(ans.size() == now.size()){
                                if(ans > now) ans = now;
                            }else if(ans.size() < now.size()){
                                ans = now;
                            }
                        }
                    }
                    // cout << i << " " << j << " " << k << " " << now << endl;
                }
            }
        }
        if(ans == "") cout << "0" << endl;
        else cout << ans << endl;
    }
}