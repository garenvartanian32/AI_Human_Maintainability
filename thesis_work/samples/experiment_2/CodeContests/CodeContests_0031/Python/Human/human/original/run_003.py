def CF_7B():
    t,m=list(map(int,input().split()))
    operation=[]
    for i in range(0,t):
        line=input().split()
        if len(line)==2:
            line[1]=int(line[1])
        operation.append(line)
    
    memory=[None]*m
    id=1
    for i in range(0,t):
        if operation[i][0]=='alloc':
            memory,id=alloc(memory,operation[i][1],id)
        if operation[i][0]=='erase':
            memory=erase(memory,operation[i][1])[:]
        if operation[i][0]=='defragment':
            memory=defragment(memory)[:]
    return
    
def alloc(mem,n,id):
    length=0
    for i in range(0,len(mem)): 
        if mem[i]!=None:
            length=0
            continue
        else:
            length+=1
            if length==n:
                break
    if length<n:
        print('NULL')
        return [mem,id]
    else:
        for j in range(i-n+1,i+1):
            mem[j]=id
        print(id)
        id+=1
        return [mem,id]

def erase(mem,x):
    if not x in mem:
        print('ILLEGAL_ERASE_ARGUMENT')
    else:
        for i in range(0,len(mem)):
            if mem[i]==x:
                mem[i]=None
    return mem
    
def defragment(mem):
    res=[]
    for i in range(0,len(mem)):
        if mem[i]!=None:
            res.append(mem[i])
    res.extend([None]*mem.count(None))

    return res
    
CF_7B()
