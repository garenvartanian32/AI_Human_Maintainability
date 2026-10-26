#include <iostream>
#include <cctype>
using namespace std;

int main() {
char c,d;
while(1){
cin.get(c);
if(c=='\n')break;
if(islower(c)){
	d=toupper(c);
	cout<<d;
}
else if(isupper(c)){
	cout<<(char)tolower(c);
}
else{
	cout<<(char)c;
}

}cout<<endl;
	return 0;
}