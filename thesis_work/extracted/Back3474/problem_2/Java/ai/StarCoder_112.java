public class Solution {
    public String finalString(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'i') {
                result = new StringBuilder(result).reverse().toString();
            } else {
                result += c;
            }
        }
        return result;
    }
}