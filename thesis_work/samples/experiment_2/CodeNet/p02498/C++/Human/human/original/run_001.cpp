#include<stdio.h>

int main()
{
   char c;
   while( (c=getchar()) != EOF){
      if(c>='a' && c<='z'){
         putchar(c-('a'-'A'));
      }else if(c>='A' && c<='Z'){
         putchar(c+('a'-'A'));
      }else putchar(c);
   }
   return 0;
}