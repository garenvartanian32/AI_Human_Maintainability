#include <iostream>
#include <string>

using namespace std;

// convert string to lower case
// aBc0_de  ->   abc0_de
string& toLowerCase(string &str){
    for (int i = 0; i < str.length(); i++){
        if ('A' <= str[i] && str[i] <='Z'){
            str[i] = str[i] + ('a'-'A');
        } 
    }
    return str;
}

int main(){
    // input a query
    string query;
    cin >> query;
    toLowerCase(query);
    // search
    string word;
    int cnt = 0;
    while (cin >> word){
        if (word=="END_OF_TEXT")
            break;
        if (toLowerCase(word) == query){
            cnt++;
            continue;
        }
    }
    
    // output the result
    cout << cnt << endl;
    
    return 0;
}