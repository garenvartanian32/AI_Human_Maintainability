#define scanf_s scanf
//#define gets_s gets
#include <stdio.h>
#include <string>
#include <iostream>
#include <math.h>
using namespace std;
#define MIN -1
#define MAX 1000000
int main(void)
{
	char a, b, x = 'A';
	while (scanf_s("%c,%c",&a,&b) != EOF) {
		if (a == x) x = b;
		else if (b == x) x = a;
	}
	printf("%c\n", x);
}