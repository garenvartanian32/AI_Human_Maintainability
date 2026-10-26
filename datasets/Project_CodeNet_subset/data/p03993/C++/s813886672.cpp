#include <stdio.h>

int main()
{
  int n = 0, i = 0, count = 0;
  int* arr = NULL;
  scanf("%d", &n);
  arr = new int[n];
  for (; i < n; ++i)
  {
    scanf("%d", &arr[i]);
  }
  for (i = 0; i < n; ++i)
  {
    if (-1 != arr[i] && -1 != arr[arr[i] - 1] && arr[arr[i] - 1] == i + 1)
    {
      ++count;
      arr[i] = arr[arr[i] - 1] = -1;
    }
  }
  printf("%d", count);
  delete[] arr;
}