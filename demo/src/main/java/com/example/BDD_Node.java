package com.example;

public class BDD_Node{
    private String b_function;
    private String letter; 
    private BDD_Node right;
    private BDD_Node left;

    BDD_Node(String b_func, String letter){
        this.letter = letter;
        this.b_function = b_func;
        this.right = null;
        this.left = null;
    }
}