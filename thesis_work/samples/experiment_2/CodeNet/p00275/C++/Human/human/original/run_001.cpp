#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;
#define INF ( 1 << 30 )
int main(){
  int N;
  queue<char> que;
  while(cin >> N , N){
    int sum[10] = {} , g = 0 , now = 0;
    for(int i = 0 ; i < 100 ; i++ ){
      char c;
      cin >> c;
      que.push(c);
    }
    while(!que.empty()){
      char Top = que.front(); que.pop();
      if(Top == 'M') sum[now]++;
      else if(Top == 'S') g += sum[now] + 1 , sum[now] = 0;
      else sum[now] += g + 1 , g = 0;
      now = ( now + 1 ) % N;
    }
    sort(sum,sum+N);
    for(int i = 0 ; i < N ; i++ ) cout << sum[i] << " ";
    cout << g << endl;
  }
}