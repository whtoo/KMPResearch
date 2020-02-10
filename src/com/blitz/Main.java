package com.blitz;

public class Main {

    public static void main(String[] args) {
        KMPSearch kmp = new KMPSearch("ABABC");
        String s = "ABABCABABADBAC";
        System.out.println(kmp.search(s));
        SunDaySearch sunDaySearch = new SunDaySearch("BABAD");
        System.out.println(sunDaySearch.search(s));
    }
}
