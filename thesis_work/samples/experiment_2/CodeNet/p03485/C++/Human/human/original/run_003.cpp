#include <bits/stdc++.h>
using namespace std;

int main(){
  int A,B;
  cin >> A >> B;
  if((A+B)%2 == 0){
    cout << (A+B)/2 << endl;
  }else{
    cout << (A+B+1)/2 << endl;
  }
}