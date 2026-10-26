#include <iostream>
#include <algorithm>
using namespace std;
struct apples{
	int val;
	char colour;
};
bool comp(const apples & a, const apples & b)
{
	if(a.val > b.val)
		return 1;
	return 0;
}
int main()
{
	int x, y, a, b, c;
	cin >> x >> y >> a >> b >> c;
	
	apples arr[a+b+c];
	for(int i = 0; i < a; i++)
	{
		cin >> arr[i].val;
		arr[i].colour = 'r';
	}
	for(int i = a; i < a+b; i++)
	{
		cin >> arr[i].val;
		arr[i].colour = 'g';
	}
	for(int i = a+b; i < a+b+c; i++)
	{
		cin >> arr[i].val;
		arr[i].colour = 'c';
	}
	
	int n = a+b+c, sum_elem = 0, end = x+y;
	long long sum = 0;
	sort(arr,arr+n,comp);
	
	for(int i = 0; sum_elem < end; i++)
	{
		if( arr[i].colour == 'r' ){
			if( x > 0 )
			{
				x--;
				sum += arr[i].val;
				sum_elem++;
			}
		}else if( arr[i].colour == 'g' ){
			if( y > 0 )
			{
				y--;
				sum += arr[i].val;
				sum_elem++;
			}
		}else if( arr[i].colour == 'c' ){
			if( c > 0 )
			{
				c--;
				sum += arr[i].val;
				sum_elem++;
			}
		}
	}
	cout << sum;
}