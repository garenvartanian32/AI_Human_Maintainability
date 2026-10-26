#include <iostream>
#include <string>

using namespace std;

int main(void)
{
  int key[100];
  int n;
  string str;
  while(cin>>n,n)
  {
    for(int i = 0;i<n;i++)
      cin>>key[i];
    cin>>str;
    for(int i = 0;i<str.length();i++)
    {
      for(int j = 0;j<key[i%n];j++)
      {  
        str[i] = str[i] - 1;
        if(str[i] < 'a' && str[i]>'Z')
          str[i] = 'Z'+(str[i] - 'a')+1;
        if(str[i] < 'A')
         str[i] = 'z'+(str[i] - 'A')+1;
      }
    }
  
    cout<<str<<endl;
  }
  return 0;
}