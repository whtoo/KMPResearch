package com.blitz;

import java.util.HashSet;
import java.util.Set;

public class KMPSearch implements ITextSearch {
    private int[][] dfa;
    private char[] pattern;
    private int R;

    public KMPSearch(String pattern) {
        this.pattern = pattern.toLowerCase().toCharArray();
        this.R = 256;
        this.initDFA();
    }

    private void initDFA() {

        /// 初始化dfa数组
        this.dfa = new int[R][pattern.length];

        //默认的重启匹配位置是0（也就是失败的话，从头开始匹配）
        int X = 0;
        // 第一个字符匹配成功后，应该等待匹配第二个字符（即索引为1的字符）
        dfa[pattern[0]][0] = 1;
        for (int j = 1; j < this.pattern.length; j++) {
            char currentChar = pattern[j];
            for (int c = 0; c < R; c++) {
                /// 2. X的使用
                /// X是（除正确匹配）当前列的重启位置
                /// X < j，则j列的重启值可以回看X列的参考值（但注意正确匹配不是）
                dfa[c][j] = dfa[c][X];
            }
            /// 当前字符串如果在模式串的第i个位置匹配的话，那么下一个等待匹配位置应该是 i+1
            dfa[currentChar][j] = j + 1;
            /// 下一列的重启位置
            /// 假设在X出现重启，则意味着在j位置的匹配失败了。
            /// 那么，对[0...j-1]这个字串就是就产生了滑动对齐。
            /// 此时，j位置之前的序列是匹配的（因为重启了）。
            /// 同时，j现在出现的字符是currentChar。
            /// 自然，这个时候的重启位置应该是dfa[currentChar][X]
            /// 1. X的发端
            X = dfa[currentChar][X];
        }
    }

    public int search(String s) {
        s = s.toLowerCase();
        int i, j, N = s.length(), M = pattern.length;
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[s.charAt(i)][j];
        }
        if (j == M) return i - M;
        else return -1;
    }
}
