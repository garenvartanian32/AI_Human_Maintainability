def main22():
    data = input().split()
    num = []
    for i in range(2):
        num.append(int(data[i]))
    gate = []
    for i in range(num[1]):
        karin = []
        karid = input().split()
        for j in range(2):
            karin.append(int(karid[j]))
        gate.append(karin)
    und = 1
    upp = num[0]
    for i in range(num[1]):
        if und < gate[i][0]:
            und = gate[i][0]
        if upp > gate[i][1]:
            upp = gate[i][1]
    result = upp - und + 1
    if result < 0:
        print(0)
    else:
        print(result)
        
if __name__ == "__main__":
    main22()