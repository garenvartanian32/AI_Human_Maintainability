#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;

long long int kansu( long long int x, long long int y ) {

  if ( x < y ) swap( x, y );

  while( y != 0 ) {

    x = x % y;
    swap( x, y );

  }

  return x;

}

int main() {

  long long int input;

  long long int n;

  while( true ) {

    cin >> n;

    if ( n == 0 ) break;

    vector< long long int > x;
    vector< long long int > y;

    for ( int i = 0; i < n * ( n + 1 ) / 2; i++ ) {

      cin >> input;

      if ( input % 2 == 0 ) {

	x.push_back( input );

      }else {

	y.push_back( input );

      }

    }

    sort( x.begin(), x.end() );
    sort( y.begin(), y.end() );

    long long int k = kansu( x[0], y[0] );

    long long int g = (long long int) sqrt( x[0] / k * ( x[1] / ( y[0] / k ) ) );

    cout << g << endl;

    for ( int i = 0; i < n; i++ ) {

      if ( i != 0 ) cout << " ";

      cout << x[i] / g;

    }

    cout << endl;

  }

  return 0;

}