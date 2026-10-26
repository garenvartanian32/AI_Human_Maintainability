
def findIndexGE(prefixSumsMax,startSum,query):
    n=len(prefixSumsMax)
    b=n
    i=-1
    while b>0:
        while i+b<n and startSum+prefixSumsMax[i+b]<query:
            i+=b
        b//=2
    i+=1
    return i

def main():
    
    t=int(input())
    allans=[]
    for _ in range(t):
        n,m=readIntArr()
        arr=readIntArr() #numbers of disk
        queries=readIntArr()
        
        prefixSums=arr.copy()
        for i in range(1,n):
            prefixSums[i]+=prefixSums[i-1]
        
        maxP=max(prefixSums)
        
        prefixSumsMax=prefixSums.copy()
        for i in range(1,n):
            prefixSumsMax[i]=max(prefixSumsMax[i-1],prefixSums[i])
        
        ans=[]
        for q in queries:
            if prefixSums[n-1]<=0:
                if q>maxP: #drive will run indefinitely
                    ans.append(-1)
                else:
                    ans.append(findIndexGE(prefixSumsMax,0,q))
            else:
                #find number of cycles
                nCycles=-1
                b=10**9
                while b>0:
                    while prefixSums[n-1]*(nCycles+b)+maxP<q:
                        nCycles+=b
                    b//=2
                nCycles+=1
                
                startSum=prefixSums[n-1]*nCycles
                lastCycleIdx=findIndexGE(prefixSumsMax,startSum,q)
                ans.append(nCycles*n+lastCycleIdx)
        allans.append(ans)
    multiLineArrayOfArraysPrint(allans)
    
    return
    
#import sys
#input=sys.stdin.buffer.readline #FOR READING PURE INTEGER INPUTS (space separation ok)
import sys
input=lambda: sys.stdin.readline().rstrip("\r\n") #FOR READING STRING/TEXT INPUTS.
 
def oneLineArrayPrint(arr):
    print(' '.join([str(x) for x in arr]))
def multiLineArrayPrint(arr):
    print('\n'.join([str(x) for x in arr]))
def multiLineArrayOfArraysPrint(arr):
    print('\n'.join([' '.join([str(x) for x in y]) for y in arr]))
 
def readIntArr():
    return [int(x) for x in input().split()]
 
inf=float('inf')
MOD=10**9+7
 
main()