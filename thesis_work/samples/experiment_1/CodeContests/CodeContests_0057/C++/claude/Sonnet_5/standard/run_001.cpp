#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    scanf("%d", &n);
    vector<vector<int>> grid(n, vector<int>(n));
    for (int i=0;i<n;i++){
        char buf[1400];
        scanf("%s", buf);
        int col=0;
        for (int k=0; buf[k]; k++){
            int v;
            char c = buf[k];
            if (c>='0'&&c<='9') v=c-'0'; else v=c-'A'+10;
            for (int b=3;b>=0;b--) grid[i][col++] = (v>>b)&1;
        }
    }
    vector<int> divisors;
    for (int x=1;x<=n;x++) if (n%x==0) divisors.push_back(x);
    sort(divisors.rbegin(), divisors.rend());
    for (int x: divisors) {
        bool ok = true;
        for (int bi=0; bi<n && ok; bi+=x) {
            for (int bj=0; bj<n && ok; bj+=x) {
                int val = grid[bi][bj];
                for (int i=bi;i<bi+x && ok;i++)
                    for (int j=bj;j<bj+x;j++)
                        if (grid[i][j]!=val) { ok=false; break; }
            }
        }
        if (ok) { printf("%d\n", x); return 0; }
    }
    printf("1\n");
    return 0;
}