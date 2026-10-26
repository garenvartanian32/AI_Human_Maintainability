class Solution:
    def finalString(self, s: str) -> str:
        stack = []
        for i in range(len(s)):
            if s[i] == 'i':
                stack.reverse()
            else:
                stack.append(s[i])
        return "".join(stack)