#include<iostream>
#include<algorithm>
using lint=int64_t;
using namespace std;

int main()
{
	int N,C;
	int D[35][35]={};
	int cnt[3][35]={};

	cin >> N >> C;
	for(int i=1;i<=C;i++)
		for(int j=1;j<=C;j++)
			cin >> D[i][j];
	for(int i=1;i<=N;i++)
	{
		for(int j=1;j<=N;j++)
		{
			int c;
			cin >> c;
			cnt[(i+j)%3][c]++;
		}
	}

	int ans=1e9;
	int col[3]={};
	for(col[0]=1;col[0]<=C;col[0]++)
	{
		for(col[1]=1;col[1]<=C;col[1]++)
		{
			for(col[2]=1;col[2]<=C;col[2]++)
			{
				if(col[0]==col[1])continue;
				if(col[1]==col[2])continue;
				if(col[2]==col[0])continue;

				int tans=0;
				for(int i=0;i<3;i++)
					for(int j=1;j<=C;j++)
						tans+=D[j][col[i]]*cnt[i][j];

				ans=min(ans,tans);
			}
		}
	}

	cout << ans << endl;
	return 0;
}
