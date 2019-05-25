package com.tree;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zt648
 * @time 五月:15:11:57
 * @project 数据结构与算法分析
 */
public class DemoMap2 {
    /**
     * 缺点慢，这是进行完全遍历，太慢，里面有重复情况
     *
     * @param theWords
     * @return
     */
    public static Map<String, List<String>>
    computeAdjacentWirds1(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        String[] words = new String[theWords.size()];
        theWords.toArray(words);
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (DemoMap.oneCharOff(words[i], words[j])) {
                    DemoMap.update(adjWords, words[i], words[j]);
                    DemoMap.update(adjWords, words[j], words[i]);
                }
            }
        }
        return adjWords;
    }

    public static Map<String, List<String>> computeAdjacentWirds2(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();
        for (String word : theWords) {
            DemoMap.update(wordsByLength, word.length(), word);
        }
        for (List<String> groupWords : wordsByLength.values()) {
            String[] words = new String[groupWords.size()];
            groupWords.toArray(words);
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    if (DemoMap.oneCharOff(words[i], words[j])) {
                        DemoMap.update(adjWords, words[i], words[j]);
                        DemoMap.update(adjWords, words[j], words[i]);
                    }
                }
            }
        }
        return adjWords;
    }

    /**
     * 这是第三种方法，将长度相同的数组中的每个单词进行判断，先移除每个位置上的一个字母，添加list中
     * 在对只有一个字母不同的list并且长度大于2的进行update操作
     * @param theWords
     * @return
     */
    public static Map<String, List<String>>
    computeAdjacentWirds3(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();

        for (String word : theWords) {
            DemoMap.update(wordsByLength, word.length(), word);
        }
        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {

            List<String> groupWords = entry.getValue();
            int group = entry.getKey();

            //在每个位置上进行操作
            for (int i = 0; i < group; i++) {
//                将每个位置的一个字符移除
                Map<String, List<String>> repToword = new TreeMap<>();
                for (String string : groupWords) {
                    String rep = string.substring(0, i) + string.substring(i + 1);
                    DemoMap.update(repToword, rep, string);
                }

                for (List<String> wordClique : repToword.values()
                ) {
                    if (wordClique.size() >= 2)
                        for (String s : wordClique
                        ) {
                            for (String s2 : wordClique
                            ) {
                                if (!s.equals(s2)) {
                                    DemoMap.update(adjWords, s, s2);
                                }
                            }
                        }
                }
            }
        }
        return adjWords;
    }

}
