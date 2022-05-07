package com.example;

/**
 * Hello world!
 *
 */
public class App 
{   
    private final static String b_function = "!B!D+!ABD+!A!B!CD+CD+!E";
    private final static String order = "ABCDE";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(b_function, order);
        Tree.PrintTree();
        System.out.println(Tree.BDD_USE("01001", Tree.Root));
    }
}
