while True:
    n = int(input())#文字列の量
    if n == 0:
        break
    w = list()#文字用
    for i in range(0, n):#入力
        w.append(input())

    for j in range(1, n+1):#始めの文字の場所
        ck = 0
        co = 0
        p = 0
        t = ""
        for k in range(j-1,50):#５７５７７チェック用
            #print(k)
            if co == 0 or co == 2:#５の場所
                if ck < 5:#五以下
                    #print("<")
                    ck = ck + len(w[k+p])#文字増やす
                elif ck == 5:#ピッタリになったら
                    #print("==")
                    co = co + 1#次に移る
                    ck = 0
                    p = p-1
                else:#オーバーしたら
                    #print(">")
                    break
            else:
                if ck < 7:
                    #print("<7")
                    ck = ck+len(w[k+p])
                    #print("      " ,ck)
                elif ck == 7:
                    #print("==7")
                    co = co+1
                    ck = 0
                    p = p-1
                    if co == 5:
                        t = "fin"
                        break
                else:
                    #print(">7")
                    break

        if t == "fin":
            print(j)
            break
    #print("over")

