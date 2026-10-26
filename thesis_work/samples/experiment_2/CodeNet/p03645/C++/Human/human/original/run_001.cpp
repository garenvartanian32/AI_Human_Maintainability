#include<iostream>
#include<vector>
using namespace std;

typedef long long ll;

vector<ll > adj[200000];
bool memo[200000];
ll N, M;
const ll WHITE = 0;
const ll BLACK = 1;

bool DFS(ll depth, ll pos){
    memo[pos] = BLACK;
    if(depth==2){
        if(pos==N-1) return true;
        else return false;
    }else{
        bool flag;
        flag = false;
        for(ll i=0; i<adj[pos].size(); i++){
            if(memo[adj[pos][i]]==WHITE){
                flag = DFS(depth+1, adj[pos][i]);
                if(flag) return flag;
            }
        }
        return flag;
    }
}

int main(){
    cin >> N >> M;
    ll a, b;
    for(ll i=0; i<N; i++){
        memo[i] = WHITE;
    }
    for(ll i=0; i<M; i++){
        cin >> a >> b;
        a--; b--;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    if(DFS(0, 0)){
        cout << "POSSIBLE" << endl;
    }else{
        cout << "IMPOSSIBLE" << endl;
    }

}
