#include <iostream>
#include <string>
using namespace std;

int main(void){
string str;
string ret = "";
getline(cin, str);
for (int i = 0; i < str.length(); i++){
if ('A' <= str[i] && str[i] <= 'Z'){
ret += str[i] + 32;
}
else if ('a' <= str[i] && str[i] <= 'z'){
ret += str[i] - 32;
}
else ret += str[i];
}
cout << ret << endl;
return 0;
}