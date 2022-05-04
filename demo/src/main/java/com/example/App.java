package com.example;

/**
 * Hello world!
 *
 */
public class App 
{   
    private final static String b_function = "!A!B!HJK!MP+!AD!EGK!N!P+!C!D!JNO!P+AD!E!FL+!D!K";
    private final static String order = "ABCDEFGHIJKLMNOP";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(b_function, order);
        Tree.PrintTree();
        System.out.println(Tree.BDD_USE("0100001011110111", Tree.Root));
    }
}
