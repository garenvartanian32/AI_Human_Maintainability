#include <iostream>
using namespace std;

int A[105];
bool dp[100005];

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N, K;
    cin >> N >> K;
    for (int i = 0; i < N; i++) cin >> A[i];
    for (int i = 1; i <= K; i++)
    {
        for (int j = 0; j < N; j++) if (A[j] <= i && !dp[i - A[j]])
        {
            dp[i] = true;
            break;
        }
    }
    if (dp[K]) cout << "First\n";
    else cout << "Second\n";
    return(0);
}