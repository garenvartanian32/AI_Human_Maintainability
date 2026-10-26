#include <bits/stdc++.h>
using namespace std;

int main() {
  string r,s,t;
  cin >> r >> s >>t;
  	int n= s.size();
   char a='a';
  
  if(s.at(0)<a){
    if(n>=1 && n<=100) {
  		if(n==1){
          cout<<r.at(0)<<s.at(0)<<t.at(0)<<endl;
        }
        for(int i=1; i<n; i++){
   			if(s.at(i)<a){
    				break;
  				}
         if(i<(n-1)){ 
           continue;
         }
        
         cout << r.at(0) << s.at(0) << t.at(0) << endl;
        
        }
  }
 
}
}



