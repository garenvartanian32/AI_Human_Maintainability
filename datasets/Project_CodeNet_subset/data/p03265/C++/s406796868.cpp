#include <iostream>

using namespace std;

int main(){
    int x1,y1,x2,y2;
    int x3,y3,x4,y4;
    cin >> x1 >> y1 >> x2 >> y2;
    
    int dx = x2 - x1, dy = y2 - y1;
    int x = x2, y = y2;
 
    for(int i = 0; i < 2; i++){
        int _dx = -dy;
        int _dy = dx;
        dx = _dx;
        dy = _dy;
 
        x = x + dx;
        y = y + dy;
 
        cout << x << " " << y;
        if (i == 0) printf(" ");
        else printf("\n");
    }
}