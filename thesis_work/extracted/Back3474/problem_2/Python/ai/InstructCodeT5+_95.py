class Solution:
    def finalString(self, s: str) -> str:
        res = ""
        for i in s:
            if i!= 'i':
                res += i
            else:
                res = res[::-1]
        return res