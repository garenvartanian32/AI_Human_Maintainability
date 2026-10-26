#include <bits/stdc++.h>
#include <iostream>
#include <string>
#include <algorithm>
#include <math.h>
using namespace std;
typedef long long ll;


int main(){
	
	int m,f,b;
	cin>>m>>f>>b;
	if(b-m<=0){
		cout<<0<<endl;
	}
	if(b-m>0 && b-m<=f){
		cout<<b-m<<endl;
	}
	if(b-m>0 && b-m>f){
		cout<<"NA"<<endl;
	}
	/*int a,b;
	cin>>a>>b;
	cout<<(a+b)/2<<endl;*/
	
	/*random_device rnd;
	mt19937 mt(rnd());
	uniform_int_distribution<> rnd3(1, 3);
	
	string p1, p2, p3, p4;
	cin >> p1 >> p2 >> p3 >> p4;
	
	int t = rnd()%3;
	
	if(t == 0){
		cout << "1:" << p1 << ',' << p2 << endl;
		cout << "2:" << p3 << ',' << p4 << endl;
	}
	if(t == 1){
		cout << "1:" << p1 << ',' << p3 << endl;
		cout << "2:" << p2 << ',' << p4 << endl;
	}
	if(t == 2){
		cout << "1:" << p1 << ',' << p4 << endl;
		cout << "2:" << p2 << ',' << p3 << endl;
	}*/
	
	/*
	switch(type){
		case 0:
			cout << "1:" << p1 << ',' << p2 << endl;
			cout << "2:" << p3 << ',' << p4 << endl;
			break;
		case 1:
			cout << "1:" << p1 << ',' << p3 << endl;
			cout << "2:" << p2 << ',' << p4 << endl;
			break;
		case 2:
			cout << "1:" << p1 << ',' << p4 << endl;
			cout << "2:" << p2 << ',' << p3 << endl;
			break;
	}
	*/
	
	
	return 0;
}

