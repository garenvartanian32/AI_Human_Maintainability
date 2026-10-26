#include <iostream>
#include <vector>
using namespace std;

using ll = long long;

int main() {

    int n, q;

    cin >> n >> q;

    vector<ll> v(n);

    for(int i = 0; i < n; ++i)
        cin >> v[i];


    for(int i = 0; i < q; ++i) {
        
        ll num;

        cin >> num;

        int right;
        right = 0;

        ll sum = 0;
        ll result = 0;

        for(int left = 0; left < n; ++left) {

            while(right < n && sum + v[right] <= num) {
                sum += v[right];
                ++right;
            }


            result += right - left;

            if(left == right)
                ++right;
            else 
                sum -= v[left];


        }

        cout << result << endl;


    }

    return 0;


}
