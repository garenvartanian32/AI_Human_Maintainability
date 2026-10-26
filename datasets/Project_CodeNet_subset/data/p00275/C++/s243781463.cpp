#include <bits/stdc++.h>
using namespace std;
const double eps=1e-9;
typedef vector<int> vi;
typedef long long ll;
typedef pair<int,int> pii;
int N;
string s;
int person[15];
int field;
int nowturn;
int main(void)
{
  while(cin >> N && N)
    {
      cin >> s;
      field=0;
      nowturn=0;
      memset(person,0,sizeof(person));
      for(int i=0;i<100;i++)
	{
	  person[nowturn]++;
	  if(s[i]=='S')
	    {
	      field+=person[nowturn];
	      person[nowturn]=0;
	    }
	  else if(s[i]=='L')
	    {
	      person[nowturn]+=field;
	      field=0;
	    }
	  nowturn=(nowturn+1)%N;
	}
      sort(person,person+N);
      for(int i=0;i<N;i++)
	{
	  cout << person[i] << ' ';
	}
      cout << field << endl;
    }
  return 0;
}

