package LeetCode.Test93;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Test93 {
    @Test
    public void testIP() {
        for (String s : restoreIpAddresses(this, "25525511135")) {
            System.out.println(s);
        }
    }

    public  List<String> restoreIpAddresses(Test93 test93, String s) {
        List<String> ret = new ArrayList<>();
        if(s.length()<4 || s.length()>12){
            return ret;
        }
        //dfs深度优先算法
        test93.dfs(s,"",ret,1);
        return ret;
    }
    //深度优先
    void  dfs(String s, String temp, List<String> res, int count){
        //递归方式
        if(count==4 && isvalid(s)){
            res.add(temp+s); //最后一次递归 s是最后一部分的剩余数据，temp是前面的数据
            return;
        }
        //从1开始
        for(int i=1; i<Math.min(4,s.length()); i++){
            //先进行判断i之前的字符串是否合法
            String subString = s.substring(0,i);
            if(isvalid(subString)){
                //递归调用自己，实现dfs深度优先
                dfs(s.substring(i) ,temp+subString+".", res,count+1);
            }
        }
    }
    boolean  isvalid(String string){
        //如果开头是0，只能是一位
        //非0， 判断是否在0-255之间
        if(string.charAt(0) == 0){
            return string.length()==1;
        }
        int anInt = Integer.parseInt(string);
        return anInt>0 && anInt<256;
    }

}



/*
public class Test93 {
    @Test
    public void testIP() {
        for (String s : restoreIpAddresses("25525511135")) {
            System.out.println(s);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() < 4 || s.length() > 12)//长度非法，去除
            return res;
        dfs(s, "", res, 1);
        return res;
    }
    //dfs--深度优先算法
    public void dfs(String s, String temp, List<String> res, int count) { //count控制次数
        if (count == 4 && isValid(s)) { //判断如果个数为4并且合法
            res.add(temp + s);
            return;
        }
        // Math.min(4, s.length())后面几位少于4的情况比如，0000
        for (int i = 1; i < Math.min(4, s.length()); i++) { //正常情况下min为4，所以遍历3次
            String cur = s.substring(0, i);
            if (isValid(cur)) {
                // i 是beginIndex
                dfs(s.substring(i), temp + cur + ".",
                        res, count + 1); //递归方式--获取子字符串
            }
        }
    }
    //判断是否合法：
    public boolean isValid(String s) {
        // 前导0，如果第一个字符是0，只能一个为0
        if (s.charAt(0) == '0') //第一个为0，那么只能等于0
            return s.equals("0");
        int num = Integer.parseInt(s);
        return 0 < num && num < 256;
    }


}
*/