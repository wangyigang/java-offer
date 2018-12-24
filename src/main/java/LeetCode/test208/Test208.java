package LeetCode.test208;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test208 {
    @Test
    public void test() {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

}

/*
    实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

        示例:

        Trie trie = new Trie();

        trie.insert("apple");
        trie.search("apple");   // 返回 true
        trie.search("app");     // 返回 false
        trie.startsWith("app"); // 返回 true
        trie.insert("app");
        trie.search("app");     // 返回 true
        说明:

        你可以假设所有的输入都是由小写字母 a-z 构成的。
        保证所有输入均为非空字符串。
      */

class Trie {
    private Set<String> set;
    /**
     * Initialize your data structure here.
     */
    public Trie() {
        set =new HashSet<>();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        set.add(word);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        for (String s : set) {
            if(word.equals(s)){
                return  true;
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        for (String s : set) {
            if(s.startsWith(prefix)){
                return true;
            }
        }
        return false;
    }
}
