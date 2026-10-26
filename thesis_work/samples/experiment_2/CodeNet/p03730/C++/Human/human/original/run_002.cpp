    #include <bits/stdc++.h>
    using namespace std;
     
    int main(){
    	int a,b,c;
      	cin >> a >> b >> c;
      	bool x=false;
      	for (int i=1;i<=b;i++){
        	if((a*i)%b==c) x=true;
        }
      	if (x==true) cout << "YES"<<endl;
      	else cout <<"NO"<<endl;
    }