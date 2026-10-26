
#include <stdio.h>
#include <string.h>
#define MAX (999)

int main(int argc, const char * argv[])
{
    char W[MAX],T[MAX];
    int count=0;
    scanf("%s",W);
    
    while(1){
        scanf("%s",T);
        
        if(strcmp("END_OF_TEXT",T)==0){
            break;
        }
        
        for(int i=0;;i++){
            if('A'<= T[i] && T[i]<='Z'){
                T[i]=T[i]-'A'+'a';
            }
            if(T[i]=='\0'){
                break;
            }
        }
        if(strcmp(W,T)==0){
            count++;
        }
    }
    printf("%d\n",count);
    return 0;
}