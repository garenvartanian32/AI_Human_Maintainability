#include<bits/stdc++.h>
using namespace std;

int main()
{
  int a, b, c;
  while( cin >> a >> b >> c ) {
    if( a == 0 && b == 0 && c == 0 ) break;
    int p = 2*a, r = 2*a;
    int q = -(-b+(sqrt((double)(b*b-4*a*c))));
    int s = -(-b-(sqrt((double)(b*b-4*a*c))));
    //int tmp = (int)sqrt((double)(b*b-4*a*c));
    //cout << b*b-4*a*c << endl;
    //cout << sqrt((double)b*b-4*a*c) << endl;
    //cout << q << " " << s << endl;
    int tmp = (int)sqrt((double)(b*b-4*a*c));
    //cout << tmp << endl;
    if( abs(sqrt((double)(b*b-4*a*c)) - (double)tmp) != 0 || b*b-4*a*c < 0 ) {
      cout << "Impossible" << endl;
    } else { 
      //cout << sqrt((double)(b*b-4*a*c)) << endl;
      //cout << q << " " << s << endl;
      for(int i=100000; i>1; i--) {
	if( !(p % i) && !(q % i) ) {
	  p /= i; q /= i;
	}
	if( !(r % i) && !(s % i) ) {
	  r /= i; s /= i;
	}
	
	
      }

      if( p < r ) {
	swap(p, r);
	swap(q, s);
      }
      if( p == r ) {
	if( q < s ) {
	  swap(q, s);
	}
      }
      cout << p << " " << q << " " << r << " " << s << endl;
    }
  }
}
  