package com.example;

public class App 
{   
    private final static String b_function = "DFJN+A!BF!H!IKL!N+BCDF!G!H!JK!LN+BE!FHI!KL+CDE!F!JLN";
    private final static String order = "ABCDEFGHIJKLN";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(b_function, order);
        Tree.PrintTree();
        //System.out.println(Tree.BDD_USE("01001", Tree.ROOT));
    }
}
