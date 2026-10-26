#include<bits/stdc++.h>
using namespace std;
int N;
struct data
{
    char s;
    int n;
}a[105],a1[105],a2[105];
void BubbleSort(int N)
{
    for(int i = 0; i < N; i++)
        for(int j = N - 1; j >= i + 1; j--)
            if(a1[j].n < a1[j - 1].n)
                swap(a1[j].s,a1[j - 1].s),swap(a1[j].n,a1[j - 1].n);
    for(int i = 0; i < N - 1; i++)
        printf("%c%d ",a1[i].s,a1[i].n);
    printf("%c%d\n",a1[N - 1].s,a1[N - 1].n);
}
void SelectionSort(int N)
{
    for(int i = 0; i < N; i++)
    {
        int mini = i;
        for(int j = i; j < N; j++)
            if(a2[j].n < a2[mini].n)
                mini = j;
        if(i != mini)
            swap(a2[i].n,a2[mini].n),swap(a2[i].s,a2[mini].s);
    }
    for(int i = 0; i < N - 1; i++)
        printf("%c%d ",a2[i].s,a2[i].n);
    printf("%c%d\n",a2[N - 1].s,a2[N - 1].n);
}
bool cmp()
{
    for(int i = 0; i < N; i++)
        if(a1[i].s != a2[i].s)
            return 0;
    return 1;
}
int main()
{
    cin.tie(0);
    cin >> N;
    for(int i = 0; i < N; i++)
    {
        cin >> a[i].s >> a[i].n;
        a1[i].s = a2[i].s = a[i].s;
        a1[i].n = a2[i].n = a[i].n;
    }
    BubbleSort(N);
    puts("Stable");
    SelectionSort(N);
    if(cmp())
        puts("Stable");
    else
        puts("Not stable");
    return 0;
}
