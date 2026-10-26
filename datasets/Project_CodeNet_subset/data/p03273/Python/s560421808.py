#-*-coding:utf-8-*-

def main():
    h, w = map(int, input().split())
    arry = [list(input()) for _ in range(h)]
    arry_new = []

    for i in range(h):
        if len(set(arry[i])) == 1 and arry[i][0] == '.':
            continue
        else:
            arry_new.append(arry[i])

    for j in range(len(arry_new[0])):
        flag = 1
        for k in range(len(arry_new)):
            if arry_new[k][j] == '.':
                flag *= 1
            else:
                flag *= 0
        if flag == 1:
            for l in range(len(arry_new)):
                arry_new[l][j] = ''
    for ans in arry_new:
        for i in ans:
            print(i, end='')
        print('')

if __name__ == '__main__':
    main()