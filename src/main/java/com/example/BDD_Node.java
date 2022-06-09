package com.example;

/**
 * This class represents BDD Tree Node with:
 * Boolean function 
 * Letter what will be substituted
 * Order of variables - "ABCD"
 * Left and Right pointers to new Nodes with smaller function 
 */
public class BDD_Node{
    public String b_function;
    public String letter; 
    public BDD_Node right;
    public BDD_Node left;
    public String order;

    BDD_Node(String b_func, String letter, String order){
        this.letter = letter;
        this.b_function = b_func;
        this.right = null;
        this.left = null;
        this.order = order;
    }
}