#include<cstdio>
using namespace std;
static const int MAX_N = 100000;

int n, q;

int n_;
int dat[4 * MAX_N], laz[4 * MAX_N];

void init(){
	n_ = 1;
	while(n_ < n) n_ *= 2;
	for(int i = 0; i < 2 * n_ - 1; i++){
		dat[i] = 0;
		laz[i] = -1001;
	}
}
void prop(int k){
	if(laz[k] == -1001) return;
	if(2 * k + 1 < 2 * n_ - 1){
		laz[2 * k + 1] = laz[k] / 2;
		laz[2 * k + 2] = laz[k] / 2;
	}
	dat[k] = laz[k];
	laz[k] = -1001;
}
void update(int a, int b, int x, int k, int l, int r){
	prop(k);
	if(r <= a || b <= l) return;
	if(a <= l && r <= b){
		laz[k] = x * (r - l);
		return;
	}
	prop(k);
	update(a, b, x, 2 * k + 1, l, (l + r) / 2);
	update(a, b, x, 2 * k + 2, (l + r) / 2, r);
	int vl = (laz[2 * k + 1] == -1001 ? dat[2 * k + 1] : laz[2 * k + 1]);
	int vr = (laz[2 * k + 2] == -1001 ? dat[2 * k + 2] : laz[2 * k + 2]);
	dat[k] = vl + vr;
}
int find(int a, int b, int k, int l, int r){
	prop(k);
	if(r <= a || b <= l) return 0;
	if(a <= l && r <= b) return dat[k];
	int vl = find(a, b, 2 * k + 1, l, (l + r) / 2);
	int vr = find(a, b, 2 * k + 2, (l + r) / 2, r);
	return vl + vr;
}

int main(){
	scanf("%d %d", &n, &q);
	init();
	for(int i = 0; i < q; i++){
		int op;
		scanf("%d", &op);
		if(op == 0){
			int s, t, x;
			scanf("%d %d %d", &s, &t, &x);
			update(s, t + 1, x, 0, 0, n_);
		}else{
			int s,t;
			scanf("%d %d", &s, &t);
			printf("%d\n", find(s, t + 1, 0, 0, n_));
		}
	}
	return 0;
}

