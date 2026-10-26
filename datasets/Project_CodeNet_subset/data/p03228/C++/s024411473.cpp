#include <stdio.h>
#include <math.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <numeric>
#include <string>
using namespace std;
typedef long long int lli;
#define urept(soeji, start, n) for (int soeji = start; soeji < n; soeji++)
#define drept(soeji, start, n) for (int soeji = start; soeji > n; soeji--)
int main(void)
{
    int A, B, K;
    cin >> A >> B >> K;
    for (int i = 0; i < K; i++)
    {
        if (A % 2 == 1 && i % 2 == 0)
        {
            A -= 1;
            B += A / 2;
          	A /= 2;
        }
        else if (A%2==0 && i % 2 == 0)
        {
            B += A / 2;
          	A /= 2;
        }
        else if (B % 2 == 1 && i % 2 == 1)
        {
            B -= 1;
            A += B / 2;
          	B /= 2;
        }
        else if (B%2==0 && i % 2 == 1)
        {
            A += B / 2;
          	B /= 2;
        }
    }
    cout << A << " " << B << endl;
    return 0;
}