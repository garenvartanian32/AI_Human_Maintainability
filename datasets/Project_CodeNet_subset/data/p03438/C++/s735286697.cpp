#include <iostream>
using namespace std;
int main(void){
    // Your code here!
    int n;
    cin>>n;
    long long ans=0;
    int a[n],b[n];
    for(int i=0;i<n;i++){
        cin>>a[i];
    }
    for(int i=0;i<n;i++){
        cin>>b[i];
    }
    for(int i=0;i<n;i++){
        if(b[i]>a[i]){
            ans+=(b[i]-a[i])/2;
        }
        else ans+=(b[i]-a[i]);
    }
    if(ans>=0)cout<<"Yes"<<endl;
    else cout<<"No"<<endl;
}
