alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]
while True:
    n = int(input())
    if n == 0:
        break
    else:
        k = [int(k) for k in input().split()]
        s = list(input())
        for i in range(1,len(s)+1):
            if (i) % len(k) == 0:
                s[i-1] = alphabet[alphabet.index(s[i-1])-k[len(k)-1]]
            else:
                s[i-1] = alphabet[alphabet.index(s[i-1])-k[i % len(k)-1]]
        print("".join(s))
