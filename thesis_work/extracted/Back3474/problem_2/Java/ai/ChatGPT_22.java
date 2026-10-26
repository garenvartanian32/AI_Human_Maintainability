public class Solution {
    public String finalString(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder reverseBuffer = new StringBuilder();
        
        boolean reverse = false;
        
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                reverse = !reverse;
            } else {
                if (reverse) {
                    reverseBuffer.insert(0, c);
                } else {
                    result.append(c);
                }
            }
        }
        
        result.append(reverseBuffer);
        
        return result.toString();
    }
    
}