#include<bits/stdc++.h>

int main(){
    using namespace std;
    unsigned long N;
    cin >> N;
    vector<long> prime, sieve(31622, 1);
    for(long i = 2; i < 31622; ++i)
        if(sieve[i]){
            prime.push_back(i);
            for(auto j = i * i; j < 31622; j += i)sieve[j] = 0;
        }

    vector<long> a(N);
    long C;
    for(auto& i : a)cin >> i;
    cin >> C;
    long u{C};
    for(const auto i : a)u = __gcd(u, abs(i));

    vector<long> ans;
    for(const auto p : prime)
        if(u % p == 0 && (ans.push_back(p), 1))
            while(u % p == 0)u /= p;
        else if(C % p == 0 && p <= N){
            vector<long> v(p);
            for(unsigned long j = 0, k = 0; j < N; ++j, k = k == p - 2 ? 0 : ++k)v[k] += a[j];
            if(none_of(v.begin(), v.end(), [&p](auto x){return x % p;}))ans.push_back(p);
        }
    for(const auto i : ans)cout << i << endl;
    if(u ^ 1)cout << u << endl;
}