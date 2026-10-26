#include <stdio.h>
#include <cmath>
#include <algorithm>
#include <cfloat>
#include <stack>
#include <queue>
#include <vector>
#include <string>
#include <iostream>
#include <set>
#include <map>
#include <time.h>
typedef long long int ll;
typedef unsigned long long int ull;
#define BIG_NUM 2000000000
#define MOD 1000000007
#define EPS 0.000000001
using namespace std;

struct Info{
	char color;
	int num;
};

struct Data{
	Data(int arg_row,int arg_col){
		row = arg_row;
		col = arg_col;
	}
	int row,col;
};


Info info[12][6];
char table[12][7];
bool check[12][6];
int diff_row[4] = {-1,0,0,1},diff_col[4] = {0,-1,1,0};

bool rangeCheck(int row,int col){
	if(row >= 0 && row <= 11 && col >= 0 && col <= 5)return true;
	else{
		return false;
	}
}

void func(){

	for(int row = 11; row >= 0; row--){
		scanf("%s",table[row]);
		for(int col = 0; col < 6; col++){
			info[row][col].color = table[row][col];
			info[row][col].num = -1;
		}
	}

	bool FLG;
	int chain_num = 0;
	int adj_row,adj_col;
	queue<Data> Q;
	vector<Data> V;

	while(true){

		for(int row = 0; row < 12; row++){
			for(int col = 0; col < 6; col++)check[row][col] = false;
		}

		for(int row = 0; row < 12; row++){
			for(int col = 0; col < 6; col++){
				if(info[row][col].color == '.' || info[row][col].color == 'O' || check[row][col] == true)continue;
				V.clear();

				check[row][col] = true;

				Q.push(Data(row,col));
				V.push_back(Data(row,col));

				while(!Q.empty()){

					for(int i = 0; i < 4; i++){
						adj_row = Q.front().row+diff_row[i];
						adj_col = Q.front().col+diff_col[i];

						if(rangeCheck(adj_row,adj_col) == false || check[adj_row][adj_col] == true)continue;

						if(info[adj_row][adj_col].color == info[row][col].color){
							check[adj_row][adj_col] = true;
							V.push_back(Data(adj_row,adj_col));
							Q.push(Data(adj_row,adj_col));
						}

					}
					Q.pop();
				}

				if(V.size() >= 4){
					for(int i = 0; i < V.size(); i++){
						info[V[i].row][V[i].col].num = (int)V.size();
					}
				}
			}
		}

		FLG = false;

		for(int row = 0; row < 12; row++){
			for(int col = 0; col < 6; col++){
				if(info[row][col].color != 'O')continue;

				for(int i = 0; i < 4; i++){
					adj_row = row+diff_row[i];
					adj_col = col+diff_col[i];

					if(!rangeCheck(adj_row,adj_col))continue;

					if(info[adj_row][adj_col].num >= 4){
						FLG = true;
						info[row][col].color = '.';
						break;
					}
				}
			}
		}

		for(int row = 0; row < 12; row++){
			for(int col = 0; col < 6; col++){
				if(info[row][col].num >= 4){
					info[row][col].color = '.';
					FLG = true;
				}
			}
		}

		if(!FLG)break;

		chain_num++;

		int tmp_row;

		for(int col = 0; col < 6; col++){
			for(int row = 0; row < 11; row++){
				if(info[row][col].color == '.'){
					tmp_row = row+1;
					while(tmp_row <= 11 && info[tmp_row][col].color == '.')tmp_row++;

					if(tmp_row == 12)break;

					info[row][col].color = info[tmp_row][col].color;
					info[tmp_row][col].color = '.';
				}
			}
		}

		for(int row = 0; row < 12; row++){
			for(int col = 0; col < 6; col++){
				info[row][col].num = -1;
				check[row][col] = false;
			}
		}
	}

	printf("%d\n",chain_num);
}


int main(){

	int N;
	scanf("%d",&N);

	for(int i = 0; i < N; i++)func();

	return 0;
}