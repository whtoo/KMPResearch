package com.blitz;

public class SunDaySearch implements ITextSearch {
    private String pattern;
    private int[] next;

    public SunDaySearch(String pattern) {
        this.pattern = pattern.toLowerCase();
        this.next = new int[26];

        init();
    }

    void init() {
        for (int i = 0; i < pattern.length(); i++) {
            this.next[i] = pattern.length() + 1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            /// 更新字母到模式最右端的距离
            next[pattern.charAt(i) - 'a'] = pattern.length() - i;
        }
    }

    @Override
    public int search(String text) {
        int pos = 0;
        int j = 0;
        int i;
        text = text.toLowerCase();
        while (pos < (text.length() - this.pattern.length() + 1)) {
            j = pos;

            for (i = 0; i < this.pattern.length(); i++, j++) {
                if (text.charAt(j) != this.pattern.charAt(i)) {
                    /// 根据next做滑动
                    /// 1. 如果完全不匹配，直接整个模式滑动自身大小
                    /// 2。如果当前字符的下一个可以和模式内某个字符匹配，那么滑动这个字符在模式串
                    /// 最右侧距离
                    pos += next[text.charAt(pos + this.pattern.length()) - 'a'];
                    break;
                }
            }
            if (i == this.pattern.length()) {
                return pos;
            }
        }

        return -1;
    }
}
