package test11;
public class Solution {
    public int NumberOf1(int n) {
        int count=0;
        char[] charArray = Integer.toBinaryString(n).toCharArray();System.out.println(charArray);
        for (char c : charArray) {
            if((c & 1) == 1){
                count++;
            }
        }
        return count;
    }
}