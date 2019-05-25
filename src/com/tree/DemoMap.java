package com.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通过使用map的结构计算一个词典中有相似结构的字母的数量
 * fine line nine vine
 *
 */
public class DemoMap {
    public static void printHighChangaeables(Map<String, List<String>> adjWords,
                                             int minWords){
        for(Map.Entry<String,List<String>> entry:adjWords.entrySet()){
            List<String> words = entry.getValue();
            if(words.size() >= minWords){
                System.out.print(entry.getKey()+"(");
                System.out.print(words.size()+"):");
                for(String w:words){
                    System.out.print(" "+w);
                }
                System.out.println();
            }
        }
    }

    /**
     * 判断两个单词不一的的字母数，只有一个不相同返回ture;
     * @param word1
     * @param word2
     * @return
     */
    public static boolean oneCharOff(String word1,String word2){
        if(word1.length() != word2.length())
            return false;
        int diffs = 0;
        for(int i=0;i<word1.length();i++){
            if(word1.charAt(i) != word2.charAt(i))
                if(++diffs>1)
                    return false;
        }
        return diffs == 1;
    }
    public static <K> void update(Map<K,List<String>> m,K key,String value){
        List<String> lst = m.get(key);
        if(lst == null){
            lst = new ArrayList<>();
            m.put(key,lst);
        }
        lst.add(value);
    }
}
