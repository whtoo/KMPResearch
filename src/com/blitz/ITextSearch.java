package com.blitz;

public interface ITextSearch {
    /**
     * 通用的文本搜索方法
     * 目前只能匹配字母表为a-z的连续串
     * TODO 扩大字符集到ASCII
     *
     * @param text 待匹配字符串
     * @return -1：没有匹配成功，others：首次匹配模式串成功时，在text上的索引值
     */

    public int search(String text);
}
