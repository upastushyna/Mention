package com.mention.Workflow;

public class StringPrinter {
    public static void main(String[] args) {
        StringMakerOne s1 = new StringMakerOne();
        StringMakerTwo s2 = new StringMakerTwo();
        System.out.println(s1.getStringOne() + " " + s2.getStringTwo());
    }
}
