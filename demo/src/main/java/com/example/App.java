package com.example;

/**
 * Hello world!
 *
 */
public class App 
{   
    private final static String b_function = "AB+CD+!AB+!BD";
    private final static String order = "ABCD";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(b_function, order);
        Tree.PrintTree();
        System.out.println(Tree.BDD_USE("0000", Tree.Root));
    }
}
