#include<bits/stdc++.h>

using namespace std;

int main(void){
    int N,M,X,Y;
    cin>>N>>M>>X>>Y;
    vector<int> x(N),y(M);
    for(int i=0;i<N;i++){
        cin>>x[i];
    }
    for(int i=0;i<M;i++){
        cin>>y[i];
    }
    sort(x.begin(),x.end());
    sort(y.begin(),y.end());
    if(x[N-1]>=X&&y[0]<=Y&&x[N-1]<y[0]){
        cout<<"No War"<<endl;
    }else{
        cout<<"War"<<endl;
    }
    return 0;
}

