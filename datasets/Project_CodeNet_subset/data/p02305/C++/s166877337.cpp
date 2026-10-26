#include<iostream>
using namespace std;

int dist(const int& x1, const int& y1, const int& x2, const int& y2){
    return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
}
int main(){
    int cx1, cy1, r1, cx2, cy2, r2;
    cin >> cx1 >> cy1 >> r1 >> cx2 >> cy2 >> r2;
    int rs = (r1+r2)*(r1+r2);
    int rd = (r1-r2)*(r1-r2);
    int d = dist(cx1, cy1, cx2, cy2);
    if(d > rs) cout << 4 << endl;
    else if(d == rs) cout << 3 << endl;
    else if(d < rs && d > rd) cout << 2 << endl;
    else if(d == rd) cout << 1 << endl;
    else cout << 0 << endl;
}
