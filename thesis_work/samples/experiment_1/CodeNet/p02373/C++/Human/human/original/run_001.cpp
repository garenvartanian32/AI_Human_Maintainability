#include<vector>
#include<algorithm>
#include<iostream>
#include<utility>
#include<climits>
using namespace std;


//初期化O(n log n) 区間最小値クエリO(1)
class SparseTableRMQ{
	int maxlog;
	std::vector<int> a;
	std::vector<char> log;
	std::vector<int> M;
public:
	SparseTableRMQ(){}
	void build(const std::vector<int> &a){
		this->a=a;
		int n=a.size();
		log.resize(n+1);
		log[0]=0;
		for(int i=1,a=0;i<=n;i++){
			if((1<<a+1)<i)a++;
			log[i]=a;
		}
		maxlog=log[n]+1;
		M.resize(n*maxlog);
		for(int i=0;i<n;i++)M[i*maxlog]=i;
		for(int j=1;1<<j<=n;j++){
			for(int i=0;i+(1<<j)-1<n;i++){
				int n=M[i*maxlog+j-1];
				int m=M[(i+(1<<j-1))*maxlog+j-1];
				M[i*maxlog+j] = a[n]<a[m]?n:m;
			}
		}
	}
	int query(int i,int j)const{//[i,j)の最小値
		int k=log[j-i];
		int n=M[i*maxlog+k];
		int m=M[(j-(1<<k))*maxlog+k];
		return a[n]<a[m]?n:m;
	}
};

class LCA{
	vector<int> eulertour;
	vector<int> vertex;
	SparseTableRMQ rmq;
	//RMQ rmq;
	void dfs(const vector<int> edge[],int n,vector<int> &depth,int d,int u){
		eulertour[u]=vertex.size();	
		vertex.push_back(u);
		depth.push_back(d);
		for(int i=0;i<edge[u].size();i++){
			dfs(edge,n,depth,d+1,edge[u][i]);
			vertex.push_back(u);
			depth.push_back(d);
		}
	}
public:
	LCA(const vector<int> edge[],int n,int root):eulertour(n){
		vector<int> depth;
		dfs(edge,n,depth,1,root);
		vertex.push_back(root);
		depth.push_back(0);
		rmq.build(depth);
	}
	int query(int u,int v)const{
		if(u==v)return u;
		if(eulertour[u]>eulertour[v])swap(u,v);
		return vertex[rmq.query(eulertour[u], eulertour[v])];
	}
};


vector<int> edge[100000];
int main(){
	int n;
	cin>>n;
	for(int i=0;i<n;i++){
		int k,v;
		cin>>k;
		for(int j=0;j<k;j++){
			cin>>v;
			edge[i].push_back(v);
		}
	}
	LCA lca(edge,n,0);
	int q,u,v;
	cin>>q;
	for(int i=0;i<q;i++){
		cin>>u>>v;
		cout<<lca.query(u,v)<<endl;
	}
	return 0;
}