#include <iostream>
using namespace std;
struct trump{
    char kind;
    int num;
};
int main(void){
    int n,min,flag=0;
    cin>>n;
    struct trump TRUMP1[n],TRUMP2[n],temp;
    for(int i=0;i<n;i++){
        cin>>TRUMP1[i].kind>>TRUMP1[i].num;
        TRUMP2[i]=TRUMP1[i];
    }
    for(int i=0;i<n-1;i++){
        for(int j=n-2;j>=0;j--){
            if(TRUMP1[j].num>TRUMP1[j+1].num){
                temp=TRUMP1[j];
                TRUMP1[j]=TRUMP1[j+1];
                TRUMP1[j+1]=temp;
            }
        }
    }
    for(int i=0;i<n;i++){
        cout<<TRUMP1[i].kind<<TRUMP1[i].num;
        if(i<n-1)cout<<' ';
    }
    cout<<endl;
    if(flag==0)cout<<"Stable"<<endl;
    
    
    for(int i=0;i<n;i++){
        min=i;
        for(int j=i;j<n;j++){
            if(TRUMP2[min].num>TRUMP2[j].num){
                min=j;
            }
        }
        temp=TRUMP2[i];
        TRUMP2[i]=TRUMP2[min];
        TRUMP2[min]=temp;
        for(int j=i+1;j<min;j++){
            if(TRUMP2[min].num==TRUMP2[j].num){
                flag=1;
            }
        }
    }
    for(int i=0;i<n;i++){
        cout<<TRUMP2[i].kind<<TRUMP2[i].num;
        if(i<n-1)cout<<' ';
    }
    cout<<endl;
    if(flag==0)cout<<"Stable"<<endl;
    else cout<<"Not stable"<<endl;
    return 0;
}

