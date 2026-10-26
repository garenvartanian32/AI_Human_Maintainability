#include<iostream>
#include<string>
#include<vector>
#include<queue>
#include<map>
#include<set>
#include<cmath>
#include<cstring>
#include<cstdio>
#include<algorithm>

using namespace std;

int main(){
        int h,w;
        cin>>h>>w;
        int hh[100] = {0};
        int ww[100] = {0};
        string mp[100];
        for(int i=0;i<h;i++)cin>>mp[i];

        string ht = "";
        string wt = "";

        for(int i=0;i<h;i++)ht += ".";
        for(int i=0;i<w;i++)wt += ".";

        for(int i=0;i<h;i++){
                if(wt == mp[i]) hh[i] = 1;
        }

        for(int i=0;i<w;i++){
                string tmp = "";
                for(int j=0;j<h;j++)tmp += mp[j][i];
                if(ht == tmp) ww[i] = 1;
        }

        string ans[100];
        int hcount =0;
        for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                        if(hh[i] == 0){
                                if(ww[j] ==0) ans[hcount]+=mp[i][j];
                        }
                }
                if(hh[i] ==0) hcount++;
        }

        for(int i=0;i<hcount;i++){
                cout<<ans[i]<<endl;
        }

        return 0;
}
