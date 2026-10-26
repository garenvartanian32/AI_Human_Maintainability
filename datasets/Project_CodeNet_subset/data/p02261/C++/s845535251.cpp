#include<bits/stdc++.h>
using namespace std;

struct Card {
  char mark, num;
};

void print(struct Card A[], int N){
  for(int i =0; i <N; i++){
    if(i == N-1){
      cout << A[i].mark << A[i].num << endl;
      break;
    }
    cout << A[i].mark << A[i].num << " ";
  }
}

void BubbleSort(struct Card A[],int N){
  for(int i=0; i < N; i++){
    for(int j = N-1; j > 0; j--){
      if(A[j].num < A[j-1].num)swap(A[j],A[j-1]);
    }
  }
}

void SelectionSort(struct Card A[],int N){
  for(int i=0; i <N ;i++){
    int minj = i;
    for(int j = i; j< N; j++){
      if(A[j].num < A[minj].num){
        minj = j;
      }
      
    }
    Card t = A[i];
    A[i] = A[minj];
    A[minj] = t;

  }
}

int main(){
  int N;
  cin >> N;
  Card C1[100];
  Card C2[100];
  for(int i =0; i <N ;i++){
    cin >> C1[i].mark >> C1[i].num;//入力
  }
  for(int i =0; i<N; i++) C2[i] = C1[i]; //コピー
  
  BubbleSort(C1,N);
  print(C1,N);
  cout << "Stable" << endl;
  
  SelectionSort(C2,N);
  print(C2,N);
  
  bool flag = 0;
  for(int i=0; i<N; i++){
    if(C1[i].mark != C2[i].mark){

      flag = 1;
    }
  }
  if(flag){
    cout <<"Not stable"<< endl;
    return 0;
  }
  cout << "Stable"<<endl;
  
}
