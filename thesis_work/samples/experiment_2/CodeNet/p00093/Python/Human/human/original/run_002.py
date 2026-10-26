result = []
while True:
    a, b = map(int, input().split())
    if a == b == 0:
        break
    result.append("\n".join([str(i) for i in range(a, b+1) if i%400 == 0 or i%4 == 0 and i%100]) or "NA")

print("\n\n".join(result))
