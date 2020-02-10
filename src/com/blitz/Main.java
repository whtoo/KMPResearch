package com.blitz;

public class Main {

    public static void main(String[] args) {
	    KMPSearch kmp = new KMPSearch("ABABC");
	    String s = "ABABCABABADBAC";
	    System.out.println(kmp.match(s));
    }
}
