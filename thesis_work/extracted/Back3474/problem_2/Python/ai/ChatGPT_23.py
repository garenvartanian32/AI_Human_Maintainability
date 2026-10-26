class Solution:
    def finalString(self, s: str) -> str:
        stack = []  # Use a stack to simulate typing process
        
        for char in s:
            if char == 'i':
                # If the character is 'i', reverse the stack
                stack.reverse()
            else:
                stack.append(char)
        
        # Convert the stack to a string and return
        return ''.join(stack)