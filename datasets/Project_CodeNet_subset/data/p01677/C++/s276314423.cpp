#include<iostream>
#include<string>
#include<cstdlib>
#include<vector>
using namespace std;

int main(){
  const int Max=(1<<30);
  const int Min=(1<<31);
  const int NAN=Max+1;
  int N;
  while(cin>>N,N){
    int max=Min;
    int min=Max;
    vector<string> vec(N);
    for(int i=0;i<N;i++){
      cin>>vec[i];
    }
    bool flag=true;
    for(int i=0;i<N;i++){
      if(vec[i]=="x"){
	if((i>=1 && vec[i-1]=="x") || (i+1<N && vec[i+1]=="x")){
	  flag=false;
	}else{
	  int b=NAN;
	  int a=NAN;
	  if(i>=1) b=atoi(vec[i-1].c_str());
	  if(i+1<N) a=atoi(vec[i+1].c_str());
	  if(i&1){
	    if(b==NAN) b=Min;
	    if(a==NAN) a=Min;
	    if(max<b) max=b;
	    if(max<a) max=a;
	  }else{
	    if(b==NAN) b=Max;
	    if(a==NAN) a=Max;
	    if(min>b) min=b;
	    if(min>a) min=a;
	  }
	}
      }else{
	int b=NAN;
	int c=atoi(vec[i].c_str());
	int a=NAN;
	if(i>=1 && vec[i-1]!="x")
	  b=atoi(vec[i-1].c_str());
	if(i+1<N && vec[i+1]!="x")
	  a=atoi(vec[i+1].c_str());
	if(i&1){
	  if(b==NAN) b=Min;
	  if(a==NAN) a=Min;
	  if(b>=c || a>=c) flag=false;	  
	}else{
	  if(b==NAN) b=Max;
	  if(a==NAN) a=Max;
	  if(b<=c || a<=c) flag=false;	  
	}
      }
    }
    int ch=(min-max-1)*flag;
    if(ch==0 || max>=min){
      cout<<"none"<<endl;
    }else if(ch==1){
      cout<<min-1<<endl;
    }else{
      cout<<"ambiguous"<<endl;
    }
  }
  return 0;
}