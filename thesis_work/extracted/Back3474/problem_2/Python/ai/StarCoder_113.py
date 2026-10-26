class Solution:
    def finalString(self, s: str) -> str:
        s = list(s)
        for i in range(len(s)):
            if s[i] == 'i':
                s[i] = 'o'
            else:
                s[i] = 'i'
        return ''.join(s)