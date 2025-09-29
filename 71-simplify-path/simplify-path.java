import java.util.Stack;
import java.util.Arrays;

class Solution {
    public String simplifyPath(String s) {
        Stack<String> stack = new Stack<>();
        
        // Split the path by '/'
        String[] components = s.split("/");
        
        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                // Ignore '.' and empty strings
                continue;
            } else if (component.equals("..")) {
                // Pop from stack for '..'
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Push valid directory names
                stack.push(component);
            }
        }
        
        if (stack.isEmpty()) {
            return "/";
        }
        
        StringBuilder ans = new StringBuilder();
        for (String dir : stack) {
            ans.append("/").append(dir);
        }
        
        return ans.toString();
    }
}