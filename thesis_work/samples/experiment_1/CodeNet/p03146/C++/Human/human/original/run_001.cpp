#include <bits/stdc++.h>
using namespace std;

int main(){
    int s;
    cin >> s;
    int count = 1;
    int count_2 = 0;
    vector<int> container(0);
    for (int i = 0; i < 1000000; i++){
        container.push_back(s);
        count++;
        if (s % 2 == 0){
            s = s/2;
        }
        else if(s % 2 == 1){
            s = 3 * s + 1;
        }
        for (int j = 0; j < i; j++){
            if(container.at(j) == s){
                count_2++;
            }
        }
        if (count_2 >= 1){
            cout << count << endl;
            break;
        }
    }
}
