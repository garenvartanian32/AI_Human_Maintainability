a='a'
exec('a=[s+chr(c)for s in a for c in range(97,ord(max(s))+2)];'*~-int(input()))
print(*a)