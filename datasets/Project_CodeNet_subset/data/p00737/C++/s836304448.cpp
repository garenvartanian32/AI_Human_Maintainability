#include<iostream>
#include<queue>

using namespace std;

#define MAX_H 30
#define MAX_W 30

#define DIR_UP 0
#define DIR_RIGHT 1
#define DIR_DOWN 2
#define DIR_LEFT 3

#define INST_STRAIGHT 0
#define INST_RIGHT 1
#define INST_BACK 2
#define INST_LEFT 3

#define REP(i,a,e) for(int i = (int)(a); i < (int)(e); ++i)

const int inv = -1;
const int infty = 1<<27;
const int di[] = {0,1,0,-1};
const int dj[] = {-1,0,1,0};

int h,w;
int map[MAX_H][MAX_W];
bool G[MAX_H][MAX_W][MAX_H][MAX_W];
int W[MAX_H][MAX_W][4];
int C[4];

void ResetGraph(){
  h=0;
  w=0;
  C[0]=C[1]=C[2]=C[3]=0;
  REP(i,0,MAX_H){
    REP(j,0,MAX_W){
      REP(k,0,MAX_H){
	REP(l,0,MAX_W){
	  G[i][j][k][l] = false;
	}
      }
      map[i][j] = inv;
      REP(k,0,4)
	W[i][j][k]=infty;
    }
  }
}

bool isInside(int i, int j){
  return 0<=i&&i<h&&0<=j&&j<w;
}

struct state{
  int i,j;
  int dir;
  int cost;
  bool operator<(const state &t)const{
    return cost > t.cost;
  }
};

bool isGoal(int i, int j){ return i==h-1&&j==w-1; }

pair<int,int> proceed(int i, int j, int dir){
  switch(dir){
  case DIR_UP: return make_pair(i-1,j);
  case DIR_RIGHT: return make_pair(i,j+1);
  case DIR_DOWN : return make_pair(i+1,j);
  case DIR_LEFT: return make_pair(i,j-1);
  }
  return make_pair(-1,-1);
}

int Dijkstra(){
  priority_queue< state > pq;
  state st;

  st.i = 0;
  st.j = 0;
  st.dir = DIR_RIGHT;
  st.cost = 0;

  pq.push(st);
  while(!pq.empty()){
    st = pq.top();
    pq.pop();

    if( W[st.i][st.j][st.dir] <= st.cost ) continue;
    if( W[st.i][st.j][st.dir] > st.cost ) W[st.i][st.j][st.dir] = st.cost;
    if( isGoal( st.i, st.j ) ) return st.cost;
    // if( map[st.i][st.j] == 4 ) continue;

    // cout << "I:"<<st.i << " J:"<<st.j<<" DIR : " << st.dir << " COST : " << st.cost<<endl;

    for(int d=0;d<4;++d){
      state next=st;
      next.dir = (st.dir+d)%4;
      pair<int,int> np = proceed( st.i, st.j, next.dir );
      
      // cout << " NEXT : " << np.first << " , " << np.second << endl;
      if( isInside( np.first, np.second ) ){
	next.i = np.first;
	next.j = np.second;
	
	if( d != map[st.i][st.j] )
	  next.cost += C[ d ];
	pq.push( next );
      }
    }
  }
  return -1;
}

int main(){

  while(true){
    ResetGraph();

    cin >> w >> h;
    if(w==0&&h==0)break;

    REP(i,0,h){
      REP(j,0,w){
	cin >> map[i][j];
      }
    }

    cin >> C[0] >> C[1] >> C[2] >> C[3];

    cout << Dijkstra() << endl;

    //break;
  }
  return 0;
}