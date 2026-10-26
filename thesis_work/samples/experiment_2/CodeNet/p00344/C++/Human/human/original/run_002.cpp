#include<bits/stdc++.h>
#define rep(i,n)for(int i=0;i<n;i++)
using namespace std;

int n;
int a[100000], in[100000];
bool used[100000];

int main() {
	scanf("%d", &n);
	rep(i, n) {
		scanf("%d", &a[i]);
		a[i] = (i + a[i]) % n;
		in[a[i]]++;
	}
	int cnt = 0;
	queue<int>que;
	rep(i, n) {
		if (!in[i] && !used[i]) {
			used[i] = true;
			que.push(i);
		}
		while (!que.empty()) {
			int v = que.front(); que.pop();
			cnt++;
			in[a[v]]--;
			if (!used[a[v]] && !in[a[v]]) {
				used[a[v]] = true;
				que.push(a[v]);
			}
		}
	}
	printf("%d\n", n - cnt);
}